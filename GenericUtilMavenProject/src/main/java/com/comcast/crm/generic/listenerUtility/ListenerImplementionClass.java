package com.comcast.crm.generic.listenerUtility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.webdriverutility.ClassObjectUtility;
import com.crm.generic.baseutility.BaseClass;

public class ListenerImplementionClass extends BaseClass implements ITestListener, ISuiteListener {

	public static ExtentReports report;
	public ExtentTest test;
	public String testName;

	public void onStart(ISuite suite) {
		System.out.println("Report Config");
		String date = new Date().toString().replace(" ", "_").replace(":", "_");
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report_"+date+".html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("CRM Report");

		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows 11");
		report.setSystemInfo("Browser", "Chrome");
	}

	public void onFinish(ISuite suite) {
		System.out.println("Report Backup");
		report.flush();
	}

	public void onTestStart(ITestResult result) {
		testName = result.getMethod().getMethodName();
		test = report.createTest(testName);
		ClassObjectUtility.setTest(test);
		test.log(Status.INFO, testName + " ==> Started");

	}

	public void onTestSuccess(ITestResult result) {
		ClassObjectUtility.getTest().log(Status.PASS, testName + " ==> Succeed");
		test.log(Status.PASS, testName + " ==> Succeed");
	}

	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, testName + " ==> Failed");
		String testName = result.getMethod().getMethodName();
		TakesScreenshot ts = (TakesScreenshot) BaseClass.sdriver;
		String filePath = ts.getScreenshotAs(OutputType.BASE64);
		String date = new Date().toString().replace(" ", "_").replace(":", "_");
		test.addScreenCaptureFromBase64String(filePath, testName+date);
	}

	public void onTestSkipped(ITestResult result) {
		test.log(Status.INFO, testName + " ==> Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		test.log(Status.INFO, testName + " ==> Failed");
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		test.log(Status.INFO, testName + " ==> Failed");
		onTestFailure(result);
	}
}
