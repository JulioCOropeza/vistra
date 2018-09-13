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
    public void tenantReadStyles(TenantsFileData tcdata){ actions.tenantReadStyles(tcdata); }

    /**
     * TC 6.2.6 Read detailed info for a specific Role inside a Tenant
     * @param tcdata
     */

    @Test(groups = {"tenantReadIntoRole"}, dataProvider = "populateDataProviders", priority=4)
    public void tenantReadIntoRole(TenantsFileData tcdata){ actions.tenantReadIntoRole(tcdata); }

    /**
     * TC 6.2.7 Read Users listed inside a Tenant
     * @param tcdata
     */

    @Test(groups = {"tenantReadIntoUser"}, dataProvider = "populateDataProviders", priority=5)
    public void tenantReadIntoUser(TenantsFileData tcdata){ actions.tenantReadIntoUser(tcdata); }

    /**
     * TC 6.3.1. Delete Tenants
     * @param tcdata
     */

    @Test(groups = {"tenantDelete"}, dataProvider = "populateDataProviders", priority=6)
    public void tenantDelete(TenantsFileData tcdata){ actions.tenantDelete(tcdata); }

    /**
     * TC 6.4.2 Update Tenant to Deactivated
     * @param tcdata
     */

    @Test(groups = {"tenantDeactive"}, dataProvider = "populateDataProviders", priority=7)
    public void tenantDeactive(TenantsFileData tcdata){ actions.tenantDeactiveOrActive(tcdata); }

    /**
     * TC 6.4.1 Update Tenant to Active
     * @param tcdata
     */

    @Test(groups = {"tenantDeactive"}, dataProvider = "populateDataProviders", priority=7)
    public void tenantActive(TenantsFileData tcdata){ actions.tenantDeactiveOrActive(tcdata); }

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