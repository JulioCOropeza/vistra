package com.janeirodigital.xform.webdriver.actions;

import com.janeirodigital.xform.webdriver.common.Common;
import com.janeirodigital.xform.webdriver.enums.CommonEnum;
import com.janeirodigital.xform.webdriver.objectRepository.UserManagementTCData;
import com.janeirodigital.xform.webdriver.objectRepository.XFormDashBoard;
import com.janeirodigital.xform.webdriver.objectRepository.XFormUserManagement;
import org.openqa.selenium.WebDriver;
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

        mainMenuActions.openMenuOption(
                CommonEnum.PageLoadingTimes.SHORT_WAIT_TIME.getValue(),
                xFormDashboard.returnMenuOption(xFormDashboard.leftMenuValues.get(CommonEnum.XFormMenuTitles.X_FORM_DASH_BOARD_MENU_USR_MNGMT_TITLE.toString())),
                CommonEnum.BodyTitles.X_FORM_USER_MANAGEMENT_TITLE.toString()
        );

        driver.findElement(xUserMngmnt.btnAddNewUser).click();
        driver.findElement(xUserMngmnt.txtFirstName).click();
        driver.findElement(xUserMngmnt.txtFirstName).sendKeys(tcData.getFirstName());
        driver.findElement(xUserMngmnt.txtLastName).sendKeys(tcData.getLastName());
        driver.findElement(xUserMngmnt.txtJobTitle).sendKeys(tcData.getJobTitle());
        driver.findElement(xUserMngmnt.txtEmail).sendKeys(tcData.getEmail());
        driver.findElement(xUserMngmnt.txtConfirmEmail).sendKeys(tcData.getConfirmEmail());
        if (Boolean.valueOf(tcData.getCheckSystemAdministrator())) {
            driver.findElement(xUserMngmnt.chkSystemAdminitrator).click();
        }

        driver.findElement(xUserMngmnt.cmbTenant).sendKeys(tcData.getComboAccountTenant());
        driver.findElement(xUserMngmnt.cmbRole).sendKeys(tcData.getRole());

        driver.findElement(xUserMngmnt.btnCreateNewUser).click();

        try {
            common.OpenBaseURL(CommonEnum.PagesURLs.FoundersDashBoardUrl.toString());
        } catch (Exception e) {
            logger.error("test has failed before redirection: {} ", e.getMessage());
        }
    }
}
