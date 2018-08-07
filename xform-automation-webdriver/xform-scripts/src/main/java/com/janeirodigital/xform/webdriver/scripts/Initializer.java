package com.janeirodigital.xform.webdriver.scripts;

import com.janeirodigital.xform.webdriver.common.Initial;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

public class Initializer extends Initial {
    public Initial init;
    WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

   @Test(groups="init")
   @Parameters({"browser", "environment"})
    public void Initializer(@Optional("CHROME_WIN") String browser, @Optional("dev") String environment, ITestContext context){
       init = new Initial();
       init.configInit(context,browser,environment,getDriver());
   }
}
