package com.janeirodigital.xform.webdriver.objectRepository.selectors;

import org.apache.commons.lang3.ObjectUtils;
import org.openqa.selenium.By;
import java.util.HashMap;
import com.janeirodigital.xform.webdriver.enums.CommonEnum;

public class GeneralUserInterfaceSelectors {
    public final By bodyTitle = By.cssSelector("xform-page h1");
    public final By componentTitle = By.cssSelector(".list-component h1");
    public final By usrManagementTitle = By.cssSelector("xform-users-list h1");
    public final By tenantsTitle = By.cssSelector("xform-tenants-list h1");
    public final By defaultRolesTitle = By.cssSelector("xform-default-roles h1");
    public final By defaultLogoStylesTitle = By.cssSelector("xform-default-styles h1.border-bottom-2");
    public final By apiDocumentsTitle = By.cssSelector(".info .info_title");
    public final By aboutTitle = By.cssSelector("xform-about h1.border-bottom-2");

    public HashMap<String, String> leftMenuValues = new HashMap<>();

    public void fillMenuHashMap () {

        leftMenuValues.put(CommonEnum.XFormMenuTitles.X_FORM_DASH_BOARD_MENU_USR_MNGMT_TITLE.toString(), CommonEnum.XFormMenuTitlesIDs.X_FORM_DASH_BOARD_MENU_USR_MNGMT_ID.toString());
        leftMenuValues.put(CommonEnum.XFormMenuTitles.X_FORM_DASH_BOARD_MENU_TENANT_TITLE.toString(), CommonEnum.XFormMenuTitlesIDs.X_FORM_DASH_BOARD_MENU_TENANT_ID.toString());
        leftMenuValues.put(CommonEnum.XFormMenuTitles.X_FORM_DASH_BOARD_MENU_SYS_ADM_TITLE.toString(), CommonEnum.XFormMenuTitlesIDs.X_FORM_DASH_BOARD_MENU_SYS_ADM_ID.toString());
        leftMenuValues.put(CommonEnum.XFormMenuTitles.X_FORM_DASH_BOARD_MENU_SA_SUB_MENU_DEFAULT_ROLES.toString(), CommonEnum.XFormMenuTitlesIDs.X_FORM_DASH_BOARD_MENU_SA_SUB_MENU_DEFAULT_ROLES_ID.toString());
        leftMenuValues.put(CommonEnum.XFormMenuTitles.X_FORM_DASH_BOARD_MENU_SA_SUB_MENU_DEFAULT_LOGO_STYLES.toString(), CommonEnum.XFormMenuTitlesIDs.X_FORM_DASH_BOARD_MENU_SA_SUB_MENU_DEFAULT_LOGO_STYLES_ID.toString());
        leftMenuValues.put(CommonEnum.XFormMenuTitles.X_FORM_DASH_BOARD_MENU_SA_SUB_MENU_API_DOCUMENTS.toString(), CommonEnum.XFormMenuTitlesIDs.X_FORM_DASH_BOARD_MENU_SA_SUB_MENU_API_DOCUMENTS_ID.toString());
        leftMenuValues.put(CommonEnum.XFormMenuTitles.X_FORM_DASH_BOARD_MENU_SA_SUB_MENU_ABOUT.toString(), CommonEnum.XFormMenuTitlesIDs.X_FORM_DASH_BOARD_MENU_SA_SUB_MENU_ABOUT_ID.toString());
    }

    public By returnMenuOption (String menuValue){
        return By.cssSelector(".main-sidebar [role='menuitem']"+menuValue);
    }

}