package com.comcast.crm.generic.listenerUtility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListenerImplementationClass implements IRetryAnalyzer{

	int count = 0;
	int limitcount = 5;

	public boolean retry(ITestResult result) {
		if (count<limitcount) {
			count++;
			return true;
		}
		
		return false;
	}
}
