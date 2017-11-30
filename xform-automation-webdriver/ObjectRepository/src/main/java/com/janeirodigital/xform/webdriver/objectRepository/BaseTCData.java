package com.janeirodigital.xform.webdriver.objectRepository;

import com.poiji.annotation.ExcelCell;

public class BaseTCData {
    @ExcelCell(0)
    private String sStatus;

    @Override
    public String toString() {
        return "BaseTCData{" +
                "sStatus=" + getsStatus() +
                '}';
    }

    public BaseTCData(){

    }

    public String getsStatus() {
        return sStatus;
    }


}
