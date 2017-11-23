package com.janeirodigital.xform.webdriver.objectRepository;

import org.openqa.selenium.By;
import java.util.HashMap;
import com.janeirodigital.xform.webdriver.enums.CommonEnum;

public class XFormDashBoard {
    public final By bodyTitle = By.cssSelector("xform-page h1");
    public final By componentTitle = By.cssSelector(".list-component h1");
    public HashMap<String, Integer> leftMenuValues = new HashMap<>();

    public void fillMenuHashMap () {
        leftMenuValues.put(CommonEnum.XFormMenuTitles.X_FORM_DASH_BOARD_MENU_USR_MNGMT_TITLE.toString(),1);
        leftMenuValues.put(CommonEnum.XFormMenuTitles.X_FORM_DASH_BOARD_MENU_TENANT_TITLE.toString(),2);
        leftMenuValues.put(CommonEnum.XFormMenuTitles.X_FORM_DASH_BOARD_MENU_SYS_ADM_TITLE.toString(),3);
    }


    public By returnMenuOption (int menuValue){
        return By.cssSelector(".main-sidebar li:nth-child("+menuValue+") a");
    }
}