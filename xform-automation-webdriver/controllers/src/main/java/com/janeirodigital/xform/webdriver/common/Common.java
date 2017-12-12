package com.janeirodigital.xform.webdriver.common;

import com.janeirodigital.xform.webdriver.enums.XmlEnum;
import com.janeirodigital.xform.webdriver.objectRepository.LoginParameterTCData;
import com.janeirodigital.xform.webdriver.objectRepository.UserManagementTCData;
import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Common extends Initial {
    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(Common.class);
    private WebDriverWait wait;
    private JavascriptExecutor javascript;

    public Common(WebDriver driver) {
        this.driver = driver;
        if (driver instanceof JavascriptExecutor) { javascript = (JavascriptExecutor)driver;}
    }

    public void OpenBaseURL(String testEnvironment) throws Exception {
        driver.get(testEnvironment + getValueFromConfig(XmlEnum.URL.getTagName()));
    }

    public void closeBrowser() {
        driver.close();
        driver.quit();
    }

    public void waitForElementToBeClickable(int timeOutInSeconds, By selector) {
        wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(selector));
    }

    public void waitForElementTextToAppear(int timeOutInSeconds, String textToAppear, By selector) {
        wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.textToBe(selector, textToAppear));
    }

    public boolean isUrlCorrect(int timeOutInSeconds, String urlExpected) {
        wait = new WebDriverWait(driver, timeOutInSeconds);
        return wait.until(ExpectedConditions.urlContains(urlExpected));
    }

    /**
     * This next method blocks the view of an element that can only be viewed if you hover the mouse over it.
     * It receives as parameter a string that it's used as the cssSelector and then sets the css
     * attribute "display:inline-block" with a jquery script
     *
     * EXAMPLE:
     * XformHeader header = new XformHeader();
     * common.blockHoverOfElement(header.notifications);
     * **/
    public void blockHoverOfElement (String cssSelector) {
        if (javascript !=null) {
            javascript.executeScript("$('" + cssSelector + "').attr('style','display:inline-block');");
        } else {
            throw new IllegalStateException("Javascript it's not properly initialized");
        }
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
