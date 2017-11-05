package scripts;

import objectRepository.TestDataProviders;
import actions.LogActions;
import common.Common;
import common.Initial;
import enums.XmlEnum;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Scripts {

    Common common;
    LogActions actions;
    Initial init;
    TestDataProviders testDataProvider;
    WebDriver driver;

    public Scripts(){

    }


    @Test
    @Parameters("browser")
    public Scripts(@Optional("Chrome") String browser) {
        //public Scripts(@Optional("HeadLess") String browser) {
        //public Scripts(@Optional("Chrome") String browser) {
        //ChromeHeadLess PhantomJS

        try {

            init = new Initial();
            System.out.print("==============  Scripts before init.getDriver(browser) =============\n");
            System.out.print("==============  browser  =============\n");
            System.out.print(browser);
            driver = init.getDriver(browser);
            if (driver == null)
                System.out.print("==============  DRIVER IS NULL  =============\n");
            else
                System.out.print(driver.toString());
            System.out.print("==============  Scripts after init.getDriver(browser) =============\n");
            common = new Common(driver);
            actions = new LogActions(driver);

        }catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

     // TODO add API testing to the framework
// TODO how to order Test Cases

    @BeforeClass
    public void BeforeClass() {
        try {
            System.out.print("==============  BeforeClass before openBaseURL =============\n");
            common.OpenBaseURL();
            System.out.print("==============  BeforeClass after openBaseURL =============\n");

            String userFlag = "1"; // flag to look for into the .xls file

            System.out.print("==============  BeforeClass before readParameterFile =============\n");
            Object[] initParameters = common.readParameterFile(userFlag, "Parameters");//login info is get from xls file
            System.out.print("==============  BeforeClass after readParameterFile =============\n");

            System.out.print("==============  BeforeClass before actions.login(initParameters) =============\n");
            actions.login(initParameters);
            System.out.print("==============  BeforeClass after actions.login(initParameters) =============\n");

        } catch (Exception e) {
            System.out.print("==============  FAILED LOGIN =============\n");
            Assert.fail(e.getMessage());
        }
    }

    @AfterClass
    public void AfterClass() {
        //uncomment this to allow the browser to be close after test
        	//common.closeBrowser();
    }


    @Test(dataProvider="populateDataProviders")
    public void testXFormUserManagement(String Id, String FilterByTenant,	String FilterByRole, String QuickSearch,
                                        String AddFirstName,	String LastName,	String JobTitle,	String Email,
                                        String ConfirmEmail, String	CheckSystemAdministrator,
                                        String	ComboAccountTenant, String	Role,	String CheckTenantAdministrator) {
        System.out.println("you have provided FilterByTenant as::"+FilterByTenant);
        System.out.println("you have provided FilterByRole as::"+FilterByRole);

        // Assert.fail("test fail");
    }

    @DataProvider
    public Object[][] populateDataProviders () {

        String ParameterFile =  "";
        Object[][] tempData = null;
        try {
            ParameterFile =  common.getValueFromConfig(XmlEnum.ParameterFile);
            testDataProvider = new TestDataProviders();
            tempData = testDataProvider.getDataUserManagement(ParameterFile);
        }catch (Exception ex){
            System.out.println("error cause :" + ex.getMessage());
        }

        return tempData;

    }
 /*
    // read an excel file looking for all rows with a number defined by idRowGet in
    // the first cell
    // return an array
    @DataProvider
    public Object[][] readExcelSheet() throws IOException {
    //public Object[][] readExcelSheet(String sheetName, String idRowGetStatus) throws IOException {
        String fileName = "";

        String sheetName = "UserManagement";
        String idRowGetStatus = "1";
        try {
            fileName =  common.getValueFromConfig(XmlEnum.ParameterFile);
        }catch (Exception ex){
            System.out.println("error cause :" + ex.getMessage());
        }

        FileInputStream inputStream = new FileInputStream(fileName);

        Workbook myWorkbook = null;

        myWorkbook = new XSSFWorkbook(inputStream);

        // Read sheet inside the workbook by its name
        org.apache.poi.ss.usermodel.Sheet mySheet = myWorkbook.getSheet(sheetName);

        // Find number of rows in excel file
        int rowCount = mySheet.getLastRowNum() - mySheet.getFirstRowNum();
        // Find number of columns in excel file
        Row tempRow = mySheet.getRow(0);
        int colCount = tempRow.getLastCellNum();

        Object[][] tempHeader = new Object[rowCount][colCount];

        // Create a loop over all the rows of excel file to read it
        for (int i = 1; i < rowCount + 1; i++) {

            Row row = mySheet.getRow(i);

            // Create a loop to print cell values in a row
            for (int j = 0; j < row.getLastCellNum(); j++) {

                // Print Excel data in console
                System.out.print(row.getCell(j).getStringCellValue());

                //start reading at second line avoiding headers
                if (row.getCell(0).getStringCellValue().compareTo(idRowGetStatus) == 0 && j > 0) {
                    tempHeader[i-1][j - 1] = row.getCell(j).getStringCellValue();
                }
            }
            System.out.println();
        }
        return tempHeader;
    }*/
}


