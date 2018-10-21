package com.test.automationforms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class waitForPage 
{
	public static WebDriver driver;
	public static void waitForPageElementToLoad(WebElement element) throws Exception
	{
		// TODO Auto-generated method stub
		WebDriverWait waitElement = new WebDriverWait(driver, 3000);
		WebElement webElementToLoad = waitElement.until(ExpectedConditions.visibilityOf(element));
	}
	
}
