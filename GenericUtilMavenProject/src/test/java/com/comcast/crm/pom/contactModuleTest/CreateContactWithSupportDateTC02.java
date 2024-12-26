//Create organization in CRM and verify it is created or not

package com.comcast.crm.pom.contactModuleTest;

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

public class CreateContactWithSupportDateTC02 {

	public static void main(String[] args) throws IOException {
		
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
		String lastname = eLib.getDataFromExcel("Contact", 4, 2) + jLib.getRandomNumber();

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
					
		//Capture System date and next 30 days date	
		String startDate = jLib.getSystemDateYYYYDDMM();
		String endDate = jLib.getRequiredDateYYYYDDMM(30);
				
		//Create contact with last name
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		
		//Enter start date
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		
		//Enter end date
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
		//Verify START DATE
		String actStartdate=driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(actStartdate.contains(startDate)) {
			System.out.println(startDate + " is verified ==> Pass");
		} else {
			System.out.println(startDate + " is not verified ==> Fail");
		}
					
		//Verify End DATE
		String actEnddate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		if(actEnddate.equals(endDate)) {
			System.out.println(endDate + " is verified ==> Pass");
		} else {
			System.out.println(endDate + " is verified ==> Fail");
		}
				
		// Logout and close browser
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
				
		driver.quit();		
	}	
}
