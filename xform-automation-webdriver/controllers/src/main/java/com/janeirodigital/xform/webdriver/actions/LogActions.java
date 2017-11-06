package com.janeirodigital.xform.webdriver.actions;

import com.janeirodigital.xform.webdriver.enums.CommonEnum;
import com.janeirodigital.xform.webdriver.objectRepository.LoginPage;
import com.janeirodigital.xform.webdriver.objectRepository.XFormDashBoard;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogActions {
    private LoginPage login;
    private XFormDashBoard XFormDashBoard;
    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(LogActions.class);

    /**
     * Initialize LoginPage and X_FORM_DASH_BOARD page objects to be tested,
     *
     * @param driver receive the actual WebDriver
     */
    public LogActions(WebDriver driver) {
        this.driver = driver;
        login = new LoginPage();
        XFormDashBoard = new XFormDashBoard();

    }

    /**
     * Log into the web site
     *
     * @param initParameters receive Password and Email to login
     */
    public void login(Object[] initParameters)  {

        String sPassword = initParameters[0].toString();
        String sEmail = initParameters[1].toString();

        driver.findElement(login.loginCheck).click();
        driver.findElement(login.username).sendKeys(sEmail);
        driver.findElement(login.password).sendKeys(sPassword);


        WebDriverWait wait = new WebDriverWait(driver, 30);
        //TODO this need to be set in a specific function in commons
        wait.until(ExpectedConditions.elementToBeClickable(login.signIn));

        driver.findElement(login.signIn).click();

        boolean rightURL = wait.until(ExpectedConditions.urlContains(CommonEnum.PagesURLs.FoundersDashBoardUrl.toString()));

        Assert.assertTrue(rightURL,"The page wasn't loaded");

        }

}
