package com.janeirodigital.xform.webdriver.actions;

import com.janeirodigital.xform.webdriver.common.Common;
import com.janeirodigital.xform.webdriver.enums.CommonEnum;
import com.janeirodigital.xform.webdriver.objectRepository.UserManagementTCData;
import com.janeirodigital.xform.webdriver.objectRepository.XFormDashBoard;
import com.janeirodigital.xform.webdriver.objectRepository.XFormUserManagement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserManagementActions {
    private Common common;
    private XFormUserManagement xUserMngmnt;
    private MainMenuActions mainMenuActions;
    private XFormDashBoard xFormDashboard;
    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(LogActions.class);

    public UserManagementActions(WebDriver driver) {
        common = new Common(driver);
        this.driver = driver;
        xUserMngmnt = new XFormUserManagement();
        mainMenuActions = new MainMenuActions(driver);

        xFormDashboard = new XFormDashBoard();
        xFormDashboard.fillMenuHashMap();
    }

    public void addNewUser(UserManagementTCData tcData) {
        int iNumber = common.getRandomValue(CommonEnum.randomRange.MAX_RANGE.getValue());
        String Email = common.emailGenerator(tcData.getEmail(),iNumber);
        String ConfirmEmail = common.emailGenerator(tcData.getConfirmEmail(),iNumber);

        mainMenuActions.openMenuOption(
                CommonEnum.PageLoadingTimes.SHORT_WAIT_TIME.getValue(),
                xFormDashboard.returnMenuOption(xFormDashboard.leftMenuValues.get(CommonEnum.XFormMenuTitles.X_FORM_DASH_BOARD_MENU_USR_MNGMT_TITLE.toString())),
                CommonEnum.BodyTitles.X_FORM_USER_MANAGEMENT_TITLE.toString(),
                xFormDashboard.usrManagementTitle
        );

        try {
            WebDriverWait waitDriver = new WebDriverWait(driver, 1);
            waitDriver.until(ExpectedConditions.presenceOfElementLocated(xUserMngmnt.btnAddNewUser));
            Thread.sleep(1000);

        } catch (Exception e) {
            logger.error("test has failed locating xUserMngmnt.btnAddNewUser: {} ", e.getMessage());
        }

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

        common.waitForElementToBeClickable(2, xUserMngmnt.btnCloseBanner);
        driver.findElement(xUserMngmnt.btnCloseBanner).click();

        try {
            common.OpenBaseURL(CommonEnum.PagesURLs.X_FORM_DASH_BOARD_URL.toString());
        } catch (Exception e) {
            logger.error("test has failed before redirection: {} ", e.getMessage());
        }
    }
}