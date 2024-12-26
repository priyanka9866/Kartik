package com.practice;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SampleReportTest {
	@Test
	public void createContactTest() {
		
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report.html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");	
		spark.config().setTheme(Theme.DARK);
		
		ExtentReports report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows 11");
		report.setSystemInfo("Browser", "Chrome");
		ExtentTest test = report.createTest("createContactTest");
				
		test.log(Status.INFO , "Login to app");
		test.log(Status.INFO , "Navigate to contact page");
		test.log(Status.INFO , "create contact");
		if ("priya".equals("priya")) {
			test.log(Status.PASS , "contact created");
		} else {
			test.log(Status.FAIL , "contact not created");
		}
		report.flush();
	}
}
