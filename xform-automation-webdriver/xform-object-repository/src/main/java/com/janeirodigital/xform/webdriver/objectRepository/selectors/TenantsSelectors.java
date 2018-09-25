package com.janeirodigital.xform.webdriver.objectRepository.selectors;
import org.openqa.selenium.By;

public class TenantsSelectors {

    public final By btnAddTenant = By.linkText("Tenants");
    public final By txtTenantTitles = By.tagName("label");
    public final By btnAddNewTenant = By.cssSelector(".btn-primary.mr-auto");
    public final By txtTenantName = By.id("name");
    public final By txtTenantLocation = By.cssSelector("div.xform-dropdown input");
    public final By txtTenantUrl = By.id("url");
    public final By txtTenantDescription = By.id("description");
    public final By txtTenantManagementTitles = By.cssSelector(".metadata div p");
    public final By txtTenantTableTitles = By.tagName("th");
    public final By txtQuickSearchField = By.cssSelector("input.form-control[name='filter']");
    public final By btnQuickSearch = By.cssSelector("button.input-group-addon");
    public final By btnQuickSearchEdit = By.cssSelector("a.table-action.table-action-detail");
    public final By txtQuickSearch = By.cssSelector("div.col-8 label");
    public final By txtOverviewTitles = By.cssSelector("div label .text-uppercase");
    public final By txtOverviewTitle = By.cssSelector("h3");
    public final By txtLogoAndStyles = By.id("tab-styles");
    public final By txtStylesTitles = By.cssSelector("p.bold");
    public final By txtColorManagementTitles = By.cssSelector(".form-group label");
    public final By txtRoleTitle = By.id("tab-roles");
    public final By txtTableTitles = By.cssSelector("th");
    public final By txtRoleListTable = By.cssSelector("td a");
    public final By btnEditRole = By.cssSelector(".btn.btn-primary");
    public final By txtUserTitle = By.id("tab-users");
    public final By txtUserListTable = By.cssSelector("td a");
    public final By txtUserMgmtTitle= By.cssSelector("a.back");
    public final By btnDeleteTenant = By.className("table-action-delete");
    public final By btnDeleteTenantConfirm = By.cssSelector(".swal2-confirm.btn-danger");
    public final By btnActiveTenant = By.cssSelector(".btn-outline-danger");
    public final By btnActiveTenantConfirm = By.cssSelector("button.swal2-confirm");
    public final By btnEditTenant = By.cssSelector(".btn.btn-primary");
    public final By txtEditTenantName = By.id("name");
    public final By btnEditSave = By.cssSelector(".mt-3.mt-md-0.btn-primary");
    public final By txtEditTenantError = By.cssSelector(".error span");
    public final By txtLogoPath = By.cssSelector(".url-input");
    public final By txtBKColor  = By.id("navigationColor");
    public final By btnResetStyle = By.cssSelector(".btn.btn-outline-primary");
}
