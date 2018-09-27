package com.janeirodigital.xform.webdriver.objectRepository.data;

import com.janeirodigital.xform.webdriver.enums.CommonEnum;
import com.poiji.annotation.ExcelCell;
import com.poiji.option.PoijiOptions;

public class TenantsFileData extends BaseTCData {
    @ExcelCell(0)
    private String sStatus;
    @ExcelCell(1)
    private String sTitleName;
    @ExcelCell(2)
    private String sTitleLocation;
    @ExcelCell(3)
    private String sTitleURL;
    @ExcelCell(4)
    private String sTitleDescription;
    @ExcelCell(5)
    private String sTitleUser;
    @ExcelCell(6)
    private String sTitleQuickSearch;
    @ExcelCell(7)
    private String sTenantName;
    @ExcelCell(8)
    private String sTenantLocation;
    @ExcelCell(9)
    private String sTenantURL;
    @ExcelCell(10)
    private String sTenantDescription;
    @ExcelCell(11)
    private String sFindUser;
    @ExcelCell(12)
    private String sActiveTenants;
    @ExcelCell(13)
    private String sNewTenants;
    @ExcelCell(14)
    private String sNewUsersAdded;
    @ExcelCell(15)
    private String sTenantTableTitle;
    @ExcelCell(16)
    private String sTenantParentTableTitle;
    @ExcelCell(17)
    private String sTenantNoOfUsersTableTitle;
    @ExcelCell(18)
    private String sTenantAdministratorsTableTitle;
    @ExcelCell(19)
    private String sTenantActionsTableTitle;
    @ExcelCell(20)
    private String sTenantCreatorTitle;
    @ExcelCell(21)
    private String sWebsiteUrlTitle;
    @ExcelCell(22)
    private String sNoOfTenantsTitle;
    @ExcelCell(23)
    private String sNoOfRolesTitle;
    @ExcelCell(24)
    private String sDescriptionTitle;
    @ExcelCell(25)
    private String sTenantLocationTitle;
    @ExcelCell(26)
    private String sDateCreatedTitle;
    @ExcelCell(27)
    private String sOverviewTitle;
    @ExcelCell(28)
    private String sTenantNameTitle;
    @ExcelCell(29)
    private String sOverviewTableDesc;
    @ExcelCell(30)
    private String sOverviewTableUser;
    @ExcelCell(31)
    private String sOverviewTableDate;
    @ExcelCell(32)
    private String sCompanyLogoPreviewTitle;
    @ExcelCell(33)
    private String sUploadFileTitle;
    @ExcelCell(34)
    private String sOrTitle;
    @ExcelCell(35)
    private String sColorPreviewTitle;
    @ExcelCell(36)
    private String sNavigationBarTitle;
    @ExcelCell(37)
    private String sSubnavigationBarTitle;
    @ExcelCell(38)
    private String sNavigationLinksTitle;
    @ExcelCell(39)
    private String sBackgroundTitle;
    @ExcelCell(40)
    private String sRoleNameTitle;
    @ExcelCell(41)
    private String sTimesAppliedTitle;
    @ExcelCell(42)
    private String sLastEditTitle;
    @ExcelCell(43)
    private String sNoOfPermissionsTitle;
    @ExcelCell(44)
    private String sFirstNameTitle;
    @ExcelCell(45)
    private String sLastNameTitle;
    @ExcelCell(46)
    private String sEmailTitle;
    @ExcelCell(47)
    private String sTenantAdministratorTitle;
    @ExcelCell(48)
    private String sInactiveTitle;
    @ExcelCell(49)
    private String sActionsTitle;
    @ExcelCell(50)
    private String sLogoPath;
    @ExcelCell(51)
    private String sBKColor;
    @ExcelCell(52)
    private String sRoleName;
    @ExcelCell(53)
    private String sRolePermission;

    @Override
    public String toString() {
        return "TenantsFileData{" +
                "sStatus=" + getsStatus() +
                ", sTitleName='" + getsTitleNamed() + '\'' +
                ", sTitleLocation='" + getsTitleLocation() + '\'' +
                ", sTitleURL='" + getsTitleURL() + '\'' +
                ", sTitleDescription='" + getsTitleDescription() + '\'' +
                ", sTitleUser='" + getsTitleUser() + '\'' +
                ", sTitleQuickSearch='" + getsTitleQuickSearch() + '\'' +
                ", sTenantName='" + getsTenantName() + '\'' +
                ", sTenantLoaction='" + getsTenantLocation() + '\'' +
                ", sTenantURL='" + getsTenantURL() + '\'' +
                ", sTenantDescription='" + getsTenantDescription() + '\'' +
                ", sFindUser='" + getsFindUser() + '\'' +
                ", sActiveTenants='" + getsActiveTenants() + '\'' +
                ", sNewTenants='" + getsNewTenants() + '\'' +
                ", sNewUsersAdded='" + getsNewUsersAdded() + '\'' +
                ", sTenantTableTitle='" + getsTenantTableTitle() + '\'' +
                ", sTenantParentTableTitle='" + getsTenantParentTableTitle() + '\'' +
                ", sTenantNoOfUsersTableTitle='" + getsTenantNoOfUsersTableTitle() + '\'' +
                ", sTenantAdministratorsTableTitle='" + getsTenantAdministratorsTableTitle() + '\'' +
                ", sTenantActionsTableTitle='" + getsTenantActionsTableTitle() + '\'' +
                ", sTenantCreatorTitle='" + getsTenantCreatorTitle() + '\'' +
                ", sWebsiteUrlTitle='" + getsWebsiteUrlTitle() + '\'' +
                ", sNoOfTenantsTitle='" + getsNoOfTenantsTitle() + '\'' +
                ", sNoOfRolesTitle='" + getsNoOfRolesTitle() + '\'' +
                ", sDescriptionTitle='" + getsDescriptionTitle() + '\'' +
                ", sTenantLocationTitle='" + getsTenantLocationTitle() + '\'' +
                ", sDateCreatedTitle='" + getsDateCreatedTitle() + '\'' +
                ", sOverviewTitle='" + getsOverviewTitle() + '\'' +
                ", sTenantNameTitle='" + getsTenantNameTitle() + '\'' +
                ", sOverviewTableDesc='" + getsOverviewTableDesc() + '\'' +
                ", sOverviewTableUser='" + getsOverviewTableUser() + '\'' +
                ", sOverviewTableDate='" + getsOverviewTableDate() + '\'' +
                ", sCompanyLogoPreviewTitle='" + getsCompanyLogoPreviewTitle() + '\'' +
                ", sUploadFileTitle='" + getsUploadFileTitle() + '\'' +
                ", sOrTitle='" + getsOrTitle() + '\'' +
                ", sColorPreviewTitle='" + getsColorPreviewTitle() + '\'' +
                ", sNavigationBarTitle='" + getsNavigationBarTitle() + '\'' +
                ", sSubnavigationBarTitle='" + getsSubnavigationBarTitle() + '\'' +
                ", sNavigationLinksTitle='" + getsNavigationLinksTitle() + '\'' +
                ", sBackgroundTitle='" + getsBackgroundTitle() + '\'' +
                ", sRoleNameTitle='" + getsRoleNameTitle() + '\'' +
                ", sTimesAppliedTitle='" + getsTimesAppliedTitle() + '\'' +
                ", sLastEditTitle='" + getsLastEditTitle() + '\'' +
                ", sNoOfPermissionsTitle='" + getsNoOfPermissionsTitle() + '\'' +
                ", sFirstNameTitle='" + getsFirstNameTitle() + '\'' +
                ", sLastNameTitle='" + getsLastNameTitle() + '\'' +
                ", sEmailTitle='" + getsEmailTitle() + '\'' +
                ", sTenantAdministratorTitle='" + getsTenantAdministratorTitle() + '\'' +
                ", sInactiveTitle='" + getsInactiveTitle() + '\'' +
                ", sActionsTitle='" + getsActionsTitle() + '\'' +
                ", sLogoPath='" + getsLogoPath() + '\'' +
                ", sBKColor='" + getsBKColor() + '\'' +
                ", sRoleName='" + getsRoleName() + '\'' +
                ", sRolePermission='" + getsRolePermission() + '\'' +
                '}';
    }

    public TenantsFileData(){
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings()
                .sheetIndex(Integer.valueOf(String.valueOf(CommonEnum.XFormDataProviders.X_FORM_DP_TENANTS_MANAGEMENT)))
                .build();
    }

    public String getsStatus() { return sStatus; }

    public String getsTitleNamed() { return sTitleName; }

    public String getsTitleLocation() { return sTitleLocation; }

    public String getsTitleURL() { return sTitleURL; }

    public String getsTitleDescription() { return sTitleDescription; }

    public String getsTitleUser() { return sTitleUser; }

    public String getsTitleQuickSearch() { return sTitleQuickSearch; }

    public String getsTenantName() { return sTenantName; }

    public String getsTenantLocation() { return sTenantLocation; }

    public String getsTenantURL() { return sTenantURL; }

    public String getsTenantDescription() { return sTenantDescription; }

    public String getsFindUser() { return sFindUser; }

    public String getsActiveTenants() { return sActiveTenants; }

    public String getsNewTenants() { return sNewTenants; }

    public String getsNewUsersAdded() { return sNewUsersAdded; }

    public String getsTenantTableTitle() { return sTenantTableTitle; }

    public String getsTenantParentTableTitle() { return sTenantParentTableTitle; }

    public String getsTenantNoOfUsersTableTitle() { return sTenantNoOfUsersTableTitle; }

    public String getsTenantAdministratorsTableTitle() { return sTenantAdministratorsTableTitle; }

    public String getsTenantActionsTableTitle() { return sTenantActionsTableTitle; }

    public String getsTenantCreatorTitle() { return sTenantCreatorTitle; }

    public String getsWebsiteUrlTitle() { return sWebsiteUrlTitle; }

    public String getsNoOfTenantsTitle() { return sNoOfTenantsTitle; }

    public String getsNoOfRolesTitle() { return sNoOfRolesTitle; }

    public String getsDescriptionTitle() { return sDescriptionTitle; }

    public String getsTenantLocationTitle() { return sTenantLocationTitle; }

    public String getsDateCreatedTitle() { return sDateCreatedTitle; }

    public String getsOverviewTitle() { return sOverviewTitle; }

    public String getsTenantNameTitle() { return sTenantNameTitle; }

    public String getsOverviewTableDesc() { return sOverviewTableDesc; }

    public String getsOverviewTableUser() { return sOverviewTableUser; }

    public String getsOverviewTableDate() { return sOverviewTableDate; }

    public String getsCompanyLogoPreviewTitle() { return sCompanyLogoPreviewTitle; }

    public String getsUploadFileTitle() { return sUploadFileTitle; }

    public String getsOrTitle() { return sOrTitle; }

    public String getsColorPreviewTitle() { return sColorPreviewTitle; }

    public String getsNavigationBarTitle() { return sNavigationBarTitle; }

    public String getsSubnavigationBarTitle() { return sSubnavigationBarTitle; }

    public String getsNavigationLinksTitle() { return sNavigationLinksTitle; }

    public String getsBackgroundTitle() { return sBackgroundTitle; }

    public String getsRoleNameTitle() { return sRoleNameTitle; }

    public String getsTimesAppliedTitle() { return sTimesAppliedTitle; }

    public String getsLastEditTitle() { return sLastEditTitle; }

    public String getsNoOfPermissionsTitle() { return sNoOfPermissionsTitle; }

    public String getsFirstNameTitle() { return sFirstNameTitle; }

    public String getsLastNameTitle() { return sLastNameTitle; }

    public String getsEmailTitle() { return sEmailTitle; }

    public String getsTenantAdministratorTitle() { return sTenantAdministratorTitle; }

    public String getsInactiveTitle() { return sInactiveTitle; }

    public String getsActionsTitle() { return sActionsTitle; }

    public String getsLogoPath() { return sLogoPath; }

    public String getsBKColor() { return sBKColor; }

    public String getsRoleName() { return sRoleName; }

    public String getsRolePermission() { return sRolePermission; }
}
