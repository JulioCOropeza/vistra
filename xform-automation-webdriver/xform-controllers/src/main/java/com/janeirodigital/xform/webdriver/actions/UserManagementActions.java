package com.janeirodigital.xform.webdriver.actions;

import com.janeirodigital.xform.webdriver.common.Common;
import com.janeirodigital.xform.webdriver.enums.CommonEnum;
import com.janeirodigital.xform.webdriver.objectRepository.data.UserManagementFileData;
import com.janeirodigital.xform.webdriver.objectRepository.selectors.GeneralUserInterfaceSelectors;
import com.janeirodigital.xform.webdriver.objectRepository.selectors.UserManagementSelectors;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import java.util.List;

public class UserManagementActions {
    private Common common;
    private UserManagementSelectors xUserMngmnt;
    private GeneralUserInterfaceActions generalUserInterfaceActions;
    private GeneralUserInterfaceSelectors xFormDashboard;
    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(UserManagementActions.class);

    public UserManagementActions(WebDriver driver) {
        common = new Common(driver);
        this.driver = driver;
        xUserMngmnt = new UserManagementSelectors();
        generalUserInterfaceActions = new GeneralUserInterfaceActions(driver);
        xFormDashboard = new GeneralUserInterfaceSelectors();
        xFormDashboard.fillMenuHashMap();
    }

    public void addNewUser(UserManagementFileData tcData) {
        int iNumber = common.getRandomValue(CommonEnum.randomRange.MAX_RANGE.getValue());
        String Email = common.emailGenerator(tcData.getEmail(),iNumber);
        String ConfirmEmail = common.emailGenerator(tcData.getConfirmEmail(),iNumber);

        generalUserInterfaceActions.openMenuOption(
                CommonEnum.PageLoadingTimes.SHORT_WAIT_TIME.getValue(),
                xFormDashboard.returnMenuOption(xFormDashboard.leftMenuValues.get(CommonEnum.XFormMenuTitles.X_FORM_DASH_BOARD_MENU_USR_MNGMT_TITLE.toString())),
                CommonEnum.BodyTitles.X_FORM_USER_MANAGEMENT_TITLE.toString(),
                xFormDashboard.usrManagementTitle
        );

        common.waitForPresenceOfElement(1, xUserMngmnt.btnAddNewUser);
        common.scrollTop();
        driver.findElement(xUserMngmnt.btnAddNewUser).click();
        driver.findElement(xUserMngmnt.txtFirstName).click();
        driver.findElement(xUserMngmnt.txtFirstName).sendKeys(tcData.getFirstName());
        driver.findElement(xUserMngmnt.txtLastName).sendKeys(tcData.getLastName());
        driver.findElement(xUserMngmnt.txtJobTitle).sendKeys(tcData.getJobTitle());
        driver.findElement(xUserMngmnt.txtEmail).sendKeys(Email);
        driver.findElement(xUserMngmnt.txtConfirmEmail).sendKeys(ConfirmEmail);
        if (Boolean.valueOf(tcData.getCheckSystemAdministrator())) {
            driver.findElement(xUserMngmnt.chkSystemAdminitrator).click();
        }

        driver.findElement(xUserMngmnt.cmbTenant).click();
        driver.findElement(xUserMngmnt.cmbTenant).sendKeys(tcData.getComboAccountTenant()+ Keys.ENTER);
        common.waitForElementToBeClickable(2, xUserMngmnt.cmbRole);
        driver.findElement(xUserMngmnt.cmbRole).click();
        driver.findElement(xUserMngmnt.cmbRole).sendKeys(tcData.getRole());
        driver.findElement(xUserMngmnt.btnCreateNewUser).click();
        common.scrollTop();
    }

    public void userUpdate (UserManagementFileData tcData) {
        driver.get(CommonEnum.PagesURLs.X_FORM_USER_LIST_URL.toString());
        common.waitForPresenceOfElement(1, xUserMngmnt.txtQuickSearch);
        common.fillInput(xUserMngmnt.txtQuickSearch,tcData.getFirstName());
        common.waitForElementToBeClickable(1, xUserMngmnt.btntFilterQuickSearch);
        driver.findElement(xUserMngmnt.btntFilterQuickSearch).click();
        common.waitForPresenceOfElement(1, xUserMngmnt.txtUsersList);
        common.getListLastElement(xUserMngmnt.txtUsersList).click();
        common.waitForElementToBeClickable(2, xUserMngmnt.btnEditUserInfo);
        driver.findElement(xUserMngmnt.btnEditUserInfo).click();
        common.fillInput(xUserMngmnt.txtFirstName, tcData.sFirstNameChange());
        common.fillInput(xUserMngmnt.txtLastName, tcData.sLastNameChange());
        common.fillInput(xUserMngmnt.txtJobField,tcData.sJobTitleChange() );
        driver.findElement(xUserMngmnt.btnSaveInfo).click();
    }

    public void userRead(UserManagementFileData tcData){
        driver.get(CommonEnum.PagesURLs.X_FORM_USER_LIST_URL.toString());
        common.waitForPresenceOfElement(1, xUserMngmnt.txtQuickSearch);
        common.fillInput(xUserMngmnt.txtQuickSearch,tcData.sFirstNameChange());
        common.waitForPresenceOfElement(1, xUserMngmnt.btntFilterQuickSearch);
        driver.findElement(xUserMngmnt.btntFilterQuickSearch).click();
        common.waitForPresenceOfElement(1, xUserMngmnt.txtUsersList);
        common.getListLastElement(xUserMngmnt.txtUsersList).click();
        common.waitForElementToBeClickable(2, xUserMngmnt.btnEditUserInfo);
        List<WebElement> currentData = common.getListOfElement(xUserMngmnt.txtUserCurrentData);

        String sCurrentMail = currentData.get(2).getAttribute("innerHTML");
        String [] sExpectedMail = sCurrentMail.split("@");
        String [] sExpectedDomain = sExpectedMail[1].split("\\.");
        String sEmailToVerify = sExpectedMail[0] + "@" + sExpectedDomain[1] + ".com";

        Assert.assertEquals(currentData.get(0).getAttribute("innerHTML"),tcData.sFirstNameChange());
        Assert.assertEquals(currentData.get(1).getAttribute("innerHTML"),tcData.sLastNameChange());
        Assert.assertEquals(sEmailToVerify,tcData.getEmail());
        Assert.assertEquals(currentData.get(3).getAttribute("innerHTML"),tcData.sJobTitleChange());
    }

    public void userDelete(UserManagementFileData tcData){
        driver.get(CommonEnum.PagesURLs.X_FORM_USER_LIST_URL.toString());
        common.waitForPresenceOfElement(1, xUserMngmnt.txtQuickSearch);
        common.fillInput(xUserMngmnt.txtQuickSearch,tcData.sFirstNameChange() );
        common.waitForElementToBeClickable(1, xUserMngmnt.btntFilterQuickSearch);
        driver.findElement(xUserMngmnt.btntFilterQuickSearch).click();
        common.waitForPresenceOfElement(2, xUserMngmnt.txtUsersList);
        common.getListLastElement (xUserMngmnt.btnDeleteUser).click();
        common.waitForElementToBeClickable(1, xUserMngmnt.btnConfirmDelete);
        driver.findElement(xUserMngmnt.btnConfirmDelete).click();
        common.waitForPresenceOfElement(1,xUserMngmnt.btntFilterQuickSearch );
        common.fillInput(xUserMngmnt.txtQuickSearch, tcData.sFirstNameChange());
        common.waitForElementToBeClickable(1, xUserMngmnt.btntFilterQuickSearch);
        driver.findElement(xUserMngmnt.btntFilterQuickSearch).click();
        common.verifyElementDisplayed(xUserMngmnt.txtNoContentMsg);
    }
}