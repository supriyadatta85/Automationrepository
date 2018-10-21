package com.main.testbase;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.main.config.FrameworkComponents;
import com.main.excel.ExcelPOI;
import com.main.excel.ExcelReadWrite2;


public class TestBase 
{
		public static WebDriver driver;
		public static String strWebBrowser;
		public static String strURL; 
		public static String strUserID;
		public static String strPassword;
		public static String testDataExcelFileName;
		
		
		public static void setUp() throws Exception
		{
			/*Stop printing the Logs into the Console - the below code works */
			java.util.logging.Logger.getLogger("org.apache.http.wire").setLevel(java.util.logging.Level.FINEST);
			java.util.logging.Logger.getLogger("org.apache.http.headers").setLevel(java.util.logging.Level.FINEST);
			System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
			System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
			System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire", "ERROR");
			System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "ERROR");
			System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.headers", "ERROR");
			/*Code Ends here*/
						
			//strWebBrowser = ExcelReadWrite2.read_excel("LoginInfo", "Browser", 1);		//Read Browser Name from Excel
			//strURL = ExcelReadWrite2.read_excel("LoginInfo", "URL", 1);					//Read URL from Excel
			String strBrowser = selectBrowser();
			String strWebURL = getURL();
			driver = FrameworkComponents.GetWebdriver(strBrowser,strWebURL);			//WebDriver Initialization
			
			try
			{
				ExcelPOI.UpdateLog("TestCase - SetUp","TestBase.setUpFunction.setURL and Browser", "URL: " +strWebURL+" Browser: "+strBrowser+" is entered", "Pass");
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
		public static String selectBrowser() throws Exception
		{
			strWebBrowser = ExcelReadWrite2.read_excel("LoginInfo", "Browser", 1);		//Read Browser Name from Excel
			return strWebBrowser;
		}
		
		
		public static String getURL() throws Exception
		{
			strURL = ExcelReadWrite2.read_excel("LoginInfo", "URL", 1);					//Read URL from Excel
			return strURL;
		}
		
		
		public static void waitForPageElementToLoad(WebElement element) throws Exception
		{
			WebDriverWait waitElement = new WebDriverWait(driver, 300);

			try
			{
				WebElement webElementToLoad = waitElement.until(ExpectedConditions.visibilityOf(element));
				// TODO Auto-generated method stub
				ExcelPOI.UpdateLog("TestCase - WaitForElement","TestBase.waitForPageElementToLoad", "Element: "+element+" is found", "Pass");
			}
			catch(IOException e)
			{
				ExcelPOI.UpdateLog("TestCase - WaitForElement","TestBase.waitForPageElementToLoad", "Element: "+element+" is not found", "Pass");
				e.printStackTrace();
			}
		}
		
		
		private boolean clickElement(WebElement element, WebElement expectedElement)
		{
			boolean flag;
			
			/**
			 * First "try block" executes normal / basic element click().
			 */
			try
			{
				waitForPageElementToLoad(element);
				element.click();
				try
				{
					Thread.sleep(3000);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				waitForPageElementToLoad(expectedElement);
				flag = expectedElement.isDisplayed();
				if(!flag)
				{
					throw new Exception();
				}
				ExcelPOI.UpdateLog("TestCase - clickonElement","TestBase.clickElement", "click on Element is done", "Pass");
				return true;
			}
			catch(Exception e1)
			{
				
				return true;
			}
			
		}
			
		public void waitforElementTobeClickable(WebElement element, int i) 
		{
			// TODO Auto-generated method stub
			WebDriverWait wait = new WebDriverWait(driver, i);
		    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#submitButton")));
		}

		@Test
		public static void tearDown() throws Exception 
		{
			Thread.sleep(3000);
			driver.close();
			try
			{
				ExcelPOI.UpdateLog("TestCase - CloseApplication","TestBase.tearDown", "Application is closed", "Pass");
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}

}
