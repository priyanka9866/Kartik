package com.Listener;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.generic.baseutility.BaseClass;

@Listeners(com.comcast.crm.generic.listenerUtility.ListenerImplementionClass.class)
public class InvoiceRetryTest extends BaseClass{
	
	@Test(retryAnalyzer = com.comcast.crm.generic.listenerUtility.RetryListenerImplementationClass.class)
	public void activateSimTest() {
		System.out.println("execute activateSimTest");
		Assert.assertEquals("", "Login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}
	
}
