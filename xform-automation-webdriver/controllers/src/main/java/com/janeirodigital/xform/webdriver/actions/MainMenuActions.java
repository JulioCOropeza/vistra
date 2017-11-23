package com.janeirodigital.xform.webdriver.actions;

import com.janeirodigital.xform.webdriver.enums.CommonEnum;
import com.janeirodigital.xform.webdriver.common.Common;
import com.janeirodigital.xform.webdriver.objectRepository.XFormDashBoard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MainMenuActions {
    private Common common;
    private XFormDashBoard xFormDashboard;
    private WebDriver driver;

    public MainMenuActions(WebDriver driver){
        common = new Common(driver);
        this.driver = driver;
        xFormDashboard = new XFormDashBoard();
        xFormDashboard.fillMenuHashMap();
    }

    /**
     * This method opens the main menu Options, by waiting the specified time in waitTime
     * and finding the specified selector, then finally waiting for the title of that opened menu
     * that it's specified in the CommonEnum.BodyTitles
     *
     * EXAMPLE:
     *  public void openUserManagement() {
            openMenuOption(
                CommonEnum.PageLoadingTimes.SHORT_WAIT_TIME.getValue(),
                xFormDashboard.returnMenuOption(xFormDashboard.leftMenuValues.get(CommonEnum.XFormMenuTitles.X_FORM_DASH_BOARD_MENU_USR_MNGMT_TITLE.toString())),
                CommonEnum.BodyTitles.X_FORM_USER_MANAGEMENT_TITLE.toString()
            );
        }
     * **/

    public void openMenuOption(int waitTime, By selector, String menuOption) {
        common.waitForElementToBeClickable(waitTime, selector);
        driver.findElement(selector).click();
        common.waitForElementTextToAppear(waitTime, menuOption, xFormDashboard.componentTitle);
    }

}
