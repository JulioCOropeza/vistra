package com.janeirodigital.Vistra.webdriver.scripts;

import com.janeirodigital.vistra.webdriver.common.Common;
import com.janeirodigital.xform.webdriver.common.Environment;
import com.janeirodigital.xform.webdriver.objectRepository.CasesReaderDataProvider;
import com.janeirodigital.xform.webdriver.common.Initial;
import com.janeirodigital.xform.webdriver.enums.XmlEnum;
import com.janeirodigital.xform.webdriver.objectRepository.RedisHistoriesTCData;
import com.janeirodigital.xform.webdriver.scripts.Scripts;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VistraScripts {

    public Common common;
    public Initial init;
    private CasesReaderDataProvider testDataProvider;
    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(Scripts.class);
    private Environment testEnvironment;


    public VistraScripts() {
    }

    @Test
    @Parameters({"environment"})
    public VistraScripts(@Optional("dev") String environment) {
        try {
            init = new Initial();

            ConfigFactory.setProperty("env", environment);
            testEnvironment = ConfigFactory.create(Environment.class);
            init.setTestEnvironment(testEnvironment);

            common = new Common();
            common.setTestEnvironment(testEnvironment);

        } catch (Exception e) {
            logger.error("Initial configuration activities has failed: {} ", e.getMessage());
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass (groups = "parent")
    @Parameters({"environment"})
    public void BeforeClass(ITestContext context, @Optional("dev") String environment) {
        try {
        } catch (Exception e) {
            logger.error("BeforeClass configuration activities has failed: {} ", e.getMessage());
        }
    }


    @Test(groups = "parent", dataProvider = "populateDataProviders")
    public void testVistraRedisHistoriesInjection(RedisHistoriesTCData tcData){
        com.janeirodigital.vistra.webdriver.actions.RedisInjectionActions redisInjectionAct = new com.janeirodigital.vistra.webdriver.actions.RedisInjectionActions();

        redisInjectionAct.setRedisHistories(tcData, testEnvironment);

        logger.info("you have provided ServerId as:: {} ", tcData.getServerId().toString());
        logger.info("you have provided CurrentValue as:: {} ", tcData.getCurrentValue().toString());

    }

    @DataProvider
    public Object[][] populateDataProviders() {

        String ParameterFile = "";
        Object[][] tempData = null;
        try {
            ParameterFile = common.getValueFromConfig(XmlEnum.PARAMETER_FILE.getTagName());
            testDataProvider = new CasesReaderDataProvider();
            tempData = testDataProvider.getDataRedisHistoriesInjection(ParameterFile);
        } catch (Exception ex) {
            logger.error("Error loading DataProvider excel file");
            Assert.fail(ex.getMessage());
        }
        return tempData;
    }

    @AfterClass
    public void AfterClass() {

    }




}


