package com.janeirodigital.xform.webdriver.objectRepository;
import org.openqa.selenium.By;

public class LoginPage {

    public final By loginCheck = By.cssSelector("label[for=foundersLogin]");
    public final By username = By.cssSelector("#username");
    public final By password = By.cssSelector("#password");
    public final By signIn = By.cssSelector("button[type=submit]");

}


