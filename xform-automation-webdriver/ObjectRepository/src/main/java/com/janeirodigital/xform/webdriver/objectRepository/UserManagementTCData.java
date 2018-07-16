package com.janeirodigital.xform.webdriver.objectRepository;

import com.poiji.annotation.ExcelCell;
import com.poiji.option.PoijiOptions;
import org.openqa.selenium.WebDriver;

public class UserManagementTCData extends BaseTCData {
    @ExcelCell(0)
    private String sId;
    @ExcelCell(1)
    private String sFilterByTenant;
    @ExcelCell(2)
    private String sFilterByRole;
    @ExcelCell(3)
    private String sQuickSearch;
    @ExcelCell(4)
    private String sFirstName;
    @ExcelCell(5)
    private String sLastName;
    @ExcelCell(6)
    private String sJobTitle;
    @ExcelCell(7)
    private String sEmail;
    @ExcelCell(8)
    private String sConfirmEmail;
    @ExcelCell(9)
    private String sCheckSystemAdministrator;
    @ExcelCell(10)
    private String sComboAccountTenant;
    @ExcelCell(11)
    private String sRole;
    @ExcelCell(12)
    private String sCheckTenantAdministrator;
    @ExcelCell(13)
    private String sJobTitleChange;
    @ExcelCell(14)
    private String sFirstNameChange;
    @ExcelCell(15)
    private String sLastNameChange;

    //no need getters/setters to map excel cells to fields

    @Override
    public String toString() {
        return "UserManagementTCData{" +
                "sId=" + getId() +
                ", sFilterByTenant='" + getFilterByTenant() + '\'' +
                ", sFilterByRole='" + getFilterByRole() + '\'' +
                ", sQuickSearch=" + getQuickSearch() +
                ", sFirstName=" + getFirstName() +
                ", sLastName='" + getLastName() + '\'' +
                ", sJobTitle='" + getJobTitle() + '\'' +
                ", sEmail='" + getEmail() + '\'' +
                ", sConfirmEmail='" + getConfirmEmail() + '\'' +
                ", sCheckSystemAdministrator='" + getCheckSystemAdministrator() + '\'' +
                ", sComboAccountTenant='" + getComboAccountTenant() + '\'' +
                ", sRole='" + getRole() + '\'' +
                ", sCheckTenantAdministrator='" + getCheckTenantAdministrator() + '\'' +
                ", sJobTitleChange='" + sJobTitleChange() + '\'' +
                ", sFirstNameChange='" + sFirstNameChange() + '\'' +
                ", sLastNameChange='" + sLastNameChange() + '\'' +
                '}';
    }

    public UserManagementTCData (){
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings()
                .sheetIndex(2) //3rd sheet from .xlsx file
                .build();
    }

    public String getId() {
        return sId;
    }

    public String getFilterByTenant() {
        return sFilterByTenant;
    }

    public String getFilterByRole() {
        return sFilterByRole;
    }

    public String getQuickSearch() {
        return sQuickSearch;
    }

    public String getFirstName() {
        return sFirstName;
    }

    public String getLastName() {
        return sLastName;
    }

    public String getJobTitle() {
        return sJobTitle;
    }

    public String getEmail() { return sEmail; }

    public String getConfirmEmail() { return sConfirmEmail; }

    public String getCheckSystemAdministrator() {
        return sCheckSystemAdministrator;
    }

    public String getComboAccountTenant() {
        return sComboAccountTenant;
    }

    public String getRole() {
        return sRole;
    }

    public String getCheckTenantAdministrator() {
        return sCheckTenantAdministrator;
    }

    public String sJobTitleChange() { return sJobTitleChange; }

    public  String sFirstNameChange() { return sFirstNameChange; }

    public  String sLastNameChange() { return sLastNameChange; }
}