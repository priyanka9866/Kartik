//Create organization in CRM and verify it is created or not

package com.comcast.crm.pom.contactOrgsTest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.pom.objectRepositoryUtility.CreateOrganizationPage;
import com.comcast.crm.pom.objectRepositoryUtility.HomePage;
import com.comcast.crm.pom.objectRepositoryUtility.LoginPage;
import com.comcast.crm.pom.objectRepositoryUtility.OrganizationsPage;

public class DeleteOrganizationTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		
		//Read data from property file
		String browser = fLib.getDataFromPropertyFile("Browser");
		String url = fLib.getDataFromPropertyFile("Url");
		String username = fLib.getDataFromPropertyFile("Username");
		String password = fLib.getDataFromPropertyFile("Password");
				
		//Read test script data from Excel
		String orgName = eLib.getDataFromExcel("Org", 1, 2) + jLib.getRandomNumber();		
		
		//Launch Browser
				
		WebDriver driver = null;
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
			
		driver.manage().window().maximize();
		wLib.waitForPageToLoad(driver, 20);
		
		//Launch application and login
		LoginPage lp = new LoginPage(driver);  
		lp.LoginToApp(url, username, password);
				
		//Create organization with org name
		HomePage hp = new HomePage(driver);
		hp.getOrganizationLink().click();
		
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrgImgLink().click();
		
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.createOrg(orgName);
		
		Thread.sleep(3000);
		//search and delete the org
		hp.getOrganizationLink().click();
		op.searchOrg(orgName, "Organization Name");
		driver.findElement(By.xpath("//a[text()='"+orgName+"']/../..//a[text()='del']")).click();
		wLib.switchToAlertandAccept(driver);
				
		// Logout and close browser
		hp.signOut();
				
		driver.quit();		
	}	
}
