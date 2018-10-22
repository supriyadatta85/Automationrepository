package com.main.excel;

import org.testng.annotations.Test;

import com.main.config.ConfigFile;
import com.main.config.*;

	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
	import java.util.Calendar;

	import org.apache.commons.io.FilenameUtils;
	import org.apache.poi.hssf.usermodel.HSSFWorkbook;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;


	import org.apache.poi.hssf.usermodel.HSSFRow;
	import org.apache.poi.ss.usermodel.Cell;
	import org.apache.poi.ss.usermodel.Sheet;

	import org.testng.annotations.Test;

	public class ExcelPOI 
	{
		@Test(priority = 0)
		public void setExcelFile()
		{
			
		}
	  
		@Test(priority = 1)
		//public void ReadDataFromExcel() 
		public static String ReadDataFromExcel(String strSheetName, String strColNameToRead, int iRowIndexToRead, String... strTestDataFilePath) throws IOException 
		{
				String strValueRetrieved = "";
				String strTestDataFilePathTemp = "";
				try{
					if (strTestDataFilePath.length > 0)
						strTestDataFilePathTemp = strTestDataFilePath[0];
					else
						//strTestDataFilePathTemp = ConfigFile.strTestDataFilePath;
						
						strTestDataFilePathTemp = ConfigFile.strTestDataFilePath;

					//Create a object of File class to open xlsx file
				    File file = new File(strTestDataFilePathTemp);
				 
				    //Create an object of FileInputStream class to read excel file
				    FileInputStream inputStream = new FileInputStream(file);
				    
				    
				 
				    Workbook wbTestDataExcelWB = null;
				    String strFileExtnsn = FilenameUtils.getExtension(strTestDataFilePathTemp);
				    //System.out.println("Excel File: " +ConfigFile.strTestDataFilePath + " Extnsn: " +strFileExtnsn);

				    if(strFileExtnsn.equals("xlsx")){
				    	wbTestDataExcelWB = new XSSFWorkbook(inputStream);
				    }
				    else if(strFileExtnsn.equals("xls")){
				    	wbTestDataExcelWB = new HSSFWorkbook(inputStream);
				    }
				 
				    //Read sheet inside the workbook by its name
				    Sheet shtTestDataSheet = wbTestDataExcelWB.getSheet(strSheetName);
				    
				    int i, j;
				    boolean bColNameFound = false;
				    
				    Row row = shtTestDataSheet.getRow(0);
				    for (j = 0; j <= row.getLastCellNum(); j++) {
				    	if (row.getCell(j).getStringCellValue().equals(strColNameToRead)){
				    		bColNameFound = true;
				    		break;
				    	}
				    }
				    
				    if (bColNameFound){
					    row = shtTestDataSheet.getRow(iRowIndexToRead);
					    //System.out.println(i +" " +j);
					    strValueRetrieved = row.getCell(j).getStringCellValue();
					    System.out.println("Value retrived: " +strValueRetrieved);
				    }
				    else{
				    	System.out.println("Column Name not found");
				    }
				}
				catch(IOException e){
					e.printStackTrace();
				}
				
				return strValueRetrieved;
			}
		
		public static void WriteDataToExcel(String strSheetName, String strColNameToWrite, int iRowIndexToWrite, String strValueToWrite, String... strTestDataFilePath) throws Exception {
			String strTestDataFilePathTemp = "";
			try{
				if (strTestDataFilePath.length > 0)
					strTestDataFilePathTemp = strTestDataFilePath[0];
				else
					strTestDataFilePathTemp = ConfigFile.strTestDataFilePath;

				//Create a object of File class to open xlsx file
			    File file = new File(strTestDataFilePathTemp);
			 
			    //Create an object of FileInputStream class to read excel file
			    FileInputStream inputStream = new FileInputStream(file);
			 
			    Workbook wbTestDataExcelWB = null;
			    String strFileExtnsn = FilenameUtils.getExtension(strTestDataFilePathTemp);
			    //System.out.println("Excel File: " +ConfigFile.strTestDataFilePath + " Extnsn: " +strFileExtnsn);

			    if(strFileExtnsn.equals("xlsx")){
			    	wbTestDataExcelWB = new XSSFWorkbook(inputStream);
			    }
			    else if(strFileExtnsn.equals("xls")){
			    	wbTestDataExcelWB = new HSSFWorkbook(inputStream);
			    }
		 
			    //Read excel sheet by sheet name    
			    Sheet shtTestDataSheet = wbTestDataExcelWB.getSheet(strSheetName);
		 
			    int i, j;
			    boolean bColNameFound = false;
			    
			    Row row = shtTestDataSheet.getRow(0);
			    for (j = 0; j <= row.getLastCellNum(); j++) {
			    	if (row.getCell(j).getStringCellValue().equals(strColNameToWrite)){
			    		bColNameFound = true;
			    		break;
			    	}
			    }
			    
			    if (bColNameFound){
				    row = shtTestDataSheet.getRow(iRowIndexToWrite);
				    //System.out.println(i +" " +j);
				    Cell cell = row.createCell(j);
				    cell.setCellValue(strValueToWrite);
			    }
			    else{
			    	System.out.println("Column Name not found");
			    }
			    
			    //Close input stream
			    inputStream.close();
			 
			    //Create an object of FileOutputStream class to create write data in excel file
			    FileOutputStream outputStream = new FileOutputStream(file);
			 
			    //write data in the excel file
			    wbTestDataExcelWB.write(outputStream);
			 
			    //close output stream
			    outputStream.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		public static void UpdateLog(String strTestCaseName,String strFuncName, String strLogDesc, String strPassFail, String... strTestDataFilePath) throws Exception 
		{
			String strTestDataFilePathTemp = "";
			try{
				if (strTestDataFilePath.length > 0)
					strTestDataFilePathTemp = strTestDataFilePath[0];
				else
				{
					//strTestDataFilePathTemp = ConfigFile.strTestDataFilePath;
					strTestDataFilePathTemp = "C:\\UIAutomationResults\\UIAutomationTestResults.xls";
				}
				
				//Create a object of File class to open xlsx file
			    File file = new File(strTestDataFilePathTemp);
			 
			    //Create an object of FileInputStream class to read excel file
			    FileInputStream inputStream = new FileInputStream(file);
			 
			    Workbook wbTestDataExcelWB = null;
			    String strFileExtnsn = FilenameUtils.getExtension(strTestDataFilePathTemp);
			    //System.out.println("Excel File: " +ConfigFile.strTestDataFilePath + " Extnsn: " +strFileExtnsn);

			    if(strFileExtnsn.equals("xlsx")){
			    	wbTestDataExcelWB = new XSSFWorkbook(inputStream);
			    }
			    else if(strFileExtnsn.equals("xls")){
			    	wbTestDataExcelWB = new HSSFWorkbook(inputStream);
			    }
		 
			    //Read excel sheet by sheet name    
			    Sheet shtTestDataSheet = wbTestDataExcelWB.getSheet("Log");//(ConfigFile.strLogSheet);
		 
			    int iLogSheetRowCounter = shtTestDataSheet.getLastRowNum() + 1;
			    Row row = shtTestDataSheet.createRow(iLogSheetRowCounter);
				//Cell cell = row.createCell(0);
				//cell.setCellValue(FrameworkComponents.strTestCaseName);
				Cell cell = row.createCell(0);
				cell.setCellValue(strTestCaseName);
				cell = row.createCell(1);
				cell.setCellValue(strFuncName);
				cell = row.createCell(2);
				cell.setCellValue(strLogDesc);
				cell = row.createCell(3);
				cell.setCellValue(strPassFail);
				Calendar cal = Calendar.getInstance();
		        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		        String strTime = sdf.format(cal.getTime());    
		        cell = row.createCell(4);
				cell.setCellValue(strTime);
				
				iLogSheetRowCounter ++;
			    
			    //Close input stream
			    inputStream.close();
			 
			    //Create an object of FileOutputStream class to create write data in excel file
			    FileOutputStream outputStream = new FileOutputStream(file);
			 
			    //write data in the excel file
			    wbTestDataExcelWB.write(outputStream);
			 
			    //close output stream
			    outputStream.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}

	}

