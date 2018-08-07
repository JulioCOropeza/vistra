package com.janeirodigital.xform.webdriver.scripts;

import com.janeirodigital.xform.webdriver.actions.UserManagementActions;
import com.janeirodigital.xform.webdriver.common.Common;
import com.janeirodigital.xform.webdriver.enums.XmlEnum;
import com.janeirodigital.xform.webdriver.objectRepository.data.CasesReaderDataProvider;
import com.janeirodigital.xform.webdriver.objectRepository.data.UserManagementFileData;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

public class UserManagement {
    private static final Logger logger = LoggerFactory.getLogger(AccessAndSecurity.class);
    private WebDriver driver;
    private Common common;
    private CasesReaderDataProvider testDataProvider;
    UserManagementActions userMgmntAct;

    @BeforeClass(dependsOnGroups = "init", groups = {"userManagement"})
    public void BeforeClass(ITestContext ctx) {
        driver = (WebDriver) ctx.getAttribute("driver");
        common = (Common) ctx.getAttribute("common");
        userMgmntAct = new UserManagementActions(driver);
        logger.info("driver.toString:: {} ", driver.toString());
    }

    /**
     * TC 7.1.1 Create new user - Positive
     * This @Test is in charge to create the users based in the information added in parameterFile.xlsx file.
     * @param tcData
     */

    @Test(dependsOnGroups = "login", groups = {"userManagement", "userCreate"}, priority=1, dataProvider = "populateDataProviders")
    public void userCreate(UserManagementFileData tcData) {
        userMgmntAct.addNewUser(tcData);
    }

    /**
     * TC 7.3.3 Update User Data
     * This @Test is in charge to take an user and modify the data based on the excel file information.
     * @param tcData
     */

    @Test(dependsOnGroups = {"login","userCreate"}, groups = {"userManagement","userUpdate"}, priority=2, dataProvider = "populateDataProviders")
    public void userUpdate(UserManagementFileData tcData) {
        userMgmntAct.userUpdate(tcData);
    }

    /**
     * TC 7.2.1 Read listed info from User
     * This @Test is in charge to read and verify all the user information.
     * @param tcData
     */

    @Test(dependsOnGroups = {"login", "userUpdate"}, groups = {"userManagement", "userRead"}, priority=3, dataProvider = "populateDataProviders")
    public void userRead(UserManagementFileData tcData) {
        userMgmntAct.userRead(tcData);
    }

    /**
     * TC 7.4.1. Delete User
     * This @Test is in charge to delete the user created previously
     * @param tcData
     */

    @Test(dependsOnGroups = {"login", "userRead"}, groups = {"userManagement"}, priority=4, dataProvider = "populateDataProviders")
    public void userDelete(UserManagementFileData tcData) {
        userMgmntAct.userDelete(tcData);
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

    @AfterClass(dependsOnGroups = "login", groups = {"userManagement"})
    public void AfterClass() {
        common.closeBrowser();
    }
}