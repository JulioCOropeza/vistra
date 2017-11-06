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
    public Scripts(@Optional("PhantomJS") String browser) {
        //public Scripts(@Optional("HeadLess") String browser) {
        //public Scripts(@Optional("Chrome") String browser) {
        //ChromeHeadLess PhantomJS

        try {

            init = new Initial();
            driver = init.getDriver(browser);
         /*   if (driver == null)
                System.out.print("==============  DRIVER IS NULL  =============\n");
            else
                System.out.print(driver.toString());*/

            common = new Common(driver);
            actions = new LogActions(driver);

        }catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    public void BeforeClass() {
        try {

            common.OpenBaseURL();
            String userFlag = "1"; // flag to look for into the .xls file

            Object[] initParameters = common.readParameterFile(userFlag, "Parameters");//login info is get from xls file
            actions.login(initParameters);

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

}


