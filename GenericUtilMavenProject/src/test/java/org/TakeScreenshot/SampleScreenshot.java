package org.TakeScreenshot;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class SampleScreenshot {
	
	@Test
	public void takeScreenshotOfWebPage() throws IOException {
		JavaUtility jLib = new JavaUtility();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://amazon.com");
		TakesScreenshot ts= (TakesScreenshot) driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
        File destFile = new File("./Screenshot/test" + jLib.getRandomNumber() + ".png");

		FileUtils.copyFile(srcFile, destFile);
	}
}
