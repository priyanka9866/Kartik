package com.DataProvider;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetProdInfoInAmazon_DP_Test {

	@Test(dataProvider = "getData")
	public void getProdInfoInAmazon_DP_Test(String brand , String productName) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://amazon.in");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brand, Keys.ENTER);
		String price = driver.findElement(By.xpath("//span[text()='"+productName+"']/../../../..//span[@class='a-price-whole']")).getText();
		System.out.println(price);
		driver.quit();
	}
	
	@DataProvider
	public Object[][] getData() throws IOException{
		ExcelUtility eLib = new ExcelUtility();
		int rowNum = eLib.getRowCount("mobileProduct");
		
		Object[][] objArr = new Object[rowNum][2];
		
		for (int i = 0; i < rowNum; i++) {
			objArr[i][0] = eLib.getDataFromExcel("mobileProduct", i+1, 0);
			objArr[i][1] = eLib.getDataFromExcel("mobileProduct", i+1, 1);
		}
				
		return objArr;
	}
}

