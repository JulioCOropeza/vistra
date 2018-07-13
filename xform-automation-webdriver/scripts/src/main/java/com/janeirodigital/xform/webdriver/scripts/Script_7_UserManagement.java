package com.janeirodigital.xform.webdriver.scripts;

import com.janeirodigital.xform.webdriver.actions.UserManagementActions;
import com.janeirodigital.xform.webdriver.common.Common;
import com.janeirodigital.xform.webdriver.enums.XmlEnum;
import com.janeirodigital.xform.webdriver.objectRepository.CasesReaderDataProvider;
import com.janeirodigital.xform.webdriver.objectRepository.UserManagementTCData;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

public class Script_7_UserManagement {
    private static final Logger logger = LoggerFactory.getLogger(Scripts.class);
    private WebDriver driver;
    private Common common;
    private CasesReaderDataProvider testDataProvider;

    @BeforeClass(dependsOnGroups = "parent", groups = {"Script_7_UserManagement"})
    public void BeforeClass(ITestContext ctx) {
        driver = (WebDriver) ctx.getAttribute("driver");
        common = (Common) ctx.getAttribute("common");
        logger.info("driver.toString:: {} ", driver.toString());
    }

    /**
     * This @Test is in charge to create the users based in the information added in ParameterFile.xlsx file.
     * @param tcData
     */

    @Test(dependsOnGroups = "parent", groups = {"Script_7_UserManagement"}, priority=1, dataProvider = "populateDataProviders")
    public void userCreate(UserManagementTCData tcData) {
        UserManagementActions userMgmntAct = new UserManagementActions(driver);
        userMgmntAct.addNewUser(tcData);
    }

    /**
     * This Test is in charge to take an user and modify the Job Title.
     * @param tcData
     */

    @Test(dependsOnGroups = "parent", groups = {"Script_7_UserManagement"}, priority=2, dataProvider = "populateDataProviders")
    public void userUpdate(UserManagementTCData tcData) {
        UserManagementActions userMgmntAct = new UserManagementActions(driver);
        userMgmntAct.userUpdate(tcData);
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

    @AfterClass(dependsOnGroups = "parent", groups = {"Script_7_UserManagement"})
    public void AfterClass() {
        common.closeBrowser();
    }
}