package com.janeirodigital.xform.webdriver.actions;

import com.janeirodigital.xform.webdriver.common.Common;
import com.janeirodigital.xform.webdriver.objectRepository.data.TenantsFileData;
import com.janeirodigital.xform.webdriver.objectRepository.selectors.TenantsSelectors;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class TenantsActions {
    private Common common;
    private TenantsSelectors selectors;
    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(TenantsActions.class);

    public TenantsActions(WebDriver driver) {
        common = new Common(driver);
        this.driver = driver;
        selectors = new TenantsSelectors();
    }

    public void tenantOverviewRead(TenantsFileData tcdata){
        common.findAndClick(selectors.btnAddTenant);
        List<WebElement> tenantManagementTitles = common.getListOfElement(selectors.txtTenantManagementTitles);
        common.textCompareFromElement(tenantManagementTitles.get(1),tcdata.getsActiveTenants().toLowerCase());
        common.textCompareFromElement(tenantManagementTitles.get(3),tcdata.getsNewTenants().toLowerCase());
        common.textCompareFromElement(tenantManagementTitles.get(5),tcdata.getsNewUsersAdded().toLowerCase());
        common.verifyElementDisplayed(selectors.txtQuickSearch);
        List<WebElement> tableTitles = common.getListOfElement(selectors.txtTenantTableTitles);
        String [] expectedTableTitles = {tcdata.getsTenantTableTitle(),tcdata.getsTenantParentTableTitle(),tcdata.getsTenantNoOfUsersTableTitle(),tcdata.getsTenantAdministratorsTableTitle(),tcdata.getsTenantActionsTableTitle()};
        for(int i = 0; i < expectedTableTitles.length; i++){
            common.textCompareFromElement(tableTitles.get(i),expectedTableTitles[i].toLowerCase());
        }
        common.fillInput(selectors.txtQuickSearchField,tcdata.getsTenantName());
        common.findAndClick(selectors.btnQuickSearch);
        common.findAndClick(selectors.btnQuickSearchEdit);
        common.waitForPresenceOfElement(1,selectors.txtOverviewTitle);
        String OverviewTitle = driver.findElement(selectors.txtOverviewTitle).getAttribute("innerHTML");
        common.textCompare(OverviewTitle.toLowerCase(),tcdata.getsOverviewTitle().toLowerCase());
        List<WebElement> overviewTitles = common.getListOfElement(selectors.txtOverviewTitles);
        String [] overviewExpectedTitles = {tcdata.getsTenantNameTitle(),tcdata.getsTenantCreatorTitle(),tcdata.getsWebsiteUrlTitle(),tcdata.getsNoOfTenantsTitle(),tcdata.getsNoOfRolesTitle(),tcdata.getsTenantNoOfUsersTableTitle(),tcdata.getsDescriptionTitle(),tcdata.getsTenantLocationTitle(),tcdata.getsDateCreatedTitle()};
        for(int i = 0; i < overviewExpectedTitles.length; i++){
            common.textCompareFromElement(overviewTitles.get(i),overviewExpectedTitles[i].toLowerCase());
        }
        List<WebElement> overviewTableTitles = common.getListOfElement(selectors.txtTenantTableTitles);
        String [] overviewTableTitlesExpected = {tcdata.getsOverviewTableDesc(),tcdata.getsOverviewTableUser(),tcdata.getsOverviewTableDate()};
        for(int i = 0; i < overviewTableTitlesExpected.length; i++){
            common.textCompareFromElement(overviewTableTitles.get(i),overviewTableTitlesExpected[i].toLowerCase());
        }
    }
}
