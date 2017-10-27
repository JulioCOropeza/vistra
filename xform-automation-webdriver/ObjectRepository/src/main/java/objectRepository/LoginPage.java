package objectRepository;
import org.openqa.selenium.By;

public class LoginPage {
    public By loginCheck = By.cssSelector("#Wrapper > div > xform-login > xform-page > section > div.col.white-bg > section > div > form > div > div > label");
    public By username = By.cssSelector("#username");
    //public By username = By.id("username");
    public By password = By.cssSelector("#password");
    //public By signIn = By.cssSelector("#Wrapper > div > xform-login > xform-page > section > div.col.white-bg > section > div > form > div.form-group.text-center > button");
    public By signIn = By.cssSelector("#Wrapper > div > xform-login > xform-page > section > div.col.white-bg > section > div > form > div.sign-in > div.form-group.text-center > button");

}


