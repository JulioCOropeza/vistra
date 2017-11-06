package com.janeirodigital.xform.webdriver.scripts;


import com.janeirodigital.xform.webdriver.actions.LogActions;
import com.janeirodigital.xform.webdriver.common.Common;
import com.janeirodigital.xform.webdriver.common.Environment;
import com.janeirodigital.xform.webdriver.enums.BrowsersEnum;
import com.janeirodigital.xform.webdriver.objectRepository.CasesReaderDataProvider;
import com.janeirodigital.xform.webdriver.common.Initial;
import com.janeirodigital.xform.webdriver.enums.XmlEnum;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Scripts {

    private Common common;
    private LogActions actions;
    public Initial init;
    private CasesReaderDataProvider testDataProvider;
    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(Scripts.class);
    private Environment testEnvironment;

    public Scripts() {
    }

    @Test
    @Parameters({"browser","environment"})
    public Scripts(@Optional("PHANTOM_JS_WIN") String browser, @Optional("dev") String environment ) {
        try {
            init = new Initial();

            ConfigFactory.setProperty("env", environment);
            testEnvironment = ConfigFactory.create(Environment.class);
            init.setTestEnvironment(testEnvironment);

            BrowsersEnum browserEnum = BrowsersEnum.valueOf(browser);
            driver = init.getDriver(browserEnum);

            common = new Common(driver);
            actions = new LogActions(driver);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    @Parameters({"environment"})
    public void BeforeClass(@Optional("dev") String environment) {
        try {
            ConfigFactory.setProperty("env", environment);
            testEnvironment = ConfigFactory.create(Environment.class);
            init.setTestEnvironment(testEnvironment);

            common.OpenBaseURL(testEnvironment.url());
            String activeRowIndicator = "1"; // flag to look for into the .xls file

            Object[] initParameters = common.readParameterFile(activeRowIndicator, "Parameters");//login info is get from xls file
            actions.login(initParameters);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @AfterClass
    public void AfterClass() {
        common.closeBrowser();
    }


    @Test(dataProvider = "populateDataProviders")
    public void testXFormUserManagement(String Id, String FilterByTenant, String FilterByRole, String QuickSearch,
                                        String AddFirstName, String LastName, String JobTitle, String Email,
                                        String ConfirmEmail, String CheckSystemAdministrator,
                                        String ComboAccountTenant, String Role, String CheckTenantAdministrator) {

        logger.debug("you have provided FilterByTenant as:: ", FilterByTenant);
        logger.debug("you have provided FilterByRole as:: ", FilterByRole);

    }

    @DataProvider
    public Object[][] populateDataProviders() {

        String ParameterFile = "";
        Object[][] tempData = null;
        try {
            ParameterFile = common.getValueFromConfig(XmlEnum.PARAMETER_FILE.getTagName());
            testDataProvider = new CasesReaderDataProvider();
            tempData = testDataProvider.getDataUserManagement(ParameterFile);
        } catch (Exception ex) {
            logger.error("Error loading DataProvider excel file");
            Assert.fail(ex.getMessage());
        }
        return tempData;
    }

}


