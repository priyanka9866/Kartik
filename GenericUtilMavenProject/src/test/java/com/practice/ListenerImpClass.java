package com.practice;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerImpClass extends ListenerBaseClass implements ITestListener, ISuiteListener {
    
    public void onStart(ISuite suite) {
        System.out.println("Report Config");
    }

    public void onFinish(ISuite suite) {
        System.out.println("Report Backup");
    }

    public void onTestStart(ITestResult result) {
        System.out.println("TestName : " + result.getMethod().getMethodName() + " ==>> started");
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("TestName : " + result.getMethod().getMethodName() + " ==>> succeeded");
    }

    public void onTestFailure(ITestResult result) {
        takeScreenshot(result, "FAILED");
    }

    public void onTestSkipped(ITestResult result) {
        takeScreenshot(result, "SKIPPED");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);
    }

    private void takeScreenshot(ITestResult result, String status) {
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
        if (driver != null) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File srcFile = ts.getScreenshotAs(OutputType.FILE);
            
            File screenshotDir = new File("./Screenshot/");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            String testName = result.getMethod().getMethodName();
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            try {
                File screenshotFile = new File(screenshotDir + "/" + testName + "_" + status + "_" + timestamp + ".png");
                FileUtils.copyFile(srcFile, screenshotFile);
                System.out.println("Screenshot saved at: " + screenshotFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Driver is null for " + result.getMethod().getMethodName());
        }
    }
}
