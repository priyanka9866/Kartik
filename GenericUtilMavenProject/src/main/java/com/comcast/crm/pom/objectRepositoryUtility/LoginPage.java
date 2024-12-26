package com.comcast.crm.pom.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

/**
 * @author Priyanka Patil
 * 
 * Login page class contains all the locations of web elements along with business libraries like LoginToApp()
 * 
 **/

	//Rule 1: Pom class creation
public class LoginPage extends WebDriverUtility{

	WebDriver driver;
	//Rule 3: Object initialization
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);	
	}
	
	//Rule 2: Object identification
	@FindBy(name="user_name")
	private WebElement usernameTextbox;
	
	@FindBy(name="user_password")
	private WebElement passwordTextbox;

	@FindBy(id="submitButton")
	private WebElement loginBtn;
	
	//Rule 4: Object Encapsulation 
	public WebElement getUsernameTextbox() {
		return usernameTextbox;
	}

	public WebElement getPasswordTextbox() {
		return passwordTextbox;
	}
	
	public WebElement getLoginBtn() {
		return loginBtn;
	}

	/**
	 * 
	 * LoginToApp is used to login into crm application with the help of arguments like url, username, password.
	 * 
	 * @param url
	 * @param username
	 * @param password
	 */
	
	//Rule 5: Object Utilization
	public void LoginToApp(String url, String username, String password) {
		driver.manage().window().maximize();
		waitForPageToLoad(driver, 20);
		driver.get(url);
		usernameTextbox.sendKeys(username);
		passwordTextbox.sendKeys(password);
		loginBtn.click();
	}

}

