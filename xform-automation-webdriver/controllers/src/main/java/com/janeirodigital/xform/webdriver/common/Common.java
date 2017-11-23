package com.janeirodigital.xform.webdriver.common;

import com.janeirodigital.xform.webdriver.enums.XmlEnum;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Common extends Initial {
    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(Common.class);
    private WebDriverWait wait;

    public Common(WebDriver driver) {
        this.driver = driver;
    }

    public void OpenBaseURL(String testEnvironment) throws Exception {
        driver.get(testEnvironment + getValueFromConfig(XmlEnum.URL.getTagName()));
    }

    public void closeBrowser() {
        driver.close();
        driver.quit();
    }

    public void waitForElementToBeClickable(int timeOutInSeconds,By selector){
        wait = new WebDriverWait(driver,timeOutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(selector));
    }

    public void waitForElementTextToAppear(int timeOutInSeconds,String textToAppear,By selector){
        wait = new WebDriverWait(driver,timeOutInSeconds);
        wait.until(ExpectedConditions.textToBe(selector,textToAppear));
    }

    public boolean isUrlCorrect(int timeOutInSeconds, String urlExpected){
        wait = new WebDriverWait(driver,timeOutInSeconds);
        return wait.until(ExpectedConditions.urlContains(urlExpected));
    }

    /**
     * read an excel file looking for a row with a number defined by idRowGet in
     * the first cell
     *
     * @param activeRowIndicator   value to look for in the first column into the xlsx file
     * @param sSheetName name of the sheet into the file
     * @return an array
     */
    public Object[] readParameterFile(String activeRowIndicator, String sSheetName) throws IOException {


        Object[] tempHeader = null;
        try {
            tempHeader = readExcel(getValueFromConfig(XmlEnum.PARAMETER_FILE.getTagName()), sSheetName, activeRowIndicator);
        } catch (IOException e) {
            logger.error("Cannot find the Profile Header Configuration File: ", e);
            throw new IOException("Cannot find the Profile Header Configuration File");
        } catch (Exception e) {
            logger.error("readParameterFile has failed: ", e);
            throw new IOException();
        }
        return tempHeader;
    }

    /**
     * read an excel file looking for a row with a number defined by idRowGet in
     * the first cell
     *
     * @param fileName  path of the file (including file name) to be readed
     * @param sheetName name of the sheet into the file
     * @param idRowGet  value to look for in the first column into the xlsx file
     * @return an array
     */
    public Object[] readExcel(String fileName, String sheetName, String idRowGet) throws IOException {

        // Create an object of File class to open xlsx file
        File file = new File(fileName);
        // Create an object of FileInputStream class to read excel file
        FileInputStream inputStream = new FileInputStream(file);
        Workbook myWorkbook = null;
        myWorkbook = new XSSFWorkbook(inputStream);
        // Read sheet inside the workbook by its name
        org.apache.poi.ss.usermodel.Sheet mySheet = myWorkbook.getSheet(sheetName);
        // Find number of rows in excel file
        int rowCount = mySheet.getLastRowNum() - mySheet.getFirstRowNum();
        Object[] tempHeader = new Object[rowCount + 1];

        // Create a loop over all the rows of excel file to read it
        for (int i = 0; i < rowCount + 1; i++) {
            Row row = mySheet.getRow(i);
            // Create a loop to print cell values in a row

            for (int j = 0; j < row.getLastCellNum(); j++) {
                logger.debug("register read from excel file: ", row.getCell(j).getStringCellValue());
                if (row.getCell(0).getStringCellValue().compareTo(idRowGet) == 0 && j > 0) {
                    tempHeader[j - 1] = row.getCell(j).getStringCellValue();
                }
            }
        }
        return tempHeader;
    }


}
