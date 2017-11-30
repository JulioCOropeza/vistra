package com.janeirodigital.xform.webdriver.objectRepository;

import com.poiji.annotation.ExcelCell;

public class LoginParameterTCData extends BaseTCData {
    @ExcelCell(0)
    private String sStatus;
    @ExcelCell(1)
    private String sPassword;
    @ExcelCell(2)
    private String sUser;

    @Override
    public String toString() {
        return "LoginParameterTCData{" +
                "sStatus=" + getsStatus() +
                ", sPassword='" + getPassword() + '\'' +
                ", sUser='" + getUser() + '\'' +
                '}';
    }

    public LoginParameterTCData(){

    }

    public String getsStatus() {
        return sStatus;
    }

    public String getPassword() {
        return sPassword;
    }

    public String getUser() {
        return sUser;
    }

}
