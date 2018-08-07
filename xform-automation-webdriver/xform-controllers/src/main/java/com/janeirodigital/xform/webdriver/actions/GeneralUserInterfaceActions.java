package com.janeirodigital.xform.webdriver.actions;

import com.janeirodigital.xform.webdriver.enums.CommonEnum;
import com.janeirodigital.xform.webdriver.common.Common;
import com.janeirodigital.xform.webdriver.objectRepository.selectors.GeneralUserInterfaceSelectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GeneralUserInterfaceActions {
    private Common common;
    private GeneralUserInterfaceSelectors xFormDashboard;
    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(AccessAndSecurityActions.class);

    public GeneralUserInterfaceActions(WebDriver driver) {
        common = new Common(driver);
        this.driver = driver;
        xFormDashboard = new GeneralUserInterfaceSelectors();
        xFormDashboard.fillMenuHashMap();
    }

    /**
     * This method opens the main menu Options, by waiting the specified time in waitTime
     * and finding the specified selector, then finally waiting for the title of that opened menu
     * that it's specified in the CommonEnum.BodyTitles
     * EXAMPLE:
     * public void openUserManagement() {
     * openMenuOption(
     * CommonEnum.PageLoadingTimes.SHORT_WAIT_TIME.getValue(),
     * xFormDashboard.returnMenuOption(xFormDashboard.leftMenuValues.get(CommonEnum.XFormMenuTitles.X_FORM_DASH_BOARD_MENU_USR_MNGMT_TITLE.toString())),
     * CommonEnum.BodyTitles.X_FORM_USER_MANAGEMENT_TITLE.toString()
     * );
     * }
     **/

    public void openMenuOption(int waitTime, By selector, String menuOption, By byXFormDashboardSelector) {
        common.waitForElementToBeClickable(waitTime, selector);
        common.clickUsingJavaScript(selector);
    }

    /**
     * Validate the Menu Structure for the web site is working properly
     * all the menu elements will be checked
     */

    public void readNavigationFlow() {
        // Validate Left Option Menu User Management
        validateMenuWebElement(
                CommonEnum.XFormMenuTitles.X_FORM_DASH_BOARD_MENU_USR_MNGMT_TITLE,
                CommonEnum.BodyTitles.X_FORM_USER_MANAGEMENT_TITLE,
                xFormDashboard.usrManagementTitle);
        // Validate Left Option Menu Tenants
        validateMenuWebElement(
                CommonEnum.XFormMenuTitles.X_FORM_DASH_BOARD_MENU_TENANT_TITLE,
                CommonEnum.BodyTitles.X_FORM_TENANT_MANAGEMENT_TITLE,
                xFormDashboard.tenantsTitle);
        // Validate Left Option Menu System Administration and Submenu Default Roles
        validateMenuWebElement(
                CommonEnum.XFormMenuTitles.X_FORM_DASH_BOARD_MENU_SYS_ADM_TITLE,
                CommonEnum.BodyTitles.X_FORM_DEFAULT_ROLES_TITLE,
                xFormDashboard.defaultRolesTitle);
        // Validate Left Option Sub Menu Default Logo & Styles
        validateMenuWebElement(
                CommonEnum.XFormMenuTitles.X_FORM_DASH_BOARD_MENU_SA_SUB_MENU_DEFAULT_LOGO_STYLES,
                CommonEnum.BodyTitles.X_FORM_DEFAULT_LOGO_STYLES_TITLE,
                xFormDashboard.defaultLogoStylesTitle);
        // Validate Left Option Sub Menu API Documents
//        validateMenuWebElement(
//                CommonEnum.XFormMenuTitles.X_FORM_DASH_BOARD_MENU_SA_SUB_MENU_API_DOCUMENTS,
//                CommonEnum.BodyTitles.X_FORM_API_DOCUMENTS_TITLE,
//                xFormDashboard.apiDocumentsTitle);
        // Validate Left Option Sub Menu About
        validateMenuWebElement(
                CommonEnum.XFormMenuTitles.X_FORM_DASH_BOARD_MENU_SA_SUB_MENU_ABOUT,
                CommonEnum.BodyTitles.X_FORM_ABOUT_TITLE,
                xFormDashboard.aboutTitle);
    }

    /**
     * Validate the Menu Structure for the web site is working properly
     * all the menu elements will be checked
     * @param mnuTitle                 description of the menu item
     * @param bdyTitle                 main title of the page called by clicking the menu item
     * @param byXFormDashboardSelector css selector for the bdyTitle
     */

    public void validateMenuWebElement(CommonEnum.XFormMenuTitles mnuTitle, CommonEnum.BodyTitles bdyTitle, By byXFormDashboardSelector) {

        this.openMenuOption(
                CommonEnum.PageLoadingTimes.MEDIUM_WAIT_TIME.getValue(),
                xFormDashboard.returnMenuOption(xFormDashboard.leftMenuValues.get(mnuTitle.toString())),
                bdyTitle.toString(),
                byXFormDashboardSelector
        );
        common.waitForElementToBeClickable(CommonEnum.PageLoadingTimes.MEDIUM_WAIT_TIME.getValue(), byXFormDashboardSelector);
        if (!common.elementExists(byXFormDashboardSelector)) {
            Assert.fail();
            logger.error("test method --readNavigationFlow-- has failed finding an WebElement: {} ", byXFormDashboardSelector.toString());
        }
    }
}
