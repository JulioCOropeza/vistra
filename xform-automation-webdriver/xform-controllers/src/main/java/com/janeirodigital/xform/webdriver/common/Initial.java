package com.janeirodigital.xform.webdriver.common;

import com.janeirodigital.xform.webdriver.actions.AccessAndSecurityActions;
import com.janeirodigital.xform.webdriver.enums.BrowsersEnum;
import com.janeirodigital.xform.webdriver.enums.XmlEnum;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.testng.ITestContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Initial {
    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(Initial.class);
    private Environment testEnvironment;
    public Common common;
    private AccessAndSecurityActions actions;

    public Initial() {
    }

    public WebDriver getDriver(BrowsersEnum browser) {
        try {
            logger.debug("Driver is starting to be created");

            switch (browser) {
                case CHROME_WIN:
                    driver = getDriverChrome(browser.CHROME_WIN);
                    break;
                case CHROME_HEAD_LESS_WIN:
                    driver = getDriverChrome(browser.CHROME_HEAD_LESS_WIN);
                    break;
                case CHROME_LINUX_32:
                    driver = getDriverChrome(browser.CHROME_LINUX_32);
                    break;
                case CHROME_LINUX_64:
                    driver = getDriverChrome(browser.CHROME_LINUX_64);
                    break;
                case FIREFOX_WIN:
                    driver = getDriverFireFox();
                    break;
                case EDGE_WIN:
                    driver = getDriverEdge();
                    break;
                case HEAD_LESS_WIN:
                    driver = new HtmlUnitDriver();
                    break;
                case PHANTOM_JS_WIN:
                    driver = getDriverPhantomJS();
                    break;
                case PHANTOM_JS_LINUX_64:
                    driver = getDriverPhantomJSLinux64();
                    break;
            }

            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            return driver;

        } catch (Exception e) {
            logger.error("The WebDriver was not created: ", e);
            return null;

        }

    }

    /**
     * This Method Is Used To Download a WebDriver File From an specific Url
     * uncompress the downloaded file and rename it
     *
     * @param sUrlStr           url of the file to be downloaded
     * @param sFilePath         path and name of the file to be stored locally
     * @param sUnzipPath        path where the file is going to be uncompressed
     * @param sForced           flag to force download process even a previous driver exist
     * @param sFileOriginalName original file name after decompression
     * @param sFileNewName      name to be set after file decompression, if null the file stay original
     */
    private void downloadFileFromUrl(String sUrlStr, String sFilePath, String sUnzipPath, Boolean sForced, String sFileOriginalName, String sFileNewName) {

        URL urlObj;
        ReadableByteChannel rbcObj = null;
        FileOutputStream fOutStream = null;

        // Checking If The File Exists At The Specified Location Or Not
        Path filePathObj = Paths.get(sFilePath);
        boolean fileExists = Files.exists(filePathObj);
        if (!fileExists || sForced) {
            try {
                urlObj = new URL(sUrlStr);
                rbcObj = Channels.newChannel(urlObj.openStream());
                fOutStream = new FileOutputStream(sFilePath);

                fOutStream.getChannel().transferFrom(rbcObj, 0, Long.MAX_VALUE);
                logger.info("WebDriver File Successfully Downloaded!");
                unzip(sFilePath, sUnzipPath, "", sFileOriginalName, sFileNewName);

            } catch (IOException ex) {
                logger.error("Problem occured while downloading WebDriver file = {} ", ex.getMessage());
            } finally {
                try {
                    if (fOutStream != null) {
                        fOutStream.close();
                    }
                    if (rbcObj != null) {
                        rbcObj.close();
                    }
                } catch (IOException ex) {
                    logger.error("Problem occured while Closing the Downloaded WebDriver = {}" + ex.getMessage());
                }
            }
        } else {
            logger.info("WebDriver File Present!");
        }
    }

    public void fileRename(String sOriginalName, String sNewName) {
        File fExecutableFile = null;
        Path f = Paths.get(sOriginalName);
        Path rF = Paths.get(sNewName);
        try {
            Files.move(f, rF, StandardCopyOption.REPLACE_EXISTING);

            fExecutableFile = new File(sNewName);
            fExecutableFile.setExecutable(true);

            logger.info("File was successfully renamed");
        } catch (IOException e) {
            logger.error("Error: Unable to rename file: {}", e.getMessage());
        }
    }

    private WebDriver getDriverChrome(BrowsersEnum browser) {
        String sBinaryName = null;
        boolean bHeadLess = false;
        boolean bLinux = false;
        String sUrl = null;
        String sLocalPath = null;
        String sUnzipPath = null;
        Boolean bDownLoadForced = null;
        String sFileOriginalName = null;
        String sFileNewName = null;

        try {
            switch (browser) {
                case CHROME_WIN:
                case CHROME_HEAD_LESS_WIN:
                    sUrl = getValueFromConfig(XmlEnum.DOWNLOAD_CHROME_WIN32_WD.getTagName());
                    sLocalPath = getValueFromConfig(XmlEnum.DOWNLOAD_CHROME_WIN32_WD_LOCAL_PATH.getTagName());
                    sBinaryName = XmlEnum.GOOGLE_BINARY.getTagName();
                    if (browser.equals(BrowsersEnum.CHROME_HEAD_LESS_WIN)) {
                        bHeadLess = true;
                    }
                    break;
                case CHROME_LINUX_32:
                    sUrl = getValueFromConfig(XmlEnum.DOWNLOAD_CHROME_LINUX_32_WD.getTagName());
                    sLocalPath = getValueFromConfig(XmlEnum.DOWNLOAD_CHROME_LINUX_32_WD_LOCAL_PATH.getTagName());
                    sFileOriginalName = getValueFromConfig(XmlEnum.CHROME_LINUX_ORIGINAL_BINARY_NAME.getTagName());
                    sFileNewName = getValueFromConfig(XmlEnum.DOWNLOAD_UNZIP_WD_LOCAL_PATH.getTagName()) + "/" + getValueFromConfig(XmlEnum.CHROME_LINUX_32_BINARY_NAME.getTagName());
                    sBinaryName = XmlEnum.CHROME_LINUX_32.getTagName();
                    bHeadLess = true;
                    bLinux = true;
                    break;
                case CHROME_LINUX_64:
                    sUrl = getValueFromConfig(XmlEnum.DOWNLOAD_CHROME_LINUX_64_WD.getTagName());
                    sLocalPath = getValueFromConfig(XmlEnum.DOWNLOAD_CHROME_LINUX_64_WD_LOCAL_PATH.getTagName());
                    sFileNewName = getValueFromConfig(XmlEnum.DOWNLOAD_UNZIP_WD_LOCAL_PATH.getTagName()) + "/" + getValueFromConfig(XmlEnum.CHROME_LINUX_64_BINARY_NAME.getTagName());
                    sBinaryName = XmlEnum.CHROME_LINUX_64.getTagName();
                    bHeadLess = true;
                    bLinux = true;
                    break;
            }
            sFileOriginalName = getValueFromConfig(XmlEnum.DOWNLOAD_UNZIP_WD_LOCAL_PATH.getTagName()) + "/" + getValueFromConfig(XmlEnum.CHROME_LINUX_ORIGINAL_BINARY_NAME.getTagName());
            sUnzipPath = getValueFromConfig(XmlEnum.DOWNLOAD_UNZIP_WD_LOCAL_PATH.getTagName());
            bDownLoadForced = Boolean.valueOf(getValueFromConfig(XmlEnum.DOWNLOAD_UNZIP_WD_FORCED.getTagName()));
            downloadFileFromUrl(sUrl, sLocalPath, sUnzipPath, bDownLoadForced, sFileOriginalName, sFileNewName);

            ChromeOptions optionsChrome = new ChromeOptions();

            if (bLinux) {
                optionsChrome.setBinary(getValueFromConfig(XmlEnum.GOOGLE_EXE_LINUX.getTagName()));
                optionsChrome.addArguments("--headless");
                optionsChrome.addArguments("--disable-gpu");
                optionsChrome.addArguments("--no-sandbox");
                optionsChrome.addArguments("window-size=1280x1024");
            } else {
                optionsChrome.setBinary(getValueFromConfig(XmlEnum.GOOGLE_EXE.getTagName()));
                if (bHeadLess) {
                    optionsChrome.addArguments("--headless");
                    optionsChrome.addArguments("--disable-gpu");
                }
            }
            System.setProperty("webdriver.chrome.driver", getValueFromConfig(sBinaryName));
            logger.debug("Browser instantiated: ", sBinaryName);
            return new ChromeDriver(optionsChrome);

        } catch (Exception e) {
            logger.error("getDriverChrome has failed - Version: {} ", sBinaryName, e);
            return null;
        }

    }

    private WebDriver getDriverFireFox() {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        String sBinaryName = null;
        String sUrl = null;
        String sLocalPath = null;
        String sUnzipPath = null;
        Boolean bDownLoadForced = null;
        String sFileOriginalName = null;
        String sFileNewName = null;

        try {
            sUrl = getValueFromConfig(XmlEnum.DOWNLOAD_FIRE_FOX_WD.getTagName());
            sLocalPath = getValueFromConfig(XmlEnum.DOWNLOAD_FIRE_FOX_WD_LOCAL_PATH.getTagName());
            sBinaryName = XmlEnum.FIRE_FOX_BINARY.getTagName();
            sUnzipPath = getValueFromConfig(XmlEnum.DOWNLOAD_UNZIP_WD_LOCAL_PATH.getTagName());
            bDownLoadForced = Boolean.valueOf(getValueFromConfig(XmlEnum.DOWNLOAD_UNZIP_WD_FORCED.getTagName()));
            downloadFileFromUrl(sUrl, sLocalPath, sUnzipPath, bDownLoadForced, sFileOriginalName, sFileNewName);

            FirefoxOptions options = new FirefoxOptions();

            options.addPreference("log", "{level: trace}");

            capabilities.setCapability("marionette", true);
            capabilities.setCapability("moz:firefoxOptions", options);

            System.setProperty("webdriver.gecko.driver", getValueFromConfig(sBinaryName));

            return new FirefoxDriver();

        } catch (Exception e) {
            logger.error("getDriverFireFox has failed: ", e);
            return null;
        }

    }

    private WebDriver getDriverEdge() {

        String sBinaryName = null;
        String sUrl = null;
        String sLocalPath = null;
        String sUnzipPath = null;
        Boolean bDownLoadForced = null;
        String sFileOriginalName = null;
        String sFileNewName = null;

        try {
            sUrl = getValueFromConfig(XmlEnum.DOWNLOAD_IE__WD.getTagName());
            sLocalPath = getValueFromConfig(XmlEnum.DOWNLOAD_IE__WD_LOCAL_PATH.getTagName());
            sBinaryName = XmlEnum.IE_BINARY.getTagName();
            sUnzipPath = getValueFromConfig(XmlEnum.DOWNLOAD_UNZIP_WD_LOCAL_PATH.getTagName());
            bDownLoadForced = Boolean.valueOf(getValueFromConfig(XmlEnum.DOWNLOAD_UNZIP_WD_FORCED.getTagName()));
            downloadFileFromUrl(sUrl, sLocalPath, sUnzipPath, bDownLoadForced, sFileOriginalName, sFileNewName);

            System.setProperty("webdriver.edge.driver", getValueFromConfig(XmlEnum.IE_BINARY.getTagName()));
            return new EdgeDriver();
        } catch (Exception e) {
            logger.error("getDriverEdge has failed: ", e);
            return null;
        }

    }

    private WebDriver getDriverPhantomJS() {
        DesiredCapabilities caps = new DesiredCapabilities();
        String sBinaryName = null;
        String sUrl = null;
        String sLocalPath = null;
        String sUnzipPath = null;
        Boolean bDownLoadForced = null;
        String sFileOriginalName = null;
        String sFileNewName = null;
        try {
            sUrl = getValueFromConfig(XmlEnum.DOWNLOAD_PHANTOM_JS_WIN_WD.getTagName());
            sLocalPath = getValueFromConfig(XmlEnum.DOWNLOAD_PHANTOM_JS_WIN_WD_LOCAL_PATH.getTagName());
            sBinaryName = XmlEnum.PHANTOM_JS_BINARY_NAME.getTagName();
            sUnzipPath = getValueFromConfig(XmlEnum.DOWNLOAD_UNZIP_WD_LOCAL_PATH.getTagName());
            bDownLoadForced = Boolean.valueOf(getValueFromConfig(XmlEnum.DOWNLOAD_UNZIP_WD_FORCED.getTagName()));
            downloadFileFromUrl(sUrl, sLocalPath, sUnzipPath, bDownLoadForced, sFileOriginalName, sFileNewName);

            caps.setJavascriptEnabled(true);
            caps.setCapability("takesScreenshot", true);
            caps.setCapability(
                    PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                    getValueFromConfig(XmlEnum.PHANTOM_JS.getTagName())
            );
            driver = new PhantomJSDriver(caps);
            driver.manage().window().setSize(new Dimension(1280, 1024));
            return driver;
        } catch (Exception e) {
            logger.error("getDriverPhantomJS has failed: ", e);
            return null;
        }

    }

    private WebDriver getDriverPhantomJSLinux64() {
        DesiredCapabilities capsPhantomJSLinux64 = new DesiredCapabilities();
        String sBinaryName = null;
        String sUrl = null;
        String sLocalPath = null;
        String sUnzipPath = null;
        Boolean bDownLoadForced = null;
        String sFileOriginalName = null;
        String sFileNewName = null;
        try {
            sUrl = getValueFromConfig(XmlEnum.DOWNLOAD_PHANTOM_JS_LINUX_WD.getTagName());
            sLocalPath = getValueFromConfig(XmlEnum.DOWNLOAD_PHANTOM_JS_LINUX_WD_LOCAL_PATH.getTagName());
            sBinaryName = XmlEnum.PHANTOM_JS_LINUX_64_BINARY_NAME.getTagName();
            sUnzipPath = getValueFromConfig(XmlEnum.DOWNLOAD_UNZIP_WD_LOCAL_PATH.getTagName());
            bDownLoadForced = Boolean.valueOf(getValueFromConfig(XmlEnum.DOWNLOAD_UNZIP_WD_FORCED.getTagName()));
            downloadFileFromUrl(sUrl, sLocalPath, sUnzipPath, bDownLoadForced, sFileOriginalName, sFileNewName);

            capsPhantomJSLinux64.setJavascriptEnabled(true);
            capsPhantomJSLinux64.setCapability("takesScreenshot", true);
            capsPhantomJSLinux64.setCapability(
                    PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                    getValueFromConfig(XmlEnum.PHANTOM_JS_LINUX_64.getTagName())
            );
            driver = new PhantomJSDriver(capsPhantomJSLinux64);
            driver.manage().window().setSize(new Dimension(1280, 1024));
            return driver;
        } catch (Exception e) {
            logger.error("getDriverPhantomJSLinux64 has failed: ", e);
            return null;
        }
    }

    public void setTestEnvironment(Environment testEnvironment) {
        this.testEnvironment = testEnvironment;
    }

    public String getValueFromConfig(String Value) throws Exception {

        File fXmlFile = new File(testEnvironment.config());
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("config");
        Node nNode = nList.item(0);
        Element eElement = (Element) nNode;
        String content = eElement.getElementsByTagName(Value.toString()).item(0).getTextContent();
        if (content.isEmpty())
            throw new Exception("Cannot find the value in the resource file Config");

        return content;

    }

    public void unzip(String sSource, String sDestination, String sPassword, String sFileOriginalName, String sFileNewName) {
        String sFileExtension = null;
        sFileExtension = getExtension(sSource);
        try {
            if (sFileExtension.equals("bz2")) {
                unzip_bz2(sSource, sDestination);
            } else if (sFileExtension.equals("zip")) {
                ZipFile zipFile = new ZipFile(sSource);
                if (zipFile.isEncrypted()) {
                    zipFile.setPassword(sPassword);
                }
                zipFile.extractAll(sDestination);

                if (sFileNewName != null) {
                    fileRename(sFileOriginalName, sFileNewName);
                }
            }

        } catch (ZipException e) {
            logger.error("unzip webdriver process has failed: {} ", e);
        }

    }

    private void unzip_bz2(String sSource, String sDestination) {
        try {

            FileInputStream fin = new FileInputStream(sSource);
            BufferedInputStream in = new BufferedInputStream(fin);
            FileOutputStream out = new FileOutputStream(sDestination + "/TempDownload.tar");
            BZip2CompressorInputStream bzIn = new BZip2CompressorInputStream(in);

            int buffersize = 1024;
            final byte[] buffer = new byte[buffersize];
            int n = 0;
            while (-1 != (n = bzIn.read(buffer))) {
                out.write(buffer, 0, n);
            }
            out.close();
            bzIn.close();

            sSource = sDestination + "/TempDownload.tar";
            decompress_tar(sSource, new File(sDestination));

        } catch (IOException e) {
            logger.error("unTAR webdriver process has failed: {} ", e);
        } catch (Exception e) {
            logger.error("unBZ2 webdriver process has failed: {} ", e);
            throw new Error(e.getMessage());
        }
    }

    public static void decompress_tar(String in, File out) throws IOException {
        try (TarArchiveInputStream fin = new TarArchiveInputStream(new FileInputStream(in))) {
            TarArchiveEntry entry;
            while ((entry = fin.getNextTarEntry()) != null) {
                if (entry.isDirectory()) {
                    continue;
                }
                File curfile = new File(out, entry.getName());
                File parent = curfile.getParentFile();
                if (!parent.exists()) {
                    parent.mkdirs();
                }
                IOUtils.copy(fin, new FileOutputStream(curfile));
            }
        }
    }

    private String getExtension(String sFileName) {
        if (sFileName.length() == 3) {
            return sFileName;
        } else if (sFileName.length() > 3) {
            return sFileName.substring(sFileName.length() - 3);
        } else {
            logger.error("unzip webdriver process has failed, FileName has less than 3 characters!:");
            throw new IllegalArgumentException("word has less than 3 characters!");
        }
    }

    /**
     * This method is in charge to create the instance and read from the excel file to configure the instance created previously.
     * @param context
     * @param browser
     * @param environment
     * @param driver
     */

    public void configInit(ITestContext context, String browser, String environment, WebDriver driver){
        try {
            ConfigFactory.setProperty("env", environment);
            testEnvironment = ConfigFactory.create(Environment.class);
            setTestEnvironment(testEnvironment);
            BrowsersEnum browserEnum = BrowsersEnum.valueOf(browser);
            driver = getDriver(browserEnum);
            common = new Common(driver);
            actions = new AccessAndSecurityActions(driver);
            common.setTestEnvironment(testEnvironment);
        } catch (Exception e) {
            logger.error("Initial configuration activities has failed: {} ", e.getMessage());
        }
        try {
            context.setAttribute("driver", driver); //Current driver to be shared among Tests under execution
            context.setAttribute("common", common); //Current common functions to be shared among Tests under execution
            common.OpenBaseURL(testEnvironment.urlMiddleOffice());
        } catch (Exception e) {
            logger.error("BeforeClass configuration activities has failed: {} ", e.getMessage());
        }
    }
}

