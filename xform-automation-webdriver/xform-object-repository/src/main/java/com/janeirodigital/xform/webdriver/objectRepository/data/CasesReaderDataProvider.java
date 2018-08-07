package com.janeirodigital.xform.webdriver.objectRepository.data;

import com.janeirodigital.xform.webdriver.enums.CommonEnum;
import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CasesReaderDataProvider {
    private static final Logger logger = LoggerFactory.getLogger(CasesReaderDataProvider.class);

    public CasesReaderDataProvider(){

    }

    public Object[][] getDataUserManagement(String ParameterFile) throws IOException
    {
        Object[][] tempData = null;
        tempData = readTestCasesDataFile(ParameterFile, "1", CommonEnum.XFormDataProviders.X_FORM_DP_USER_MANAGEMENT.toString(), UserManagementFileData.class);
        return tempData;
    }

    public Object[][] getDataAccessAndSecurity(String ParameterFile) throws IOException
    {
        Object[][] tempData = null;
        tempData = readTestCasesDataFile(ParameterFile, "1", CommonEnum.XFormDataProviders.XFROM_DP_ACCESS_AND_SECURITY_MANAGEMENT.toString(), AccessAndSecurityFileData.class);
        return tempData;
    }


    /**
     * Read an excel file looking for all rows with a number defined by idRowGet in
     * the first cell
     *
     * @param fileName  path of the file (including file name) to be readed
     * @param idRowGet  value to look for in the first column into the xlsx file
     * @param iSheetNumber cero base sheet number from .xlsx file
     * @param cTestCaseData class structure to read the .xlsx file
     * @return an array of UserManagementFileData objects
     */

    public Object[][] readTestCasesDataFile(String fileName, String idRowGet, String iSheetNumber, Class cTestCaseData) {
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings() // settings without any value means to ignore first row of the excel data
                .sheetIndex(Integer.valueOf(iSheetNumber))
                .build();
        List<BaseTCData> lsUsers = Poiji.fromExcel(new File(fileName), cTestCaseData, options);

        Object[][] oTCData = new Object[lsUsers.size()][1];
        int counter = 0;
        for (BaseTCData tcData : lsUsers ) {
            if (tcData.getsStatus().compareTo(idRowGet) == 0) {
                oTCData[counter][0] = tcData;
                counter++;
            }
        }
        return oTCData;
    }

}
