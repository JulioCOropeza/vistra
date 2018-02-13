package com.janeirodigital.xform.webdriver.scripts;


import com.janeirodigital.xform.webdriver.actions.LogActions;
import com.janeirodigital.founders.webdriver.actions.UserManagementActions;
import com.janeirodigital.xform.webdriver.common.Common;
import com.janeirodigital.xform.webdriver.common.Environment;
import com.janeirodigital.xform.webdriver.enums.BrowsersEnum;
import com.janeirodigital.xform.webdriver.objectRepository.CasesReaderDataProvider;
import com.janeirodigital.xform.webdriver.common.Initial;
import com.janeirodigital.xform.webdriver.enums.XmlEnum;
import com.janeirodigital.xform.webdriver.objectRepository.UserManagementTCData;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Scripts {

    public Common common;
    private LogActions actions;
    public Initial init;
    private CasesReaderDataProvider testDataProvider;
    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(Scripts.class);
    private Environment testEnvironment;

    public Scripts() {
    }

    @Test
    @Parameters({"browser", "environment"})
    public Scripts(@Optional("CHROME_WIN") String browser, @Optional("dev") String environment) {
        try {
            init = new Initial();

            ConfigFactory.setProperty("env", environment);
            testEnvironment = ConfigFactory.create(Environment.class);
            init.setTestEnvironment(testEnvironment);

            BrowsersEnum browserEnum = BrowsersEnum.valueOf(browser);
            driver = init.getDriver(browserEnum);

            common = new Common(driver);
            actions = new LogActions(driver);

            common.setTestEnvironment(testEnvironment);

        } catch (Exception e) {
            logger.error("Initial configuration activities has failed: {} ", e.getMessage());
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass (groups = "parent")
    @Parameters({"environment"})
    public void BeforeClass(ITestContext context, @Optional("dev") String environment) {
        try {
            context.setAttribute("driver", getDriver()); //Current driver to be shared among Tests under execution
            context.setAttribute("common", common); //Current common functions to be shared among Tests under execution

            common.OpenBaseURL(testEnvironment.urlMiddleOffice());
            String activeRowIndicator = "1"; // flag to look for into the .xls file

            Object[] initParameters = common.readParameterFile(activeRowIndicator, "Parameters");//login info is get from xls file
            actions.login(initParameters);
        } catch (Exception e) {
            logger.error("BeforeClass configuration activities has failed: {} ", e.getMessage());
        }
    }

    // ---------------------------------------
    // 4. Access and Security Testing
    // https://janeirodigital.atlassian.net/wiki/spaces/FORCE/pages/270270562/4.+Access+and+Security+Testing
    // ---------------------------------------
    /**
     * Login an user into the system using positive values
     * NOTE: This TC must be used specifically for scenarios were is necessary Login many times
     *
     * TC 4.1.1. Login - Positive
     * https://janeirodigital.atlassian.net/wiki/spaces/FORCE/pages/270270625/TC+4.1.1.+Login+-+Positive
     */
    @Test(groups = "parent")
    public void Login_Positive() {

    }

    @AfterClass
    public void AfterClass() {
        common.closeBrowser();
    }




}


