package common;

import enums.XmlEnum;
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

/*
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
*/

public class Initial {
	WebDriver driver;

	public Initial() {
	}

	public WebDriver getDriver(String browser) {
		try {
			switch (browser){
				case "Chrome":
					ChromeOptions optionsChrome = new ChromeOptions();
					optionsChrome.setBinary(getValueFromConfig(XmlEnum.GoogleExe));
					System.setProperty("webdriver.chrome.driver", getValueFromConfig(XmlEnum.GoogleBinary));

					driver = new ChromeDriver(optionsChrome);
					break;
				case "ChromeHeadLess":
					ChromeOptions optionsChromeHeadLess = new ChromeOptions();

					optionsChromeHeadLess.setBinary(getValueFromConfig(XmlEnum.GoogleExe));

					optionsChromeHeadLess.addArguments("headless");
					optionsChromeHeadLess.addArguments("window-size=1280x1024");

					System.setProperty("webdriver.chrome.driver", getValueFromConfig(XmlEnum.GoogleBinary));

					driver = new ChromeDriver(optionsChromeHeadLess);
					break;
				case "ChromeLinux32":
					ChromeOptions optionsChromeLinux32 = new ChromeOptions();

					optionsChromeLinux32.setBinary(getValueFromConfig(XmlEnum.ChromeLinux32));

					optionsChromeLinux32.addArguments("headless");
					optionsChromeLinux32.addArguments("window-size=1280x1024");

					System.setProperty("webdriver.chrome.driver", getValueFromConfig(XmlEnum.ChromeLinux32));

					driver = new ChromeDriver(optionsChromeLinux32);
					break;
				case "ChromeLinux64":
					ChromeOptions optionsChromeLinux64 = new ChromeOptions();

					optionsChromeLinux64.setBinary(getValueFromConfig(XmlEnum.ChromeLinux64));

					optionsChromeLinux64.addArguments("headless");
					optionsChromeLinux64.addArguments("window-size=1280x1024");

					System.setProperty("webdriver.chrome.driver", getValueFromConfig(XmlEnum.ChromeLinux64));

					driver = new ChromeDriver(optionsChromeLinux64);
					break;
				case "Firefox":
					//System.setProperty("webdriver.firefox.marionette", getValueFromConfig(XmlEnum.FireFoxBinary));
					DesiredCapabilities capabilities = DesiredCapabilities.firefox();
					FirefoxOptions options = new FirefoxOptions();

					options.addPreference("log", "{level: trace}");

					capabilities.setCapability("marionette", true);
					capabilities.setCapability("moz:firefoxOptions", options);

					System.setProperty("webdriver.gecko.driver", getValueFromConfig(XmlEnum.FireFoxBinary));

					driver = new FirefoxDriver(capabilities);
					//driver = new FirefoxDriver();
					break;
				case "Edge":
					System.setProperty("webdriver.edge.driver", getValueFromConfig(XmlEnum.IEBinary));

					driver = new EdgeDriver();
					break;
				case "HeadLess":
					driver = new HtmlUnitDriver();
					break;
				case "PhantomJS":
					/*
					System.setProperty("phantomjs.binary.path", getValueFromConfig(XmlEnum.PhantomJS));
					driver = new PhantomJSDriver();
					driver.manage().window().setSize(new Dimension(1280, 1024));
					//browser.driver.manage().window().setSize(1280, 1024);
					*/

					DesiredCapabilities caps = new DesiredCapabilities();
					caps.setJavascriptEnabled(true);
					caps.setCapability("takesScreenshot", true);
					caps.setCapability(
							PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
							getValueFromConfig(XmlEnum.PhantomJS)
					);
					driver = new  PhantomJSDriver(caps);
					driver.manage().window().setSize(new Dimension(1280, 1024));

					break;
				case "PhantomJSLinux64":
					/*System.setProperty("phantomjs.binary.path", getValueFromConfig(XmlEnum.PhantomJSLinux64));
					driver = new PhantomJSDriver();
					driver.manage().window().setSize(new Dimension(1280, 1024));
					//browser.driver.manage().window().setSize(1280, 1024);*/

					DesiredCapabilities capsPhantomJSLinux64 = new DesiredCapabilities();
					capsPhantomJSLinux64.setJavascriptEnabled(true);
					capsPhantomJSLinux64.setCapability("takesScreenshot", true);
					capsPhantomJSLinux64.setCapability(
							PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
							getValueFromConfig(XmlEnum.PhantomJSLinux64)
					);

					driver = new  PhantomJSDriver(capsPhantomJSLinux64);
					//driver.manage().window().maximize();
					//driver.manage().window().setSize(new Dimension(1280, 1024));

					break;

			}


			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			return driver;

		} catch (Exception e) {
			System.out.print("\n==============  ERROR CREATING WEBDRIVER =============\n");
			e.printStackTrace();
			return null; // need to change for a exception

		}

	}


	public String getValueFromConfig(XmlEnum Value) throws Exception {
		File fXmlFile = new File("../controllers/src/main/resources/Config.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();

		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName("config");
		Node nNode = nList.item(0);
		Element eElement = (Element) nNode;
		String content = eElement.getElementsByTagName(Value.toString()).item(0).getTextContent();
		if (content.isEmpty())
			throw new Exception("Cannot find the value in the resource file Config");

		return content;

	}







/*
	public Object[] readParameterFile(String userFlag) throws IOException {
		// userFlag = value to look for in the first column into the xlsx file

		Object[] tempHeader = null;
		try {
			tempHeader = readExcel(getValueFromConfig(XmlEnum.ParameterFile), "Profiles", userFlag);

		} catch (IOException e) {
			throw new IOException("Cannot find the Profile Header Configuration File");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tempHeader;

	}


	// read an excel file looking for a row with a number defined by idRowGet in
	// the first cell
	// return an array
	public Object[] readExcel(String fileName, String sheetName, String idRowGet) throws IOException {

		// Create an object of File class to open xlsx file
		File file = new File(fileName);

		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);

		Workbook guru99Workbook = null;

		// Find the file extension by splitting file name in substring and
		// getting only extension name
		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		// Check condition if the file is xlsx file
		if (fileExtensionName.equals(".xlsx")) {

			// If it is xlsx file then create object of XSSFWorkbook class
			guru99Workbook = new XSSFWorkbook(inputStream);

		}

		// Check condition if the file is xls file
		else if (fileExtensionName.equals(".xls")) {

			// If it is xls file then create object of XSSFWorkbook class
			guru99Workbook = new HSSFWorkbook(inputStream);

		}

		// Read sheet inside the workbook by its name
		org.apache.poi.ss.usermodel.Sheet guru99Sheet = guru99Workbook.getSheet(sheetName);

		// Find number of rows in excel file
		int rowCount = guru99Sheet.getLastRowNum() - guru99Sheet.getFirstRowNum();

		// Object[] tempHeader = {null,null,null,null};
		Object[] tempHeader = new Object[100];

		// Create a loop over all the rows of excel file to read it
		for (int i = 0; i < rowCount + 1; i++) {

			Row row = guru99Sheet.getRow(i);

			// Create a loop to print cell values in a row

			for (int j = 0; j < row.getLastCellNum(); j++) {

				// Print Excel data in console

				System.out.print(row.getCell(j).getStringCellValue());

				if (row.getCell(0).getStringCellValue().compareTo(idRowGet) == 0 && j > 0) {
					tempHeader[j - 1] = row.getCell(j).getStringCellValue();
				}
			}

			System.out.println();

		}

		return tempHeader;

	}
	*/
}

