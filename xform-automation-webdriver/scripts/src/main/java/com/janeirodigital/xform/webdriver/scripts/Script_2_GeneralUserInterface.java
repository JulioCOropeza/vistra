package com.janeirodigital.xform.webdriver.scripts;

import com.janeirodigital.founders.webdriver.actions.UserManagementActions;
import com.janeirodigital.xform.webdriver.common.Common;
import com.janeirodigital.xform.webdriver.enums.XmlEnum;
import com.janeirodigital.xform.webdriver.objectRepository.CasesReaderDataProvider;
import com.janeirodigital.xform.webdriver.objectRepository.UserManagementTCData;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;

public class Script_2_GeneralUserInterface {
    private static final Logger logger = LoggerFactory.getLogger(Scripts.class);
    private WebDriver driver;
    private Common common;
    private CasesReaderDataProvider testDataProvider;

    public Script_2_GeneralUserInterface() {

    }
    @BeforeClass (dependsOnGroups = "parent", groups = "Script_2_GeneralUserInterface")
    public void BeforeClass(ITestContext ctx) {

        driver = (WebDriver) ctx.getAttribute("driver");
        common = (Common) ctx.getAttribute("common");

        logger.info("driver.toString:: {} ", driver.toString());

    }

    @Test(dependsOnGroups = "parent", groups = "Script_2_GeneralUserInterface", priority=1, dataProvider = "populateDataProviders")
    public void testXFormUserManagement(UserManagementTCData tcData) {
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
