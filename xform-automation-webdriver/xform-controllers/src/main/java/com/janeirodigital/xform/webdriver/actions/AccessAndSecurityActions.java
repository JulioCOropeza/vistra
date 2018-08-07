package com.janeirodigital.xform.webdriver.actions;

import com.janeirodigital.xform.webdriver.enums.CommonEnum;
import com.janeirodigital.xform.webdriver.common.Common;
import com.janeirodigital.xform.webdriver.objectRepository.data.AccessAndSecurityFileData;
import com.janeirodigital.xform.webdriver.objectRepository.selectors.AccessAndSecuritySelectors;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccessAndSecurityActions {
    private Common common;
    private AccessAndSecuritySelectors login;
    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(AccessAndSecurityActions.class);

    /**
     * Initialize AccessAndSecuritySelectors and X_FORM_DASH_BOARD page objects to be tested,
     * @param driver receive the actual WebDriver
     */

    public AccessAndSecurityActions(WebDriver driver) {
        common = new Common(driver);
        this.driver = driver;
        login = new AccessAndSecuritySelectors();
    }


    /**
     * Log into the web site
     * @param tcdata
     */

    public void login(AccessAndSecurityFileData tcdata) {
        String sPassword = tcdata.getPassword();
        String sEmail = tcdata.getUser();
        driver.findElement(login.username).sendKeys(sEmail);
        driver.findElement(login.password).sendKeys(sPassword);
        common.waitForElementToBeClickable(CommonEnum.PageLoadingTimes.MEDIUM_WAIT_TIME.getValue(),login.signIn);
        driver.findElement(login.signIn).click();
        boolean rightURL = common.isUrlCorrect(CommonEnum.PageLoadingTimes.MEDIUM_WAIT_TIME.getValue(),CommonEnum.PagesURLs.X_FORM_DASH_BOARD_URL.toString());
        Assert.assertTrue(rightURL,"The page wasn't loaded");
    }
}
