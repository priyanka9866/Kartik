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
import com.comcast.crm.pom.objectRepositoryUtility.LoginPage;

public class CreateContactTC01 {

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
		String lastname = eLib.getDataFromExcel("Contact", 1, 2) + jLib.getRandomNumber();
				
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
			
		wLib.waitForPageToLoad(driver, 20);
				
		//Launch application and login
		LoginPage lp = new LoginPage(driver);  
		lp.LoginToApp(url, username, password);
		
		//Create contact with last name
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
						
		//Verify contact is created in contact info page using header
		String header=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(header.contains(lastname)) {
			System.out.println(lastname + "info is verified ==> Pass");
		} else {
			System.out.println(lastname + "info is not verified ==> Fail");
		}
					
		// verify contact created with same lastname in contact info page
		String actLastname = driver.findElement(By.id("dtlview_Last Name")).getText();
		if(actLastname.equals(lastname)) {
			System.out.println(lastname + "contact is created ==> Pass");
		} else {
			System.out.println(lastname + "contact is not created ==> Fail");
		}
		
		Thread.sleep(5000);
		// Logout and close browser
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
				
		driver.quit();		
	}	
}
