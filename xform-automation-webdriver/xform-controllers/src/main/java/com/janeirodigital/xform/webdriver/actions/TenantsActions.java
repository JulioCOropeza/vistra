package com.janeirodigital.xform.webdriver.actions;

import com.janeirodigital.xform.webdriver.common.Common;
import com.janeirodigital.xform.webdriver.objectRepository.data.TenantsFileData;
import com.janeirodigital.xform.webdriver.objectRepository.selectors.TenantsSelectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class TenantsActions {
    private Common common;
    private TenantsSelectors selectors;
    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(TenantsActions.class);

    public TenantsActions(WebDriver driver) {
        common = new Common(driver);
        this.driver = driver;
        selectors = new TenantsSelectors();
    }

    public void tenantOverviewRead(TenantsFileData tcdata){
        common.findAndClick(selectors.btnAddTenant);
        List<WebElement> tenantManagementTitles = common.getListOfElement(selectors.txtTenantManagementTitles);
        common.textCompareFromElement(tenantManagementTitles.get(1),tcdata.getsActiveTenants().toLowerCase());
        common.textCompareFromElement(tenantManagementTitles.get(3),tcdata.getsNewTenants().toLowerCase());
        common.textCompareFromElement(tenantManagementTitles.get(5),tcdata.getsNewUsersAdded().toLowerCase());
        common.verifyElementDisplayed(selectors.txtQuickSearch);
        List<WebElement> tableTitles = common.getListOfElement(selectors.txtTenantTableTitles);
        String [] expectedTableTitles = {tcdata.getsTenantTableTitle(),tcdata.getsTenantParentTableTitle(),tcdata.getsTenantNoOfUsersTableTitle(),tcdata.getsTenantAdministratorsTableTitle(),tcdata.getsTenantActionsTableTitle()};
        for(int i = 0; i < expectedTableTitles.length; i++){
            common.textCompareFromElement(tableTitles.get(i),expectedTableTitles[i].toLowerCase());
        }
        common.fillInput(selectors.txtQuickSearchField,tcdata.getsTenantName());
        common.findAndClick(selectors.btnQuickSearch);
        common.findAndClick(selectors.btnQuickSearchEdit);
        common.waitForPresenceOfElement(1,selectors.txtOverviewTitle);
        String OverviewTitle = driver.findElement(selectors.txtOverviewTitle).getAttribute("innerHTML");
        common.textCompare(OverviewTitle.toLowerCase(),tcdata.getsOverviewTitle().toLowerCase());
        List<WebElement> overviewTitles = common.getListOfElement(selectors.txtOverviewTitles);
        String [] overviewExpectedTitles = {tcdata.getsTenantNameTitle(),tcdata.getsTenantCreatorTitle(),tcdata.getsWebsiteUrlTitle(),tcdata.getsNoOfTenantsTitle(),tcdata.getsNoOfRolesTitle(),tcdata.getsTenantNoOfUsersTableTitle(),tcdata.getsDescriptionTitle(),tcdata.getsTenantLocationTitle(),tcdata.getsDateCreatedTitle()};
        for(int i = 0; i < overviewExpectedTitles.length; i++){
            common.textCompareFromElement(overviewTitles.get(i),overviewExpectedTitles[i].toLowerCase());
        }
        List<WebElement> overviewTableTitles = common.getListOfElement(selectors.txtTenantTableTitles);
        String [] overviewTableTitlesExpected = {tcdata.getsOverviewTableDesc(),tcdata.getsOverviewTableUser(),tcdata.getsOverviewTableDate()};
        for(int i = 0; i < overviewTableTitlesExpected.length; i++){
            common.textCompareFromElement(overviewTableTitles.get(i),overviewTableTitlesExpected[i].toLowerCase());
        }
    }

    public void tenantReadStyles(TenantsFileData tcdata){
        common.goAndFindTenant(tcdata.getsTenantName(),selectors.btnAddTenant,selectors.txtQuickSearch,selectors.txtQuickSearchField,selectors.btnQuickSearch,selectors.btnQuickSearchEdit);
        common.findAndClick(selectors.txtLogoAndStyles);
        List<WebElement> styleTitles = common.getListOfElement(selectors.txtStylesTitles);
        String [] styleTitlesExpected = {tcdata.getsCompanyLogoPreviewTitle(),tcdata.getsUploadFileTitle(),tcdata.getsOrTitle(),tcdata.getsColorPreviewTitle()};
        for(int i = 0; i < styleTitlesExpected.length; i++){
            common.textCompareFromElement(styleTitles.get(i),styleTitlesExpected[i].toLowerCase());
        }
        List<WebElement> colorManagementTitles = common.getListOfElement(selectors.txtColorManagementTitles);
        String [] colorManagementTitlesExpected = {tcdata.getsNavigationBarTitle(),tcdata.getsSubnavigationBarTitle(),tcdata.getsNavigationLinksTitle(),tcdata.getsBackgroundTitle()};
        for(int i = 0; i < colorManagementTitlesExpected.length; i++){
            common.textCompareFromElement(colorManagementTitles.get(i),colorManagementTitlesExpected[i].toLowerCase());
        }
    }

    public void tenantReadIntoRole(TenantsFileData tcdata){
        common.goAndFindTenant(tcdata.getsTenantName(),selectors.btnAddTenant,selectors.txtQuickSearch,selectors.txtQuickSearchField,selectors.btnQuickSearch,selectors.btnQuickSearchEdit);
        common.findAndClick(selectors.txtRoleTitle);
        List<WebElement> roleTableTitles = common.getListOfElement(selectors.txtTableTitles);
        String [] roleTableTitlesExpected = {tcdata.getsRoleNameTitle(),tcdata.getsTimesAppliedTitle(),tcdata.getsLastEditTitle(),tcdata.getsNoOfPermissionsTitle()};
        for(int i = 0; i < roleTableTitlesExpected.length; i++){
            common.textCompareFromElement(roleTableTitles.get(i),roleTableTitlesExpected[i].toLowerCase());
        }
        List<WebElement> rolesList = common.getListOfElement(selectors.txtRoleListTable);
        rolesList.get(1).click();
        common.verifyElementDisplayed(selectors.btnEditRole);
    }

    public void tenantReadIntoUser(TenantsFileData tcdata){
        common.goAndFindTenant(tcdata.getsTenantName(),selectors.btnAddTenant,selectors.txtQuickSearch,selectors.txtQuickSearchField,selectors.btnQuickSearch,selectors.btnQuickSearchEdit);
        common.findAndClick(selectors.txtUserTitle);
        List<WebElement> userTableTitles = common.getListOfElement(selectors.txtTableTitles);
        String [] userTableTitlesExpected = {tcdata.getsFirstNameTitle(),tcdata.getsLastNameTitle(),tcdata.getsEmailTitle(),tcdata.getsTenantAdministratorTitle(),tcdata.getsInactiveTitle(),tcdata.getsActionsTitle()};
        for(int i = 0; i < userTableTitlesExpected.length; i++){
            common.textCompareFromElement(userTableTitles.get(i),userTableTitlesExpected[i].toLowerCase());
        }
        List<WebElement> userList = common.getListOfElement(selectors.txtUserListTable);
        userList.get(0).click();
        common.verifyElementDisplayed(selectors.txtUserMgmtTitle);
    }

    public void tenantDelete(TenantsFileData tcdata){
        common.findAndClick(selectors.btnAddTenant);
        common.verifyElementDisplayed(selectors.txtQuickSearch);
        common.fillInput(selectors.txtQuickSearchField,tcdata.getsTenantName());
        common.findAndClick(selectors.btnQuickSearch);
        common.waitForPresenceOfElement(1, selectors.btnDeleteTenant);
        common.findAndClick(selectors.btnDeleteTenant);
        common.waitForPresenceOfElement(1, selectors.btnDeleteTenantConfirm);
        common.findAndClick(selectors.btnDeleteTenantConfirm);
    }

    public void tenantDeactiveOrActive(TenantsFileData tcdata){
        common.goAndFindTenant(tcdata.getsTenantName(),selectors.btnAddTenant,selectors.txtQuickSearch,selectors.txtQuickSearchField,selectors.btnQuickSearch,selectors.btnQuickSearchEdit);
        common.waitForPresenceOfElement(1, selectors.btnActiveTenant);
        common.findAndClick(selectors.btnActiveTenant);
        common.waitForPresenceOfElement(1, selectors.btnActiveTenantConfirm);
        common.findAndClick(selectors.btnActiveTenantConfirm);
    }

    public void tenantEdit(TenantsFileData tcdata){
        common.goAndFindTenant(tcdata.getsTenantName(),selectors.btnAddTenant,selectors.txtQuickSearch,selectors.txtQuickSearchField,selectors.btnQuickSearch,selectors.btnQuickSearchEdit);
        common.findAndClick(selectors.btnEditTenant);
        common.fillInput(selectors.txtEditTenantName,tcdata.getsTenantName()+" Edited");
        common.findAndClick(selectors.btnEditSave);
    }

    public void tenantEditError(TenantsFileData tcdata){
        common.goAndFindTenant(tcdata.getsTenantName(),selectors.btnAddTenant,selectors.txtQuickSearch,selectors.txtQuickSearchField,selectors.btnQuickSearch,selectors.btnQuickSearchEdit);
        common.findAndClick(selectors.btnEditTenant);
        common.fillInput(selectors.txtEditTenantName," ");
        common.findAndClick(selectors.btnEditSave);
        common.verifyElementDisplayed(selectors.txtEditTenantError);
    }

    public void tenantChangeStyle(TenantsFileData tcdata){
        common.goAndFindTenant(tcdata.getsTenantName(),selectors.btnAddTenant,selectors.txtQuickSearch,selectors.txtQuickSearchField,selectors.btnQuickSearch,selectors.btnQuickSearchEdit);
        common.findAndClick(selectors.txtLogoAndStyles);
        common.fillInput(selectors.txtLogoPath,tcdata.getsLogoPath());
        common.fillInput(selectors.txtBKColor,tcdata.getsBKColor());
        common.findAndClick(selectors.btnResetStyle);
    }

    public void tenantChangeStyleError(TenantsFileData tcdata){
        common.goAndFindTenant(tcdata.getsTenantName(),selectors.btnAddTenant,selectors.txtQuickSearch,selectors.txtQuickSearchField,selectors.btnQuickSearch,selectors.btnQuickSearchEdit);
        common.findAndClick(selectors.txtLogoAndStyles);
        common.fillInput(selectors.txtLogoPath," ");
        common.verifyElementDisplayed(selectors.imgErrorImgIcon);
    }
}