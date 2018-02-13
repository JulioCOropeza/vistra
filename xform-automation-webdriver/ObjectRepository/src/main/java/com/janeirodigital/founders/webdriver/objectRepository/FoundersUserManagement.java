package com.janeirodigital.founders.webdriver.objectRepository;

import org.openqa.selenium.By;

public class FoundersUserManagement {
    public final By imgSVB = By.cssSelector(".logo-col img");
    public final By btnAddNewUser = By.cssSelector("div.row.metadata-row button");
    public final By txtFirstName = By.cssSelector("#firstName");
    public final By txtLastName = By.cssSelector("#lastName");
    public final By txtJobTitle = By.cssSelector("#jobTitle");
    public final By txtEmail = By.cssSelector("#emailAddress");
    public final By txtConfirmEmail = By.cssSelector("#confirmEmail");
    public final By chkSystemAdminitrator = By.cssSelector("label[for=superUser]");

    public final By cmbRole = By.cssSelector("#SearchRoles input");

    public final By btnCreateNewUser = By.cssSelector(".btn-primary[type=submit]");
    public final By btnCancelCreateNewUser = By.cssSelector(".btn-cancel[type=button]");

    public final By txtFilterByTenant = By.cssSelector("#tenantId input");
    public final By txtFilterByRole = By.cssSelector("#roleId input");
    public final By txtFilterQuickSearch = By.cssSelector("#filter");
    public final By btnFilterQuickSearch = By.cssSelector("button.input-group-addon");
    public final By btnFilterReset = By.cssSelector("button[type=reset]");


}
