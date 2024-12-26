//Create organization in CRM and verify it is created or not

package com.crm.configAnnotations.contactTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.pom.objectRepositoryUtility.ContactInfoPage;
import com.comcast.crm.pom.objectRepositoryUtility.ContactsPage;
import com.comcast.crm.pom.objectRepositoryUtility.CreateContactPage;
import com.comcast.crm.pom.objectRepositoryUtility.CreateOrganizationPage;
import com.comcast.crm.pom.objectRepositoryUtility.HomePage;
import com.comcast.crm.pom.objectRepositoryUtility.OrganizationsPage;
import com.crm.generic.baseutility.BaseClass;

public class CreateContactTest extends BaseClass {

	@Test(groups = "smoke")
	public void createContactTest() throws Exception {

		// 1. Read test script data from Excel
		String lastname = eLib.getDataFromExcel("Contact", 1, 2) + jLib.getRandomNumber();

		// 2. navigate to contact module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// 3. click on create contact button
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateContactImgLink().click();
		

		// 4. Enter the details and create the contact
		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.createContact(lastname);

		// Verify contact is created in contact info page using header
		ContactInfoPage cip = new ContactInfoPage(driver);
		String actHeader = cip.getHeaderLastName().getText();
		boolean status = actHeader.contains(lastname);
		Assert.assertEquals(status, true);
		
		String actLastname = cip.getActContactname().getText();
		SoftAssert assObj = new SoftAssert();
		assObj.assertEquals(actLastname, lastname);
		assObj.assertAll();
	}

	@Test(groups = "regression")
	public void createContactWithSupportDateTest() throws Exception {

		// 1. Read test script data from Excel
		String lastname = eLib.getDataFromExcel("Contact", 1, 2) + jLib.getRandomNumber();

		// 2. navigate to contact module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// 3. click on create contact button
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateContactImgLink().click();

		// 4. Enter the details along with support date and create the contact
		String startDate = jLib.getSystemDateYYYYDDMM();
		String endDate = jLib.getRequiredDateYYYYDDMM(30);

		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.createContact(lastname, startDate, endDate);

		// Verify contact is created in contact info page using header
		ContactInfoPage cip = new ContactInfoPage(driver);

		String header = cip.getHeaderLastName().getText();
		if (header.contains(lastname)) {
			System.out.println(lastname + "info is verified ==> Pass");
		} else {
			System.out.println(lastname + "info is not verified ==> Fail");
		}

	}

	@Test(groups = "regression")
	public void createContactWithOrgTest() throws Exception {
		// CREATE ORGANIZATION
		// 1. Read test script data from Excel
		String orgName = eLib.getDataFromExcel("Org", 1, 2) + jLib.getRandomNumber();

		// 2. navigate to organization page
		HomePage hp = new HomePage(driver);
		hp.getOrganizationLink().click();

		// 3. click on create organization button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrgImgLink().click();

		// 4. Enter the details and create organization
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.createOrg(orgName);

		// CREATE CONTACT WITH EXISTING ORGANIZATION
		// 1. Read test script data from Excel
		String lastname = eLib.getDataFromExcel("Contact", 1, 2) + jLib.getRandomNumber();

		Thread.sleep(6000);
		// 2. navigate to contact module
		hp.getContactLink().click();

		// 3. click on create contact button
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateContactImgLink().click();

		// 4. Enter the details along with organization and create the contact
		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.createContact(lastname, orgName);

		// Verify contact is created in contact info page using header
		ContactInfoPage cip = new ContactInfoPage(driver);
		String header = cip.getHeaderLastName().getText();
		if (header.contains(lastname)) {
			System.out.println(lastname + " info is verified ==> Pass");
		} else {
			System.out.println(lastname + " info is not verified ==> Fail");
		}
	}
}
