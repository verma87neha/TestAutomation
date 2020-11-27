
package com.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import org.apache.poi.xssf.usermodel.XSSFCell;

import org.apache.poi.xssf.usermodel.XSSFRow;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelParser {
	
	 private static XSSFSheet ExcelWSheet;
	 
	 private static XSSFWorkbook ExcelWBook;
	 
	 private static XSSFCell Cell;
	 
	 private static XSSFRow Row;
	
	public Object[][] readExcel(String filePath,String fileName,String sheetName)
	{
		File file =    new File(filePath+"//"+fileName);
		String[][] tabArray = null;
	
 
    try{
 
    FileInputStream ExcelFile = new FileInputStream(file);
 
    // Access the required test data sheet
 
    ExcelWBook = new XSSFWorkbook(ExcelFile);
 
    ExcelWSheet = ExcelWBook.getSheet(sheetName);
 
    int startCol = 1;
 
    int ci=0,cj=0;
 
    int totalRows = 1;
 
    int totalCols = 4;
 
    tabArray=new String[totalRows][totalCols];
 
    for (int j=startCol;j<=totalCols;j++, cj++)
 
    {
 
    tabArray[ci][cj]=getCellData(1,j);
 
    //System.out.println(tabArray[ci][cj]);
 
    }
 
 }
 
 catch (FileNotFoundException e)
 
 {
 
 System.out.println("Could not read the Excel sheet");
 
 e.printStackTrace();
 
 }
 
 catch (IOException e)
 
 {
 
 System.out.println("Could not read the Excel sheet");
 
 e.printStackTrace();
 
 } catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
 
 return(tabArray);
 
 }
 
 //This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
 
 public static String getCellData(int RowNum, int ColNum) throws Exception{
 
    try{
 
   Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
 
   String CellData = Cell.getStringCellValue();
 
   return CellData;
 
   }catch (Exception e){
 
 return"";
 
   }  


 }}


