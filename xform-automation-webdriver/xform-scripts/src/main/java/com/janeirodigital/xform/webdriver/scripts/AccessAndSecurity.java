package com.janeirodigital.xform.webdriver.scripts;


import com.janeirodigital.xform.webdriver.actions.AccessAndSecurityActions;
import com.janeirodigital.xform.webdriver.common.Common;
import com.janeirodigital.xform.webdriver.enums.XmlEnum;
import com.janeirodigital.xform.webdriver.objectRepository.data.AccessAndSecurityFileData;
import com.janeirodigital.xform.webdriver.objectRepository.data.CasesReaderDataProvider;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccessAndSecurity {
    public Common common;
    public Initializer init;
    private CasesReaderDataProvider testDataProvider;
    private static final Logger logger = LoggerFactory.getLogger(AccessAndSecurity.class);
    WebDriver driver;
    AccessAndSecurityActions actions;

    public AccessAndSecurity() { }

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass (groups = "login", dependsOnGroups = "init")
    @Parameters({"browser", "environment"})
    public void BeforeClass(@Optional("CHROME_WIN") String browser, @Optional("dev") String environment,ITestContext context) {
        driver = (WebDriver) context.getAttribute("driver");
        common = (Common) context.getAttribute("common");
        actions = new AccessAndSecurityActions(driver);
    }

    /**
     * TC 4.1.1. Login - Positive
     * Login an user into the system using positive values
     * NOTE: This TC must be used specifically for scenarios were is necessary Login many times
     */

    @Test(groups = {"loginPositive","login"}, priority=1, dataProvider = "populateDataProviders")
    public void loginPositive(AccessAndSecurityFileData tcdata) {
        actions.login(tcdata);
    }

    @DataProvider
    public Object[][] populateDataProviders() {
        String ParameterFile = "";
        Object[][] tempData = null;
        try {
            ParameterFile = common.getValueFromConfig(XmlEnum.PARAMETER_FILE.getTagName());
            testDataProvider = new CasesReaderDataProvider();
            tempData = testDataProvider.getDataAccessAndSecurity(ParameterFile);
        } catch (Exception ex) {
            logger.error("Error loading DataProvider excel file");
            Assert.fail(ex.getMessage());
        }
        return tempData;
    }

    @AfterClass(dependsOnGroups = "login", groups = {"accessAndSecurity"})
    public void AfterClass() {
        common.closeBrowser();
    }
}


