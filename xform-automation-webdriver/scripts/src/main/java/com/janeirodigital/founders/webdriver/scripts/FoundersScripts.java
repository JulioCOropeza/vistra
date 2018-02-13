package com.janeirodigital.founders.webdriver.scripts;


import com.janeirodigital.xform.webdriver.actions.LogActions;
import com.janeirodigital.xform.webdriver.actions.UserManagementActions;
import com.janeirodigital.xform.webdriver.common.Common;
import com.janeirodigital.xform.webdriver.common.Environment;
import com.janeirodigital.xform.webdriver.common.Initial;
import com.janeirodigital.xform.webdriver.enums.BrowsersEnum;
import com.janeirodigital.xform.webdriver.enums.XmlEnum;
import com.janeirodigital.xform.webdriver.objectRepository.CasesReaderDataProvider;
import com.janeirodigital.xform.webdriver.objectRepository.UserManagementTCData;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

public class FoundersScripts {

    public Common common;
    private LogActions actions;
    public Initial init;
    private CasesReaderDataProvider testDataProvider;
    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(FoundersScripts.class);
    private Environment testEnvironment;

    public FoundersScripts() {
    }

    @Test
    @Parameters({"browser","environment"})
    public FoundersScripts(@Optional("CHROME_WIN") String browser, @Optional("dev") String environment ) {
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

    @BeforeClass
    @Parameters({"environment"})
    public void BeforeClass(@Optional("dev") String environment) {
        try {
            common.OpenBaseURL(testEnvironment.urlMiddleOffice());
            String activeRowIndicator = "1"; // flag to look for into the .xls file

            Object[] initParameters = common.readParameterFile(activeRowIndicator, "Parameters");//login info is get from xls file
            actions.login(initParameters);
        } catch (Exception e) {
            logger.error("BeforeClass configuration activities has failed: {} ", e.getMessage());
        }
    }

    @AfterClass
    public void AfterClass() {
        common.closeBrowser();
    }


    @Test(dataProvider = "populateDataProviders")
    public void testXFormUserManagement(UserManagementTCData tcData){
        UserManagementActions userMgmntAct = new UserManagementActions(driver);

        userMgmntAct.addNewUser(tcData);

        logger.info("you have provided FilterByTenant as:: {} ", tcData.getFilterByTenant().toString());
        logger.info("you have provided FilterByRole as:: {} ", tcData.getFilterByRole().toString());

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


