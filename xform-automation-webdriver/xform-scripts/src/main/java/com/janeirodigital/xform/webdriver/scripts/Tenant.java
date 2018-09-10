package com.janeirodigital.xform.webdriver.scripts;

import com.janeirodigital.xform.webdriver.actions.TenantsActions;
import com.janeirodigital.xform.webdriver.common.Common;
import com.janeirodigital.xform.webdriver.enums.XmlEnum;
import com.janeirodigital.xform.webdriver.objectRepository.data.CasesReaderDataProvider;
import com.janeirodigital.xform.webdriver.objectRepository.data.TenantsFileData;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

public class Tenant {
    public Common common;
    private CasesReaderDataProvider testDataProvider;
    private static final Logger logger = LoggerFactory.getLogger(Tenant.class);
    WebDriver driver;
    TenantsActions actions;

    public Tenant(){}
    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass(groups = "tenantCreate", dependsOnGroups = {"login","init"})
    @Parameters({"browser", "environment"})
    public void BeforeClass(@Optional("CHROME_WIN") String browser, @Optional("dev") String environment, ITestContext context) {
        driver = (WebDriver) context.getAttribute("driver");
        common = (Common) context.getAttribute("common");
        actions = new TenantsActions(driver);
    }

    /**
     * TC 6.2.2 Read overview layout of a Tenant
     * @param tcdata
     */

    @Test(groups = {"tenantOverviewRead"}, dataProvider = "populateDataProviders", priority=2)
    public void tenantOverviewRead(TenantsFileData tcdata){ actions.tenantOverviewRead(tcdata); }

    /**
     * TC 6.2.4 Read Logo & Styles for a Tenant
     * @param tcdata
     */

    @Test(groups = {"tenantReadStyles"}, dataProvider = "populateDataProviders", priority=3)
    public void  tenantReadStyles(TenantsFileData tcdata){ actions.tenantReadStyles(tcdata); }


    @DataProvider
    public Object[][] populateDataProviders() {
        String ParameterFile = "";
        Object[][] tempData = null;
        try {
            ParameterFile = common.getValueFromConfig(XmlEnum.PARAMETER_FILE.getTagName());
            testDataProvider = new CasesReaderDataProvider();
            tempData = testDataProvider.getDataTenants(ParameterFile);
        } catch (Exception ex) {
            logger.error("Error loading DataProvider excel file");
            Assert.fail(ex.getMessage());
        }
        return tempData;
    }

    @AfterClass(dependsOnGroups = {"tenantCreate"},alwaysRun=true)
    public void AfterClass() {
        common.closeBrowser();
    }
}