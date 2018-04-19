package com.janeirodigital.xform.webdriver.objectRepository;

import com.janeirodigital.xform.webdriver.enums.CommonEnum;
import com.poiji.annotation.ExcelCell;
import com.poiji.option.PoijiOptions;

public class RedisHistoriesTCData extends BaseTCData {
    @ExcelCell(0)
    private String sRowActive;
    @ExcelCell(1)
    private String sServerId;
    @ExcelCell(2)
    private String sUnixTimeStamp;
    @ExcelCell(3)
    private String sCurrentValue;

    //no need getters/setters to map excel cells to fields

    @Override
    public String toString() {
        return "RedisHistoriesTCData{" +
                "sRowActive=" + getRowActive() +
                ", sServerId='" + getServerId() + '\'' +
                ", sUnixTimeStamp='" + getUnixTimeStamp() + '\'' +
                ", sCurrentValue='" + getCurrentValue() + '\'' +
                '}';
    }

    public RedisHistoriesTCData(){
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings()
                .sheetIndex(Integer.parseInt(CommonEnum.XFormDataProviders.X_FORM_DP_REDIS_HISTORIES_INJECTION.toString())) //6th sheet from .xlsx file
                .build();
    }

    public String getRowActive() {
        return sRowActive;
    }

    public String getServerId() {
        return sServerId;
    }

    public String getUnixTimeStamp() {
        return sUnixTimeStamp;
    }

    public String getCurrentValue() {
        return sCurrentValue;
    }

}
