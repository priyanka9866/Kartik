package com.comcast.crm.pom.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//Rule 1: Pom class creation
public class EmbeddedOrganizationFrameInContactsPage {
	WebDriver driver;

	// Rule 2: Object identification

	@FindBy(xpath = "//input[@name='search_text']")
	private WebElement searchTextbox;

	@FindBy(xpath = "//select[@name='search_field']")
	private WebElement searchInSelect;

	@FindBy(xpath = "//input[@name='search']")
	private WebElement searchNowBtn;
	
	// Rule 3: Object initialization
	public EmbeddedOrganizationFrameInContactsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Rule 4: Object Encapsulation

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getSearchTextbox() {
		return searchTextbox;
	}

	public WebElement getSearchInSelect() {
		return searchInSelect;
	}

	public WebElement getSearchNowBtn() {
		return searchNowBtn;
	}
	// Rule 5: Object Utilization

}