package com.TestScenarios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	HashMap<String, String> valuesMeta;
	String filePath;
	String TestData;
	String TestCase;
	String ObjectRepository;

	public HashMap<String, String> readproperties() {
		try {
			valuesMeta = readProp.getPropValues();
			filePath = valuesMeta.get("filePath");
			TestData = valuesMeta.get("TestData");
			TestCase = valuesMeta.get("TestCase");
			ObjectRepository = valuesMeta.get("ObjectRepository");
			return valuesMeta;
		} catch (IOException e) {

			e.printStackTrace();
			return null;
		}

	}

	@Test(dataProvider = "testData")
	public void login(String TestcaseID, String URL, String data) {

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

	/*
	 * @Test(dataProvider = "ObjectRepository") public void objectRepo( String
	 * field_Name, String field_Value, String error_Msg, String xpath,String
	 * mandatory, String type) {
	 * 
	 * //attr.setField_Id(field_Id); attr.setField_Name(field_Name);
	 * attr.setField_Value(field_Value); attr.setError_Msg(error_Msg);
	 * attr.setMandatory(mandatory); attr.setXpath(xpath); attr.setInput(type);
	 * 
	 * }
	 */

	/*
	 * @Test(dataProvider = "testCases") public void testCase( String tc, String
	 * testStep, String desc, String action,String objectvalue, String input) {
	 * 
	 * attr.setField_Id(tc); attr.setField_Name(testStep);
	 * attr.setField_Value(desc); attr.setError_Msg(action);
	 * attr.setObjectRepo(objectvalue); attr.setMandatory(input);
	 * 
	 * }
	 */

	private void action(String data1) {
		ObjectRepository();
		testCases();
		driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input")).sendKeys(data1);
		driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[3]/center/input[1]")).click();
	}

	@DataProvider
	public Object[][] testData() {
		readproperties();

		Object[][] testdata = exlReader.readExcel(filePath, TestData, "TestSuit1");

		return testdata;

	}

	// @DataProvider
	public Object[][] ObjectRepository() {
		Object[][] objectRepo;
		int row = 2;
		int column = 6;
		readproperties();

		objectRepo = exlReader.readExcel(filePath, ObjectRepository, "TestSuit1");

		return objectRepo;
	}

	// @DataProvider
	public Object[][] testCases() {
		Object[][] testcase;
		int row = 4;
		int column = 6;

		Set<Object> tcSet = new HashSet<>();
		readproperties();
		List<Object> testCaseList = new ArrayList<Object>();
		testcase = exlReader.readExcel(filePath, TestCase, "TestSuit1");
		for (int i = 0; i < row; i++) {
			testCaseList.add(testcase[i][0]);
		}
		for (Object t : testCaseList) {
			tcSet.add(t);

		}
		System.out.println("value in set " + tcSet.toString());
		return testcase;
	}

	/*
	 * @Test(dataProvider = "testData") public String[][] readTestData(String sno,
	 * String tc, String URL, String data1) {
	 * 
	 * System.out.println("values " + sno + " " + tc + " " + URL + " " + data1);
	 * return null; }
	 */

	/*
	 * @Test public void quit() { BrowserFactory.closeAllDriver(); }
	 */
}
