package com.main.config;

import org.testng.annotations.Test;

public class ConfigFile 
{
	//public static String strIE_WebDriver_Name = "webdriver.ie.driver";
	
	//public static String strGECKO__WebDriver_Path_Location = "src//main//resources//geckodriver.exe";   //The location is inside project src/main/resources folder - during run time JAR can't execute this exe 
	//public static String strIE__WebDriver_Path_Location = "src//main//resources//IEDriverServer.exe";   //The location is inside project src/main/resources folder -during run time JAR can't execute this exe
	
	public static String strGECKO__WebDriver_Path_Location = "/TrUNC1.4/geckodriver.exe";  //The location is "C://TrUNC1.4/geckodriver.exe"
	public static String strIE__WebDriver_Path_Location = "/TrUNC1.4/IEDriverServer.exe";  //The location is "C://TrUNC1.4/IEDriverServer.exe"
	public static String strChrome_WebDriver_Path_Location = "/TrUNC1.4/chromedriver.exe";	//The location is "C://TrUNC1.4/chromedriver.exe"
	
	//public static String strTestDataFilePath = "C://Selenium//BTOD_TESTNG_HYBRID_FRAMEWORK_PROJECT1//src//BTOD Data Files//BTOD_Data.xls";
	//public static String strTestDataFilePath = "src//main//resources//BTOD_Data.xls";
	
	
	public static final String strTestDataFilePath = "/TruncData.xls";		//Correct location
	
	//public static String strTestPropertyFilePath = "/OpenBTODURL.properties";
	public static String strLogSheet_LoginScreen = "LoginScreen_Log";
	//public static String strLogSheet_BanSearchScreen = "BanSearchScreen_Log";
}
