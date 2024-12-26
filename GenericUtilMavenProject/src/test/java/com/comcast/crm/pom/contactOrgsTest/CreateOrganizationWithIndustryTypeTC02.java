//Create organization in CRM with industry type and verify it is created or not

package com.comcast.crm.pom.contactOrgsTest;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.pom.objectRepositoryUtility.CreateOrganizationPage;
import com.comcast.crm.pom.objectRepositoryUtility.HomePage;
import com.comcast.crm.pom.objectRepositoryUtility.LoginPage;
import com.comcast.crm.pom.objectRepositoryUtility.OrganizationsPage;

public class CreateOrganizationWithIndustryTypeTC02 {

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
		String orgName = eLib.getDataFromExcel("Org", 4, 2) + jLib.getRandomNumber();		
		String industry = eLib.getDataFromExcel("Org", 4, 5);		
		String type = eLib.getDataFromExcel("Org", 4, 6);		

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
				
		//Create organization with org name, industry nad type

		HomePage hp = new HomePage(driver);
		hp.getOrganizationLink().click();
		
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrgImgLink().click();
		
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.createOrg(orgName);
		
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.xpath("//input[@type='text' and @name='accountname'] ")).sendKeys(orgName);
	
		//Select Industry
		WebElement industryWE = driver.findElement(By.name("industry"));
		wLib.select(industryWE, industry);
						
		//Select Type
		WebElement typeWE = driver.findElement(By.name("accounttype"));
		wLib.select(typeWE, type);
	
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
		//Verify orgname is created in org info page using header
		String header=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(header.contains(orgName)) {
			System.out.println(orgName + "is created ==> Pass");
		} else {
			System.out.println(orgName + "is not created ==> Fail");
		}
						
		//Verify industry and type info
		String actIndustry = driver.findElement(By.id("dtlview_Industry")).getText();
		if(actIndustry.equals(industry)) {
			System.out.println(industry + " info is verified ==> Pass");
		} else {
			System.out.println(industry + " info is not verified ==> Fail");
		}
			
		String actType = driver.findElement(By.id("dtlview_Type")).getText();
		if(actType.equals(type)) {
			System.out.println(type + " info is verified ==> Pass");
		} else {
			System.out.println(type + " info is not verified ==> Fail");
		}
				
		Thread.sleep(5000);
		// Logout and close browser
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}	
}
