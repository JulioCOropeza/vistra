package com.janeirodigital.xform.webdriver.scripts;

import com.janeirodigital.xform.webdriver.actions.GeneralUserInterfaceActions;
import com.janeirodigital.xform.webdriver.common.Common;
import com.janeirodigital.xform.webdriver.enums.XmlEnum;
import com.janeirodigital.xform.webdriver.objectRepository.data.CasesReaderDataProvider;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;

public class GeneralUserInterface {
    private static final Logger logger = LoggerFactory.getLogger(AccessAndSecurity.class);
    private WebDriver driver;
    private Common common;
    private CasesReaderDataProvider testDataProvider;

    public GeneralUserInterface() {

    }
    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass (dependsOnGroups = "init", groups = "generalUserInterface")
    public void BeforeClass(ITestContext ctx) {
        driver = (WebDriver) ctx.getAttribute("driver");
        common = (Common) ctx.getAttribute("common");
        logger.info("driver.toString:: {} ", driver.toString());
    }

    /**
     * TC 2.2.1. Read navigation flow
     */

    @Test(dependsOnGroups = "login", groups = "generalUserInterface", priority=1)
    public void readNavigationFlow() {
        GeneralUserInterfaceActions menuActions = new GeneralUserInterfaceActions(driver);
        menuActions.readNavigationFlow();
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

    @AfterClass(dependsOnGroups = "login", groups = {"generalUserInterface"})
    public void AfterClass() {
        common.closeBrowser();
    }
}
