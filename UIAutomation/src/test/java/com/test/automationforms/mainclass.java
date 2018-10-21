package com.test.automationforms;
import java.util.ArrayList;
import java.util.Arrays;
import org.testng.TestNG;

public class mainclass 
{
  	public static void main(String args[]) throws Exception
	{
	  	  /*OpenBTODURL ob = new OpenBTODURL();
	 	  ob.setUp();
		  ob.ClickLoginAndLogout();
		  ob.tearDown();  */
		  
		  String string = "System (16)";
		  String[] parts = string.split("\\("); 			// Used String Array to split a String
		  String part = Arrays.toString(parts); 			// Used to convert String Arrays to a String
		  String parts1 = part.replaceAll("\\s+","");		// Used to replace White Space
		  String part1 = parts[0];			
		  String part2 = parts[1];
		  System.out.println(part1);						// Print the User Name
		  
	}		  
	
}
