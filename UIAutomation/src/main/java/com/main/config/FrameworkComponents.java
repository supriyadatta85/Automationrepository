package com.main.config;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

//import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class FrameworkComponents 
{
	public static WebDriver driver;
	//@Test
	public static WebDriver GetWebdriver(String strBrowser, String strURL) throws Exception
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
		System.out.println("\nInside Get Webdriver method: "+strBrowser);
		try
		{
			if (strBrowser.equalsIgnoreCase("FireFox"))
			{
				
				String strGECKO_WebDriver_Path = ConfigFile.strGECKO__WebDriver_Path_Location;  //Read WebDriver Path
				System.setProperty("webdriver.gecko.driver", strGECKO_WebDriver_Path);  // Set System Property for the WebDriver
				DesiredCapabilities dc = DesiredCapabilities.firefox();
				dc.setCapability("gecko", true);
				dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true); // Used to Handle SSL Certificate error
				System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");		//Use this code to Stop printing the logs of GECKO Driver
				System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"C:\\temp\\logs1.txt");	//The logs of GECKO Driver will be print into logs.txt file
				driver = new FirefoxDriver(dc);
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			    driver.manage().window().maximize();
				driver.get(strURL);
				return driver;
			}
			else if (strBrowser.equalsIgnoreCase("IE"))
			{
				
				String strIE_WebDriver_Path = ConfigFile.strIE__WebDriver_Path_Location;//.Read_Webdriver_Path();
				//System.setProperty(strIE_WebDriver_Name,strIE_WebDriver_Path);
				System.setProperty("webdriver.ie.driver",strIE_WebDriver_Path);
				DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
				driver = new InternetExplorerDriver(dc);
				driver.get(strURL);
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			    driver.manage().window().maximize();
			    return driver;
				
			}
			else if (strBrowser.equalsIgnoreCase("Chrome"))
			{
				
				String strChrome_WebDriver_Path = ConfigFile.strChrome_WebDriver_Path_Location;//.Read_Webdriver_Path();
				System.setProperty("webdriver.chrome.driver",strChrome_WebDriver_Path);
				DesiredCapabilities dc = DesiredCapabilities.chrome();
				driver = new ChromeDriver(dc);
				driver.get(strURL);
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			    driver.manage().window().maximize();
			    return driver;
				
			}			
			else
			{
				System.out.println("Browser is not opening.");
				//return null;
				
			}
		}
		catch(Exception e)
		{	
			System.out.println("Error in open URL");
			e.printStackTrace();
		}
		return driver;
	}
	
}

