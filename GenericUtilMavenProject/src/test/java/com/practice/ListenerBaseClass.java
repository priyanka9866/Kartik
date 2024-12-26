package com.practice;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.comcast.crm.generic.databaseutility.DatabaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.pom.objectRepositoryUtility.HomePage;
import com.comcast.crm.pom.objectRepositoryUtility.LoginPage;

public class ListenerBaseClass {
    
    public WebDriver driver = null;
    public static WebDriver sdriver = null;
    
    public DatabaseUtility dLib = new DatabaseUtility();
    public FileUtility fLib = new FileUtility();
    public ExcelUtility eLib = new ExcelUtility();
    public JavaUtility jLib = new JavaUtility();
    public WebDriverUtility wLib = new WebDriverUtility();
    
    
    @BeforeClass(groups = {"smoke", "regression"})
    public void configBeforeClass(ITestContext context) throws IOException {
        String browser = fLib.getDataFromPropertyFile("Browser");
        
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
        context.setAttribute("driver", driver); // Store the driver in ITestContext
    }
    
    @BeforeMethod(groups = {"smoke", "regression"})
    public void configBM() throws IOException {
        String url = fLib.getDataFromPropertyFile("Url");
        String username = fLib.getDataFromPropertyFile("Username");
        String password = fLib.getDataFromPropertyFile("Password");

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
}
