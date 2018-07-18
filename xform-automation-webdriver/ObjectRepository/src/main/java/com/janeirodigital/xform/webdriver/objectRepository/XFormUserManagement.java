package com.janeirodigital.xform.webdriver.objectRepository;

import org.openqa.selenium.By;

public class XFormUserManagement {
    public final By btnAddNewUser = By.cssSelector("div.row.metadata-row button");
    public final By txtFirstName = By.cssSelector("#firstName");
    public final By txtLastName = By.cssSelector("#lastName");
    public final By txtJobTitle = By.cssSelector("#jobTitle");
    public final By txtEmail = By.cssSelector("#emailAddress");
    public final By txtConfirmEmail = By.cssSelector("#confirmEmail");
    public final By chkSystemAdminitrator = By.cssSelector("div:nth-child(7) label");
    public final By cmbTenant = By.cssSelector("#tenant-0-selectized");
    public final By cmbRole = By.cssSelector("div.selectize-input.items.not-full > input[type='text']");
    public final By btnCreateNewUser = By.cssSelector("div.row.b-top button.btn.btn-primary");
    public final By btnCancelCreateNewUser = By.cssSelector("xform-user-create button.btn.btn-cancel");
    public final By txtFilterByTenant = By.cssSelector("#tenantId > div > div > input");
    public final By txttFilterByRole = By.cssSelector("#roleId > div > div > input");
    public final By txttFilterQuickSearch = By.cssSelector("#filter");
    public final By btntFilterQuickSearch = By.cssSelector("button.input-group-addon");
    public final By btntFilterReset = By.cssSelector("button.btn.btn-cancel.m-top-22");
    public final By btnCloseBanner = By.className("banner-close");
    public final By txtQuickSearch = By.id("filter");
    public final By txtUsersList = By.cssSelector("tr td a");
    public final By btnEditUserInfo = By.cssSelector(".mt-4 .btn-primary");
    public final By txtJobField = By.id("jobTitle");
    public final By btnSaveInfo = By.cssSelector(".btn-primary.mt-md-0");
    public final By txtUserCurrentData = By.cssSelector(".form-group div span");
    public final By btnDeleteUser = By.className("fa-trash");
    public final By btnConfirmDelete = By.className("swal2-confirm");
    public final By txtNoContentMsg = By.className("no-content");
}
