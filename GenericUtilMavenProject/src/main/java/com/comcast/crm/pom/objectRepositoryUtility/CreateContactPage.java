package com.comcast.crm.pom.objectRepositoryUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

//Rule 1: Pom class creation
public class CreateContactPage extends WebDriverUtility{
	WebDriver driver;

	// Rule 2: Object identification

	@FindBy(name = "lastname")
	private WebElement lastNameTextbox;
	
	@FindBy(name = "support_start_date")
	private WebElement supportStartDate;
	
	@FindBy(name = "support_end_date")
	private WebElement supportEndDate;
	
	@FindBy(xpath = "//input[@name='account_name']/..//img")
	private WebElement orgPlusBtn;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	// Rule 4: Object Encapsulation

	public WebElement getLastNameTextbox() {
		return lastNameTextbox;
	}

	public WebElement getOrgPlusBtn() {
		return orgPlusBtn;
	}

	public WebElement getSupportStartDate() {
		return supportStartDate;
	}

	public WebElement getSupportEndDate() {
		return supportEndDate;
	}

	public WebElement getsaveBtn() {
		return saveBtn;
	}

	// Rule 3: Object initialization
	public CreateContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Rule 5: Object Utilization
	public void createContact(String contactLastName) {
		driver.manage().window().maximize();
		waitForPageToLoad(driver, 30);
		lastNameTextbox.sendKeys(contactLastName);
		saveBtn.click();
	}
	
	public void createContact(String contactLastName, String startDate,String endDate) {
		driver.manage().window().maximize();
		waitForPageToLoad(driver, 30);
		lastNameTextbox.sendKeys(contactLastName);
		supportStartDate.clear();
		supportStartDate.sendKeys(startDate);
		supportEndDate.clear();
		supportEndDate.sendKeys(endDate);
		saveBtn.click();
	}
	
	public void createContact(String contactLastName, String orgName) {
		driver.manage().window().maximize();
		waitForPageToLoad(driver, 30);
		lastNameTextbox.sendKeys(contactLastName);
		orgPlusBtn.click();
		switchToTabOnUrl(driver, "module=Accounts");
		EmbeddedOrganizationFrameInContactsPage ecp = new EmbeddedOrganizationFrameInContactsPage(driver);
		ecp.getSearchTextbox().sendKeys(orgName);
		ecp.getSearchNowBtn().click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		switchToTabOnUrl(driver, "Contacts&action");
		saveBtn.click();
	}
}
