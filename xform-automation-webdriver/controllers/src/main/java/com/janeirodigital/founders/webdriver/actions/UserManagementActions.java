package com.janeirodigital.founders.webdriver.actions;

import com.janeirodigital.xform.webdriver.actions.LogActions;
import com.janeirodigital.xform.webdriver.actions.MainMenuActions;
import com.janeirodigital.xform.webdriver.common.Common;
import com.janeirodigital.xform.webdriver.enums.CommonEnum;
import com.janeirodigital.xform.webdriver.objectRepository.UserManagementTCData;
import com.janeirodigital.xform.webdriver.objectRepository.XFormDashBoard;
import com.janeirodigital.founders.webdriver.objectRepository.FoundersUserManagement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserManagementActions {
    private Common common;
    private FoundersUserManagement fUserMngmnt;
    private MainMenuActions mainMenuActions;
    private XFormDashBoard xFormDashboard;
    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(LogActions.class);

    public UserManagementActions(WebDriver driver) {
        common = new Common(driver);
             this.driver = driver;
        fUserMngmnt = new FoundersUserManagement();
        mainMenuActions = new MainMenuActions(driver);

        xFormDashboard = new XFormDashBoard();
        xFormDashboard.fillMenuHashMap();

    }

    public void addNewUser(UserManagementTCData tcData) {

        mainMenuActions.openMenuOption(
                CommonEnum.PageLoadingTimes.MEDIUM_WAIT_TIME.getValue(),
                xFormDashboard.returnMenuOption(xFormDashboard.leftMenuValues.get(CommonEnum.XFormMenuTitles.X_FORM_DASH_BOARD_MENU_USR_MNGMT_TITLE.toString())),
                CommonEnum.BodyTitles.X_FORM_USER_MANAGEMENT_TITLE.toString()
        );

        common.waitForElementToBeClickable(CommonEnum.PageLoadingTimes.MEDIUM_WAIT_TIME.getValue(), fUserMngmnt.btnAddNewUser);
        try {

            WebDriverWait waitDriver = new WebDriverWait(driver, 1);
            waitDriver.until(ExpectedConditions.presenceOfElementLocated(fUserMngmnt.btnAddNewUser));
            WebElement element = driver.findElement(fUserMngmnt.btnAddNewUser);
            Thread.sleep(1000);//Time to wait for me
            element.click();

        } catch (Exception e) {

        }

        driver.findElement(fUserMngmnt.txtFirstName).click();
        driver.findElement(fUserMngmnt.txtFirstName).sendKeys(tcData.getFirstName());
        driver.findElement(fUserMngmnt.txtLastName).sendKeys(tcData.getLastName());
        driver.findElement(fUserMngmnt.txtJobTitle).sendKeys(tcData.getJobTitle());
        driver.findElement(fUserMngmnt.txtEmail).sendKeys(tcData.getEmail());
        driver.findElement(fUserMngmnt.txtConfirmEmail).sendKeys(tcData.getConfirmEmail());
        if (Boolean.valueOf(tcData.getCheckSystemAdministrator())) {
            driver.findElement(fUserMngmnt.chkSystemAdminitrator).click();
        }
        driver.findElement(fUserMngmnt.cmbRole).sendKeys(tcData.getRole());
        driver.findElement(fUserMngmnt.btnCreateNewUser).click();

        try {
            common.OpenURL(CommonEnum.PagesURLs.FoundersDashBoardUrl.toString());
        } catch (Exception e) {
            logger.error("test has failed before redirection: {} ", e.getMessage());
        }
    }
}
