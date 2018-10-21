package com.main.config;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.main.excel.ExcelPOI;
import com.main.testbase.TestBase;

import org.testng.TestListenerAdapter;


public class CustomListner extends TestListenerAdapter
{
		
	public static WebDriver driver;

	/**
	 * Automatically - Invoked each time a test / method fails.
	 * ITestResult - This class describes the result of a test
	 * where "tr" - Test Result*/
	
	@Override
	public void onTestFailure(ITestResult tr)
	{
		driver = TestBase.driver;
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");			/*use of date and time in file name*/
		String strTime = sdf.format(cal1.getTime());
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy somewhere
		try
		{
			FileUtils.copyFile(scrFile, new File("C:\\screenshots\\"+tr.getName()+"_FT "+strTime+".png"));
			ExcelPOI.UpdateLog("TestCase - TakeScreenshotForFailedTestCase","CustomListner.onTestFailure", "Test Case Name: " +tr.getName()+" is Failed", "Pass");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	
	/**
	 * Automatically - Invoked each time a test / method skips.
	 * ITestResult - This class describes the result of a test
	 * where "tr" - Test Result*/
	@Override
	public void onTestSkipped(ITestResult tr)
	{
		driver = TestBase.driver;
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");			/*use of date and time in file name*/
		String strTime = sdf.format(cal1.getTime());
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy somewhere
		try
		{
			FileUtils.copyFile(scrFile, new File("C:\\screenshots\\"+tr.getName()+"_FT "+strTime+".png"));
			ExcelPOI.UpdateLog("TestCase - TakeScreenshotForSkippedTestCase","CustomListner.onTestFailure", "Test Case Name: " +tr.getName()+" is Skipped", "Pass");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	

}
