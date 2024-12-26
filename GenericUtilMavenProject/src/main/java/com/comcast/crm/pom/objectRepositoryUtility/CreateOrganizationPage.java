package com.comcast.crm.pom.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

//Rule 1: Pom class creation
public class CreateOrganizationPage {
	WebDriver driver;

	// Rule 2: Object identification

	@FindBy(xpath = "//input[@type='text' and @name='accountname'] ")
	private WebElement orgNameTextbox;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	@FindBy(name = "industry")
	private WebElement industrySelect;

	@FindBy(name = "accounttype")
	private WebElement typeSelect;

	@FindBy(id = "phone")
	private WebElement phoneNumTextbox;

	// Rule 4: Object Encapsulation

	public WebElement getorgNameTextbox() {
		return orgNameTextbox;
	}

	public WebElement getsaveBtn() {
		return saveBtn;
	}

	public WebElement getIndustrySelect() {
		return industrySelect;
	}

	public WebElement getTypeSelect() {
		return typeSelect;
	}

	public WebElement getPhoneNumTextbox() {
		return phoneNumTextbox;
	}

	// Rule 3: Object initialization
	public CreateOrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Rule 5: Object Utilization
	public void createOrg(String orgName) {
		driver.manage().window().maximize();
		orgNameTextbox.sendKeys(orgName);
		saveBtn.click();
	}

	public void createOrg(String orgName, String industry) {
		driver.manage().window().maximize();
		orgNameTextbox.sendKeys(orgName);
		Select sel = new Select(industrySelect);
		sel.selectByVisibleText(industry);
		saveBtn.click();
	}
	
	public void createOrgWithPhoneNum(String orgName, String phoneNumber) {
		driver.manage().window().maximize();
		orgNameTextbox.sendKeys(orgName);
		phoneNumTextbox.sendKeys(phoneNumber);		
		saveBtn.click();
	}
	

}
