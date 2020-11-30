package com.TestScenarios;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Utility.ExcelParser;
import com.Utility.ReadProperties;

public class TestScript {
	ExcelParser exlReader = new ExcelParser();
	ReadProperties readProp = new ReadProperties();
	TestAtrributes attr = new TestAtrributes();
	public static WebDriver driver = new ChromeDriver();

	@Test(dataProvider = "testData")
	public void login(String TestcaseID, String URL, String data, String x) {

		attr.setTestcaseID(TestcaseID);
		attr.setURL(URL);
		attr.setData(data);
		testcaseExe();
	}

	private void testcaseExe() {
		driver.get(attr.getURL());
		driver.manage().window().maximize();
		action(attr.getData());
		driver.close();
	}

	@Test(dataProvider = "ObjectRepository")
	public void objectRepo(String field_Id,String field_Name,String field_Value,String error_Msg,String xpath,
			String mandatory,String type) {
		
		attr.setField_Id(field_Id);
		attr.setField_Name(field_Name);
		attr.setField_Value(field_Value);
		attr.setError_Msg(error_Msg);
		attr.setMandatory(mandatory);
		attr.setXpath(xpath);
		attr.setInput(type);
	}
	
	
	private void action(String data1) {
		driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input")).sendKeys(data1);
		driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[3]/center/input[1]")).click();
	}

	@DataProvider
	public Object[][] testData() {
		try {
			HashMap<String, String> valuesMeta = readProp.getPropValues();
			String filePath = valuesMeta.get("filePath");
			String TestData = valuesMeta.get("TestData");
			String TestCase = valuesMeta.get("TestData");
			String ObjectRepository = valuesMeta.get("TestData");

			Object[][] data = exlReader.readExcel(filePath, TestData, "TestSuit1");

			return data;
		} catch (IOException e) {

			e.printStackTrace();
			return null;
		}

	}

	@DataProvider
	public Object[][] ObjectRepository() {
		Object[][] data;
		HashMap<String, String> valuesMeta;
		try {
			valuesMeta = readProp.getPropValues();

			String filePath = valuesMeta.get("filePath");
			String TestData = valuesMeta.get("TestData");
			String TestCase = valuesMeta.get("TestData");
			String ObjectRepository = valuesMeta.get("TestData");

			data = exlReader.readExcel(filePath, ObjectRepository, "TestSuit1");

		} catch (IOException e) {

			e.printStackTrace();
			return null;
		}
		return data;
	}

	@Test(dataProvider = "testData")
	public String[][] readTestData(String sno, String tc, String URL, String data1) {

		System.out.println("values " + sno + " " + tc + " " + URL + " " + data1);
		return null;
	}
	/*
	 * @Test public void quit() { BrowserFactory.closeAllDriver(); }
	 */
}
