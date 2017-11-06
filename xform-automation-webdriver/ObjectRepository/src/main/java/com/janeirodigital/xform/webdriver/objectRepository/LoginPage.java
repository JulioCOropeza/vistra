package com.janeirodigital.xform.webdriver.objectRepository;
import org.openqa.selenium.By;

public class LoginPage {

    public final By loginCheck = By.xpath("//*[@id=\"Wrapper\"]/div/xform-login/xform-page/section/div[2]/section/div/form/div/div/label");
    public final By username = By.cssSelector("#username");
    public final By password = By.cssSelector("#password");
    public final By signIn = By.cssSelector("#Wrapper > div > xform-login > xform-page > section > div.col.white-bg > section > div > form > div.sign-in > div.form-group.text-center > button");

}


