package com.janeirodigital.xform.webdriver.common;

import com.janeirodigital.xform.webdriver.enums.BrowsersEnum;
import com.janeirodigital.xform.webdriver.enums.XmlEnum;
import org.aeonbits.owner.ConfigFactory;
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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Initial {
    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(Initial.class);
    private Environment testEnvironment;

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


    private WebDriver getDriverChrome(BrowsersEnum browser) {
        String binaryName = null;
        boolean headLess = false;
        switch (browser) {
            case CHROME_WIN:
                binaryName = XmlEnum.GOOGLE_BINARY.getTagName();
                break;
            case CHROME_HEAD_LESS_WIN:
                binaryName = XmlEnum.GOOGLE_BINARY.getTagName();
                headLess = true;
                break;
            case CHROME_LINUX_32:
                binaryName = XmlEnum.CHROME_LINUX_32.getTagName();
                headLess = true;
                break;
            case CHROME_LINUX_64:
                binaryName = XmlEnum.CHROME_LINUX_64.getTagName();
                headLess = true;
                break;
        }
        ChromeOptions optionsChrome = new ChromeOptions();

        try {
            if (headLess){
                optionsChrome.setBinary(getValueFromConfig(XmlEnum.GOOGLE_EXE_LINUX.getTagName()));
                optionsChrome.addArguments("--headless");
                optionsChrome.addArguments("--disable-gpu");
                optionsChrome.addArguments("--no-sandbox");
                optionsChrome.addArguments("window-size=1280x1024");
            }else{
                optionsChrome.setBinary(getValueFromConfig(XmlEnum.GOOGLE_EXE.getTagName()));
            }
            System.setProperty("webdriver.chrome.driver", getValueFromConfig(binaryName));
            logger.debug("Browser instantiated: ", binaryName);
            return new ChromeDriver(optionsChrome);

        } catch (Exception e) {
            logger.error("getDriverChrome has failed - Version: {} ", binaryName, e);
            return null;
        }

    }

    private WebDriver getDriverFireFox() {

        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        try {
            FirefoxOptions options = new FirefoxOptions();

            options.addPreference("log", "{level: trace}");

            capabilities.setCapability("marionette", true);
            capabilities.setCapability("moz:firefoxOptions", options);

            System.setProperty("webdriver.gecko.driver", getValueFromConfig(XmlEnum.FIRE_FOX_BINARY.getTagName()));

            return new FirefoxDriver();

        } catch (Exception e) {
            logger.error("getDriverFireFox has failed: ", e);
            return null;
        }

    }

    private WebDriver getDriverEdge() {

        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        try {
            System.setProperty("webdriver.edge.driver", getValueFromConfig(XmlEnum.IE_BINARY.getTagName()));
            return new EdgeDriver();
        } catch (Exception e) {
            logger.error("getDriverEdge has failed: ", e);
            return null;
        }

    }


    private WebDriver getDriverPhantomJS() {
        DesiredCapabilities caps = new DesiredCapabilities();
        try {
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
        try {
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

        File fXmlFile = new File("../controllers/src/main/resources/Config.xml");
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

}

