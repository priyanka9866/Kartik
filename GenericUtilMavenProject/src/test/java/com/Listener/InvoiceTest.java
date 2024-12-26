package com.Listener;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.generic.baseutility.BaseClass;

@Listeners(com.comcast.crm.generic.listenerUtility.ListenerImplementionClass.class)
public class InvoiceTest extends BaseClass{
	
	@Test
	public void createInvoiceTest() {
		String title= driver.getTitle();
		Assert.assertEquals(title, "Login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}
	
	@Test
	public void createInvoiceWithContactTest() {
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}
}
