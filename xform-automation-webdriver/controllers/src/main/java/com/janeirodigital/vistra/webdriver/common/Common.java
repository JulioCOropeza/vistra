package com.janeirodigital.vistra.webdriver.common;

import com.janeirodigital.xform.webdriver.common.Initial;
import com.janeirodigital.xform.webdriver.enums.XmlEnum;
import com.janeirodigital.xform.webdriver.objectRepository.LoginParameterTCData;
import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class Common extends Initial {
    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(Common.class);

    public Common() {

    }

    /**
     * read an excel file looking for a row with a number defined by idRowGet in
     * the first cell
     *
     * @param activeRowIndicator value to look for in the first column into the xlsx file
     * @param sSheetName         name of the sheet into the file
     * @return an array
     */
    public Object[] readParameterFile(String activeRowIndicator, String sSheetName) throws IOException {


        Object[] tempHeader = null;
        try {
           tempHeader = readParamTestCasesDataFile(getValueFromConfig(XmlEnum.PARAMETER_FILE.getTagName()), activeRowIndicator);
        } catch (IOException e) {
            logger.error("Cannot find the Profile Header Configuration File: ", e);
            throw new IOException("Cannot find the Profile Header Configuration File");
        } catch (Exception e) {
            logger.error("readParameterFile has failed: {} ", e);
            throw new IOException();
        }
        return tempHeader;
    }
    /**
     * read an excel file looking for a row with a number defined by idRowGet in
     * the first cell
     *
     * @param fileName  path of the file (including file name) to be readed
     * @param idRowGet  value to look for in the first column into the xlsx file
     * @return an array of UserManagementTCData objects
     */
    public Object[] readParamTestCasesDataFile(String fileName, String idRowGet) {
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings(1) // settings 1 means does not ignore first row of the excel data
                .sheetIndex(1) //2nd sheet from .xlsx file
                .build();
        List<LoginParameterTCData> lsUsers = Poiji.fromExcel(new File(fileName), LoginParameterTCData.class, options);

        Object[] tempHeader = new Object[lsUsers.size()+1];
        int counter = 0;
        for (LoginParameterTCData tcData: lsUsers) {
            if (tcData.getsStatus().compareTo(idRowGet) == 0) {
                tempHeader[counter] = tcData;
                counter++;
            }
        }
        return tempHeader;
    }

}
