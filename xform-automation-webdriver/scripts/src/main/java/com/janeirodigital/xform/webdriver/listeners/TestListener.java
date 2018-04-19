package com.janeirodigital.xform.webdriver.listeners;

import com.janeirodigital.xform.webdriver.common.Initial;
import com.janeirodigital.xform.webdriver.scripts.Scripts;
import com.janeirodigital.xform.webdriver.enums.XmlEnum;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestListener extends Scripts implements ITestListener {
    private WebDriver Listener_driver = null;
    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getName().toString().trim();

        Object currentClass = result.getInstance();
        Listener_driver = this.getDriver();
        try {
            takeScreenShot(methodName, currentClass);
        } catch (Exception e) {
            logger.error("Test has Failed: ", e);
        }
    }

    public void takeScreenShot(String methodName, Object currentClass) throws Exception {

        File scrFile = ((TakesScreenshot) Listener_driver).getScreenshotAs(OutputType.FILE);
        //The below method will save the screen shot in the drive with test method name

        try {
            FileUtils.copyFile(scrFile, new File(getFullPath(methodName, currentClass)));
        } catch (IOException e) {
            logger.error("takeScreenShot has Failed: ", e);
        }
    }

    private String getFullPath(String methodName, Object currentClass) throws Exception {
        String filePath =((Scripts) currentClass).common.getValueFromConfig(XmlEnum.SCREEN_SHOOTS.getTagName());
        String sFullPath;
        String sBrowserName;

        filePath +=  getTimeStamp() + "/";
        sBrowserName = Listener_driver.toString().substring(0, Listener_driver.toString().indexOf(":"));
        Files.createDirectories(Paths.get(filePath ));
        sFullPath = filePath + sBrowserName + "_" + methodName + "_" + getTimeStamp() + ".png";
        return sFullPath;
    }

    private String getTimeStamp() {
        String lTimeStamp;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        lTimeStamp = sdf.format(timestamp);

        return lTimeStamp;

    }

    public void onFinish(ITestContext context) {
    }

    public void onTestStart(ITestResult result) {
    }

    public void onTestSuccess(ITestResult result) {
    }

    public void onTestSkipped(ITestResult result) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onStart(ITestContext context) {
    }
}