package com.janeirodigital.xform.webdriver.actions;

import com.janeirodigital.xform.webdriver.enums.CommonEnum;
import com.janeirodigital.xform.webdriver.common.Common;
import com.janeirodigital.xform.webdriver.objectRepository.LoginPage;
import com.janeirodigital.xform.webdriver.objectRepository.LoginParameterTCData;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class  LogActions {
    private Common common;
    private LoginPage login;
    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(LogActions.class);

    /**
     * Initialize LoginPage and X_FORM_DASH_BOARD page objects to be tested,
     *
     * @param driver receive the actual WebDriver
     */
    public LogActions(WebDriver driver) {
        common = new Common(driver);
        this.driver = driver;
        login = new LoginPage();
    }

    /**
     * Log into the web site
     *
     * @param initParameters receive Password and Email to login
     */
    public void login(Object[] initParameters)  {

        LoginParameterTCData params = (LoginParameterTCData) initParameters[0];
        String sPassword = params.getPassword();
        String sEmail = params.getUser();

        driver.findElement(login.loginCheck).click();
        driver.findElement(login.username).sendKeys(sEmail);
        driver.findElement(login.password).sendKeys(sPassword);

        common.waitForElementToBeClickable(CommonEnum.PageLoadingTimes.MEDIUM_WAIT_TIME.getValue(),login.signIn);
        driver.findElement(login.signIn).click();

        boolean rightURL = common.isUrlCorrect(CommonEnum.PageLoadingTimes.MEDIUM_WAIT_TIME.getValue(),CommonEnum.PagesURLs.FoundersDashBoardUrl.toString());
        Assert.assertTrue(rightURL,"The page wasn't loaded");
    }

}
