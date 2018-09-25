package com.janeirodigital.xform.webdriver.common;

import com.janeirodigital.xform.webdriver.enums.XmlEnum;
import com.janeirodigital.xform.webdriver.objectRepository.data.AccessAndSecurityFileData;
import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;


public class Common extends Initial {
    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(Common.class);
    private WebDriverWait wait;
    private JavascriptExecutor javascript;

    public Common(WebDriver driver) {
        this.driver = driver;
        if (driver instanceof JavascriptExecutor) { javascript = (JavascriptExecutor)driver;}
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void OpenBaseURL(String testEnvironment) throws Exception {
        driver.get(testEnvironment + getValueFromConfig(XmlEnum.URL.getTagName()));
    }

    public void OpenURL(String testEnvironment) throws Exception {
        driver.get(testEnvironment);
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

    public void waitForInvisibilityOfElementLocated(int timeOutInSeconds, By selector) {
        wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(selector));
    }

    public void waitForScriptAJAXCalls(By selector) {
        WebElement element = driver.findElement(selector);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
    }

    public void clickUsingJavaScript(By selector) {
        WebElement ele = driver.findElement(selector);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", ele);
    }

    public boolean isUrlCorrect(int timeOutInSeconds, String urlExpected) {
        wait = new WebDriverWait(driver, timeOutInSeconds);
        return wait.until(ExpectedConditions.urlContains(urlExpected));
    }

    /**
     * This method returns TRUE or FALSE if the Web element is present or not accordingly.
     * It receives as parameter a string that it's used as the cssSelector and then sets the css
     * @param locator webelement to be found
     * @return boolean indicating if the web element is present in the page
     * **/

    public boolean elementExists(By locator)
    {
        return !driver.findElements(locator).isEmpty();
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
     * @return an array of UserManagementFileData objects
     */

    public Object[] readParamTestCasesDataFile(String fileName, String idRowGet) {
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings(1) // settings 1 means does not ignore first row of the excel data
                .sheetIndex(1) //2nd sheet from .xlsx file
                .build();
        List<AccessAndSecurityFileData> lsUsers = Poiji.fromExcel(new File(fileName), AccessAndSecurityFileData.class, options);

        Object[] tempHeader = new Object[lsUsers.size()+1];
        int counter = 0;
        for (AccessAndSecurityFileData tcData: lsUsers) {
            if (tcData.getsStatus().compareTo(idRowGet) == 0) {
                tempHeader[counter] = tcData;
                counter++;
            }
        }
        return tempHeader;
    }

    /**
     * This method is in charge to generate random numbers.
     * @param iMaxRange
     * @return int random value between 1 and iMaxRange.
     */

    public int getRandomValue(int iMaxRange) {
        Random rand = new Random();
        int iNumber = rand.nextInt(iMaxRange);

        return iNumber;
    }

    /**
     * This method is to create random emails with a number after the "@"
     * @param sEmail
     * @param iNumber
     * @return Random email.
     */

    public String emailGenerator(String sEmail, int iNumber ) {
        String sMail = sEmail;
        String [] email = sMail.split("@");
        String sEmail1 = email[0];
        String sEmail2 = email[1];

        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year  = localDate.getYear();
        int month = localDate.getMonthValue();
        int day   = localDate.getDayOfMonth();

        String Email = sEmail1 + "@" + iNumber + year + month + day + "." + sEmail2;

        return Email;
    }

    /**
     * This method is in charge to scroll to find an element.
     * @param selector
     */

    public void scrollToFindElement(By selector){
        WebElement element = driver.findElement(selector);
        javascript.executeScript("arguments[0].scrollIntoView();", element);
    }

    /**
     * This method scroll to the top of the page
     */

    public void scrollTop(){
        javascript.executeScript("window.scrollTo(document.body.scrollHeight,0)");
    }

    /**
     * This method is in charge to create a list of web elements based on a selector.
     * @param selector
     * @return List of WebElements
     */

    public List<WebElement> getListOfElement(By selector){
        List<WebElement> list = driver.findElements(selector);
        return list;
    }

    /**
     * This method is in charge to clear the inputs and fill out with the data.
     * @param selector
     * @param sInputData
     */

    public void fillInput (By selector, String sInputData){
        driver.findElement(selector).clear();
        driver.findElement(selector).sendKeys(sInputData);
    }

    /**
     * This method allow the driver take an implicit wait to find an element.
     * @param timeOutInSeconds
     * @param selector
     */

    public void waitForPresenceOfElement (int timeOutInSeconds, By selector){
        try {
            wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.presenceOfElementLocated(selector));
            Thread.sleep(1000);
        } catch (Exception e) {
            logger.error("Test has failed locating " + selector.toString() + " {}", e.getMessage());
        }
    }

    /**
     * This method verify if the element is present in the page.
     * @param selector
     */

    public void verifyElementDisplayed (By selector){
        try{
            driver.findElement(selector).isDisplayed();
        } catch (Exception e){
            logger.error("Test has failed locating " + selector.toString() + " {}", e.getMessage());
        }
    }

    /**
     * This method works to get the last child on any list of web elements.
     * @param selector
     * @return
     */

    public WebElement getListLastElement(By selector){
        List<WebElement> list = driver.findElements(selector);
        WebElement element = list.get(list.size()-1);
        return element;
    }

    /**
     * This method is in charge to verify if two different copies match.
     * @param copy
     * @param expectedCopy
     */

    public void textCompare(String copy, String expectedCopy){
        Assert.assertEquals(copy.toLowerCase(),expectedCopy.toLowerCase(),"The original copy ("+ copy +") doesn't match with the expected copy ("+ expectedCopy+")");
    }

    /**
     * This method works as a finder and clicker at the same time.
     * @param selector
     */
    public void findAndClick(By selector){
        waitForPresenceOfElement(3,selector);
        driver.findElement(selector).click();
    }

    /**
     * This method is in charge to get text from web element and verify if two different copies match.
     * @param element
     * @param expectedCopy
     */

    public void textCompareFromElement(WebElement element, String expectedCopy){
        String copy = element.getText();
        Assert.assertEquals(copy.toLowerCase(),expectedCopy.toLowerCase(),"The original copy ("+ copy +") doesn't match with the expected copy ("+ expectedCopy+")");
    }

    /**
     * This method is to go to tenant page and find a specific tenant.
     * @param tcdata
     * @param btnAddTenant
     * @param txtQuickSearch
     * @param txtQuickSearchField
     * @param btnQuickSearch
     * @param btnQuickSearchEdit
     */

    public void goAndFindTenant(String tcdata, By btnAddTenant, By txtQuickSearch, By txtQuickSearchField, By btnQuickSearch, By btnQuickSearchEdit){
        findAndClick(btnAddTenant);
        verifyElementDisplayed(txtQuickSearch);
        fillInput(txtQuickSearchField,tcdata);
        findAndClick(btnQuickSearch);
        findAndClick(btnQuickSearchEdit);
    }

    /**
     * This Method works to send an enter to a specific element
     * @param selector
     */

    public void sendAnEnter(By selector){
        driver.findElement(selector).sendKeys(Keys.RETURN);
    }
}