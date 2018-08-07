package com.janeirodigital.xform.webdriver.objectRepository.data;

import com.janeirodigital.xform.webdriver.enums.CommonEnum;
import com.poiji.annotation.ExcelCell;
import com.poiji.option.PoijiOptions;

public class AccessAndSecurityFileData extends BaseTCData {
    @ExcelCell(0)
    private String sStatus;
    @ExcelCell(1)
    private String sPassword;
    @ExcelCell(2)
    private String sUser;

    @Override
    public String toString() {
        return "AccessAndSecurityFileData{" +
                "sStatus=" + getsStatus() +
                ", sPassword='" + getPassword() + '\'' +
                ", sUser='" + getUser() + '\'' +
                '}';
    }

    public AccessAndSecurityFileData(){
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings()
                .sheetIndex(Integer.valueOf(String.valueOf(CommonEnum.XFormDataProviders.XFROM_DP_ACCESS_AND_SECURITY_MANAGEMENT)))
                .build();
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
