//Create organization in CRM and verify it is created or not

package com.comcast.crm.organizationModuleTest;

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

public class CreateOrgTest {

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
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
				
		//Create organization with org name
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.xpath("//input[@type='text' and @name='accountname'] ")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
						
		//Verify orgname is created in org info page using header
		String header=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(header.contains(orgName)) {
			System.out.println(orgName + "is created ==> Pass");
		} else {
			System.out.println(orgName + "is not created ==> Fail");
		}
						
		// verify org created with same orgname in org info page
		String actOrgname = driver.findElement(By.id("dtlview_Organization Name")).getText();
		if(actOrgname.equals(orgName)) {
			System.out.println(orgName + " is created ==> Pass");
		} else {
			System.out.println(orgName + " is not created ==> Fail");
		}
				
		Thread.sleep(5000);
		// Logout and close browser
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
				
		driver.quit();		
	}	
}
