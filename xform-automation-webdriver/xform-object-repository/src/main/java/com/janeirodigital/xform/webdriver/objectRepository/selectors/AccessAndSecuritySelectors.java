package com.janeirodigital.xform.webdriver.objectRepository.selectors;
import org.openqa.selenium.By;

public class AccessAndSecuritySelectors {

    public final By loginCheck = By.cssSelector("label[for=foundersLogin]");
    public final By username = By.cssSelector("#username");
    public final By password = By.cssSelector("#password");
    public final By signIn = By.cssSelector("button[type=submit]");

}


