package com.crm.generic.baseutility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.comcast.crm.generic.databaseutility.DatabaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.ClassObjectUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.pom.objectRepositoryUtility.HomePage;
import com.comcast.crm.pom.objectRepositoryUtility.LoginPage;

public class BaseClass {
	
	public  WebDriver driver;
	public static WebDriver sdriver;
	
	public DatabaseUtility dLib = new DatabaseUtility();
	public FileUtility fLib = new FileUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public JavaUtility jLib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	
	/*
	 * @BeforeSuite(groups = {"smoke", "regression"}) public void configBS() {
	 * dLib.getDBConnection("","root","root"); }
	 * 
	 * 
	 */
	
	@BeforeClass(groups = {"smoke", "regression"})
	public void configBeforeClass() throws IOException {
		
		String browser = System.getProperty("browser", fLib.getDataFromPropertyFile("Browser"));
		//fLib.getDataFromPropertyFile("Browser");
		
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} 
		else if (browser.equalsIgnoreCase("edge")){
			driver = new EdgeDriver();
		}
		else {
			driver = new ChromeDriver();		
		}
		
		sdriver = driver;
		ClassObjectUtility.setDriver(driver);
	}
	
	@BeforeMethod(groups = {"smoke", "regression"})
	public void configBM() throws IOException {
		String url = System.getProperty("url", fLib.getDataFromPropertyFile("Url"));
		//fLib.getDataFromPropertyFile("Url");
		String username = System.getProperty("username", fLib.getDataFromPropertyFile("Username")); 
		//fLib.getDataFromPropertyFile("Username");
		String password = System.getProperty("passowrd", fLib.getDataFromPropertyFile("Password"));
		//fLib.getDataFromPropertyFile("Password");

		LoginPage lp = new LoginPage(driver);  
		lp.LoginToApp(url, username, password);
	}
	
	@AfterMethod(groups = {"smoke", "regression"})
	public void configAM() throws InterruptedException {
		HomePage hp = new HomePage(driver);
		hp.signOut();
	}
	
	
	 @AfterClass(groups = {"smoke", "regression"}) 
	 public void configAC() {
		 driver.quit(); 
	}
	 
	
	/*
	 * @AfterSuite(groups = {"smoke", "regression"}) public void configAfterSuite()
	 * { dLib.closeDBConnection(); }
	 */
}
