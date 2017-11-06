package com.janeirodigital.xform.webdriver.objectRepository;

import org.openqa.selenium.By;

public class XFormDashBoard {
    public final By bodyTitle = By.cssSelector("#Wrapper > div > xform-dashboard > xform-page > section > div.col.white-bg.col-12.col-md-10 > div > h1");
    public final By userManagmentMenu = By.cssSelector("#Wrapper > div > xform-dashboard > xform-page > section > div.col-md-2.main-sidebar > xform-sidebar > aside > ul > li:nth-child(1) > a");
    public final By tenantMenu = By.cssSelector("#Wrapper > div > xform-dashboard > xform-page > section > div.col-md-2.main-sidebar > xform-sidebar > aside > ul > li:nth-child(2) > a");
    public final By systemAdministrationMenu = By.cssSelector("#Wrapper > div > xform-dashboard > xform-page > section > div.col-md-2.main-sidebar > xform-sidebar > aside > ul > li:nth-child(3) > a");
}
