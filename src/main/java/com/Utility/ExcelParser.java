
package com.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.pojo.Report;

public class ExcelParser implements GenericParser {

	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;

	public Object[][] readInput(String filePath, String fileName, String sheetName) {
		File file = new File(filePath + "//" + fileName);
		String[][] tabArray = null;

		try {

			FileInputStream ExcelFile = new FileInputStream(file);

			// Access the required test data sheet

			ExcelWBook = new XSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheet(sheetName);

			int startCol = 0;
			int startRow = 0;

			int totalRows = ExcelWSheet.getLastRowNum();
			System.out.println("value of total rows " + totalRows);
			int totalCols = ExcelWSheet.getRow(0).getLastCellNum() - 1;
			System.out.println("value of total columns " + totalCols);

			tabArray = new String[totalRows][totalCols];

			for (int i = startRow; i < totalRows; i++) {
				System.out.println("values+++++++++++++");

				for (int j = startCol; j < totalCols; j++) {

					tabArray[i][j] = getCellData(i + 1, j + 1);
					System.out.println("value of row and column " + i + " " + j + " " + tabArray[i][j]);

				}
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

		return (tabArray);

	}

	// This method is to read the test data from the Excel cell, in this we are
	// passing parameters as Row num and Col num

	public static String getCellData(int RowNum, int ColNum) throws Exception {

		try {

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

			String CellData = Cell.getStringCellValue();

			return CellData;

		} catch (Exception e) {

			return "";

		}

	}

	@Override
	public Object[][] writeReport(String filePath, String fileName, List<Report> report) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet(" Execution Report");
		XSSFRow row;
		int rowid = 0;
		for (Report writereport : report) {

			row = spreadsheet.createRow(rowid++);

			int cellid = 0;

			//XSSFCell cell = row.createCell(cellid++);

			row.createCell(cellid++).setCellValue((String) writereport.getTestCaseId());
			row.createCell(cellid++).setCellValue((String) writereport.getStep());
			row.createCell(cellid++).setCellValue((String) writereport.getStatus());
			row.createCell(cellid++).setCellValue((String) writereport.getErrorMessage());

		}
		
		//Write the workbook in file system
	      FileOutputStream out;
		try {
			out = new FileOutputStream(new File("C://Users//sushant//Desktop//Neha//TestExecutionReport.xlsx"));
		
	      
	      workbook.write(out);
	      out.close();
	      System.out.println("Writesheet.xlsx written successfully");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return null;
	}

}
