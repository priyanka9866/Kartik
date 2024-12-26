package com.comcast.crm.generic.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
		
	//Implicit Wait
	public void waitForPageToLoad(WebDriver driver, int seconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	}
	
	//Explicit Wait
	public void waitForElementToLoad(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	//switching PARENT CHILD windows based on url
	public void switchToTabOnUrl(WebDriver driver, String partialUrl) {
		Set<String> windowIds = driver.getWindowHandles();
		Iterator<String> iterator = windowIds.iterator();
		while (iterator.hasNext()) {
			String windowid = iterator.next();
			driver.switchTo().window(windowid);
			
			String actUrl = driver.getCurrentUrl();
			if (actUrl.contains(partialUrl)) {
				break;
			} 
		}
	}
	
	//switching PARENT CHILD windows based on title
	public void switchToTabOnTitle(WebDriver driver, String partialTitle) {
		Set<String> windowIds = driver.getWindowHandles();
		Iterator<String> iterator = windowIds.iterator();
		while (iterator.hasNext()) {
			String windowid = iterator.next();
			driver.switchTo().window(windowid);
			
			String actUrl = driver.getTitle();
			if (actUrl.contains(partialTitle)) {
				break;
			} 
		}
	}
	
	// Switch to frame based on Index 
	public void switchToFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}
	
	// Switch to frame based on nameId
	public void switchToFrame(WebDriver driver, String nameId) {
		driver.switchTo().frame(nameId);
	}
	
	// Switch to frame based on web element
	public void switchToFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}
	
	//switch to Alert and Accept
	public void switchToAlertandAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	//switch to Alert and Dismiss
	public void switchToAlertandDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	//Select drop-down based on text
	public void select(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}
	
	//Select drop-down based on index
	public void select(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}
	
	//mouse hover to element 
	public void moveMouseCursorOnElement(WebDriver driver, WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}
	
}