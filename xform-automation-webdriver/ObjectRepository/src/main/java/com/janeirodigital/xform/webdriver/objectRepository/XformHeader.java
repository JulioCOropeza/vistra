package com.janeirodigital.xform.webdriver.objectRepository;

import org.openqa.selenium.By;

public class XformHeader {
    public final By searchButton = By.cssSelector("xform-header-search button");
    public final By notificationButton = By.cssSelector("xform-header-notification button");
    public final String notifications = "xform-header-notification .dropdown-menu";
}
