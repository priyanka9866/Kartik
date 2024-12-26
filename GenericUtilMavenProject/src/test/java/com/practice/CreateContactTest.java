package com.practice;

import org.testng.annotations.Test;

import com.comcast.crm.pom.objectRepositoryUtility.LoginPage;
import com.crm.generic.baseutility.BaseClass;

/**
 * @author Priyanka Patil
 * 
 * This class is used to Test contact creation in CRM application
 * 
 **/

public class CreateContactTest extends BaseClass {

	/**
	 * 
	 * This method is used to create contact in CRM 
	 * 
	 **/
	
	@Test
	public void createContactTest() {
		LoginPage lp = new LoginPage(driver);
		lp.LoginToApp("http://localhost:8888", "admin", "admin");
	}
	
}
