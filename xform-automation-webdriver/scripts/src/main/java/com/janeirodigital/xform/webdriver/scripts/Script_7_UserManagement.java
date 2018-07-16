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
    UserManagementActions userMgmntAct;

    @BeforeClass(dependsOnGroups = "parent", groups = {"Script_7_UserManagement"})
    public void BeforeClass(ITestContext ctx) {
        driver = (WebDriver) ctx.getAttribute("driver");
        common = (Common) ctx.getAttribute("common");
        userMgmntAct = new UserManagementActions(driver);
        logger.info("driver.toString:: {} ", driver.toString());
    }

    /**
     * This @Test is in charge to create the users based in the information added in ParameterFile.xlsx file.
     * @param tcData
     */

    @Test(dependsOnGroups = "parent", groups = {"Script_7_UserManagement", "userCreate"}, priority=1, dataProvider = "populateDataProviders")
    public void userCreate(UserManagementTCData tcData) {
        userMgmntAct.addNewUser(tcData);
    }

    /**
     * This Test is in charge to take an user and modify the data based on the excel file information.
     * @param tcData
     */

    @Test(dependsOnGroups = {"parent","userCreate"}, groups = {"Script_7_UserManagement","userUpdate"}, priority=2, dataProvider = "populateDataProviders")
    public void userUpdate(UserManagementTCData tcData) {
        userMgmntAct.userUpdate(tcData);
    }

    /**
     * This @Test is in charge to read and verify all the user information.
     * @param tcData
     */

    @Test(dependsOnGroups = {"parent", "userUpdate"}, groups = {"Script_7_UserManagement", "userRead"}, priority=3, dataProvider = "populateDataProviders")
    public void userRead(UserManagementTCData tcData) {
        userMgmntAct.userRead(tcData);
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