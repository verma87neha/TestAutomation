
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

	public Object[][] readExcel(String filePath, String fileName, String sheetName) {
		File file = new File(filePath + "//" + fileName);
		String[][] tabArray = null;

		try {

			FileInputStream ExcelFile = new FileInputStream(file);

			// Access the required test data sheet

			ExcelWBook = new XSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheet(sheetName);

			int startCol = 1;
			int startRow = 1;
			int ci = 0, cj = 0;

			int totalRows = ExcelWSheet.getLastRowNum() + 1;
			System.out.println("value of total rows " + totalRows);
			int totalCols = ExcelWSheet.getRow(0).getLastCellNum()-1;
			System.out.println("value of total columns " + totalCols);

			tabArray = new String[totalRows][totalCols];

			// for (int j=startCol;j<=totalCols;j++, cj++,ci++)
			for (int i = startRow; i < totalRows; i++, ci++)
				for (int j = startCol; j <= totalCols; j++, cj++) {

					// tabArray[ci][cj]=getCellData(ci,j);
					tabArray[ci][cj] = getCellData(i, j);
					// System.out.println("value of row and column " +ci+" "+cj+" " +
					// tabArray[ci][cj]);
					// System.out.println("value of row and column " +i+" "+j+" " + tabArray[i][j]);
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
}
