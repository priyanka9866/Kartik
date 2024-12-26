//Create organization in CRM and verify it is created or not

package com.comcast.crm.contactModuleTest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactWithOrgTest {

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
		String lastname = eLib.getDataFromExcel("Contact", 7, 2) + jLib.getRandomNumber();	
		String orgName = eLib.getDataFromExcel("Contact", 7, 3) + jLib.getRandomNumber();	

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
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		// Create organization with org name
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.xpath("//input[@type='text' and @name='accountname'] ")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		Thread.sleep(6000);
		//Create contact with last name
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		
		//To click on + symbol to add created org
		driver.findElement(By.xpath("//input[@name='account_name']/..//img")).click();
		
		//Switch to child window
		wLib.switchToTabOnUrl(driver, "module=Accounts");
	
		driver.findElement(By.name("search_text")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		
		//Switch to parent Window
		wLib.switchToTabOnUrl(driver, "Contacts&action");
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Verify contact is created in contact info page using header
		String header=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(header.contains(lastname)) {
			System.out.println(lastname + "info is verified ==> Pass");
		} else {
			System.out.println(lastname + "info is not verified ==> Fail");
		}
		
		Thread.sleep(5000);
		//Verify org name info in contact info page
		String actOrgname = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		if(actOrgname.trim().equals(orgName)) {
			System.out.println(orgName + " info is verified ==> Pass");
		} else {
			System.out.println(orgName + " info is not verified ==> Fail");
		}
		
		//Logout and close browser
		 Actions act = new Actions(driver);
		 act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		 driver.findElement(By.linkText("Sign Out")).click(); driver.quit();
	}	
}
