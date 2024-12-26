package com.comcast.crm.pom.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//Rule 1: Pom class creation
public class ContactInfoPage {
WebDriver driver;
	
	//Rule 2: Object identification
	
	@FindBy(id = "dtlview_Last Name")
	private WebElement actContactname;
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement headerLastName;
	
	//Rule 4: Object Encapsulation 
	
	public WebElement getActContactname() {
			return actContactname;
		}

	public WebElement getHeaderLastName() {
		return headerLastName;
	}

	//Rule 3: Object initialization
	public ContactInfoPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//Rule 5: Object Utilization
	public void verifyActOrgName(String contactLastName){
		if(headerLastName.getText().contains(contactLastName)) {
			System.out.println(contactLastName + "is created ==> Pass");
		} else {
			System.out.println(contactLastName + "is not created ==> Fail");
		}
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public void verifyHeaderOrgName(String contactLastName) {
		if(actContactname.equals(contactLastName)) {
			System.out.println(contactLastName + " is created ==> Pass");
		} else {
			System.out.println(contactLastName + " is not created ==> Fail");
		}
	}
	
}

