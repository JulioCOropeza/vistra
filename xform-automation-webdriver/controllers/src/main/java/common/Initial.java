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

}

