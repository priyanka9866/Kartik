package com.comcast.crm.pom.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//Rule 1: Pom class creation
public class OrganizationInfoPage {
WebDriver driver;
	
	//Rule 2: Object identification
	
	@FindBy(id = "dtlview_Organization Name")
	private WebElement actOrgname;
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement headerOrgName;
	
	@FindBy(id = "dtlview_Industry")
	private WebElement industry;
	
	//Rule 4: Object Encapsulation 
	
	public WebElement getActOrgname() {
			return actOrgname;
		}

	public WebElement getHeaderOrgName() {
		return headerOrgName;
	}

	public WebElement getIndustry() {
		return industry;
	}

	//Rule 3: Object initialization
	public OrganizationInfoPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//Rule 5: Object Utilization
	public void verifyActOrgName(String orgName){
		if(headerOrgName.getText().contains(orgName)) {
			System.out.println(orgName + "is created ==> Pass");
		} else {
			System.out.println(orgName + "is not created ==> Fail");
		}
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public void verifyHeaderOrgName(String orgName) {
		if(actOrgname.equals(orgName)) {
			System.out.println(orgName + " is created ==> Pass");
		} else {
			System.out.println(orgName + " is not created ==> Fail");
		}
	}
	
}

