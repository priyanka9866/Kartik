package com.comcast.crm.pom.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

	//Rule 1: Pom class creation
public class HomePage {
	WebDriver driver;
				
	//Rule 2: Object identification
	@FindBy(linkText = "Organizations")
	private WebElement organizationLink;

	@FindBy(linkText = "Contacts")
	private WebElement contactLink;
	
	@FindBy(linkText = "Campaigns")
	private WebElement campaignLink;
	
	@FindBy(linkText = "More")
	private WebElement moreLink;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement profileIconLink;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signOutLink;
	
	
	//Rule 4: Object Encapsulation 
	public WebElement getOrganizationLink() {
		return organizationLink;
	}

	public WebElement getContactLink() {
		return contactLink;
	}
	
	public WebElement getCampaignLink() {
		return campaignLink;
	}

	public WebElement getMoreLink() {
		return moreLink;
	}
	
	public WebElement getProfileIconLink() {
		return profileIconLink;
	}
	
	public WebElement getSignOutLink() {
		return signOutLink;
	}

	//Rule 3: Object initialization
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Rule 5: Object Utilization
	public void navigateToCampaignModule() {
		Actions actions = new Actions(driver);
		actions.moveToElement(moreLink).perform();
		campaignLink.click();
	}

	public void signOut() throws InterruptedException {
		Thread.sleep(5000);
		Actions actions = new Actions(driver);
		actions.moveToElement(profileIconLink).perform();
		signOutLink.click();
	}

}
