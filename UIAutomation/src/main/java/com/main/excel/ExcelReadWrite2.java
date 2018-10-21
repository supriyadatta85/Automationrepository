package com.main.excel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import com.main.config.ConfigFile;

public class ExcelReadWrite2 
{
  @Test
  public static String read_excel(String strSheetName, String strColNameToRead, int iRowIndexToRead, String... strTestDataFilePath) throws IOException 
  {
	 // String pathloc = "/BTOD_Data.xls";
	  String pathloc = ConfigFile.strTestDataFilePath;    /*This code fetches the path of "BTOD_Data.xls" file from ConfigFile Class"*/
	  InputStream is = ExcelReadWrite2.class.getResourceAsStream(pathloc);  /*Used to stream the "BTOD_Data.xls" file since can use it from the executable JAR file*/
	  File file = new File (pathloc);   /*Used to find the File extension*/
	  String strextn = FilenameUtils.getExtension(pathloc);  /* get the file extension*/
	  
	  Workbook wbTestDataExcelWB = null;  
	  
	  String strValueRetrieved = null;
	  
	  try{
	  
	  if(strextn.equals("xls"))
	  {
		  wbTestDataExcelWB = new HSSFWorkbook(is);
	  }
	  else
	  {
		  wbTestDataExcelWB = new XSSFWorkbook(is);
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
	       
	    if (bColNameFound)
	    {
		    row = shtTestDataSheet.getRow(iRowIndexToRead);
		    //System.out.println(i +" " +j);
		    strValueRetrieved = row.getCell(j).getStringCellValue();
		    System.out.println("Value from Excel file retrived: " +strValueRetrieved);
	    }
	    else{
	    	System.out.println("Column Name not found");
	    }
	}
	catch(IOException e){
		e.printStackTrace();
	}
	  wbTestDataExcelWB.close();
	return strValueRetrieved;
	  
  }
}
