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
import com.pojo.TestCase;
import com.pojo.TestData;

public class TestScript {
	ExcelParser exlReader = new ExcelParser();
	ReadProperties readProp = new ReadProperties();
	TestAtrributes attr = new TestAtrributes();
	TestData tData = new TestData();

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

		tData.setTestcaseID(TestcaseID);
		tData.setURL(URL);
		tData.setData(data);
		testcaseExe();
	}

	private void testcaseExe() {
		driver.get(tData.getURL());
		driver.manage().window().maximize();
		ObjectRepository();
		testCases();
		//action(tData.getData());
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

	private void uiAction(String action,String object,String input) {
		if(action.equalsIgnoreCase("sendKeys"))
		{
		driver.findElement(By.xpath("object")).sendKeys(input);
		}
		
		if(action.equalsIgnoreCase("click"))
		{
		driver.findElement(By.xpath("object")).click();
		}
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
	public String[][] testCases() {
		String[][] testcase;
		TestCase tCase;
		List<TestCase> TCList = new ArrayList<TestCase>();
		int row = 4;
		int column = 6;

		Set<String> tcSet = new HashSet<>();
		readproperties();
		List<String> testCaseList = new ArrayList<String>();
		testcase = (String[][]) exlReader.readExcel(filePath, TestCase, "TestSuit1");
		for (int i = 0; i < row; i++) {
			testCaseList.add(testcase[i][0]);
		}

		System.out.println("Value of Test cases  " + TCList.toString());
		for (String t : testCaseList) {
			tcSet.add(t);

		}

		for (int i = 0, j = 0; i < row; i++)

		{
			tCase = new TestCase();
			tCase.setTc(testcase[i][j]);
			tCase.setTestStep(testcase[i][j + 1]);
			tCase.setDesc(testcase[i][j + 1]);
			tCase.setAction(testcase[i][j + 2]);
			tCase.setObjectRepo(testcase[i][j + 3]);
			tCase.setInput(testcase[i][j + 4]);
			TCList.add(tCase);
		}

		HashMap<String, List<TestCase>> testScenarios = new HashMap<String, List<TestCase>>();

		for (String scenarios : tcSet)

		{
			List<TestCase> tempList = new ArrayList<TestCase>();
			for (int i = 0; i < TCList.size(); i++) {

				if ((TCList.get(i).getTc()).equals(scenarios))

				{
					System.out.println("correct values " + TCList.get(i).getTc() + " " + scenarios);

					tempList.add(TCList.get(i));

					System.out.println(" print list" + TCList.get(i).getTestStep() + " " + scenarios);
				}

			}
			testScenarios.put(scenarios, tempList);
		}

		System.out.println("Print Test case set");
		for (String scenarios : tcSet) {
			System.out.println(" value in hashmap " + testScenarios.get(scenarios).toString());
			for(int k = 0; k < testScenarios.get(scenarios).size();k++)
			{
			String action =	testScenarios.get(scenarios).get(k).getAction();
			//String object =	testScenarios.get(scenarios).get(k).getObjectRepo();
			String object =	getObjectValue();
			String input =	testScenarios.get(scenarios).get(k).getInput();
			
			uiAction(action,object,input);
			}
		}
		return testcase;
	}

	private String getObjectValue() {
		// TODO Auto-generated method stub
		return null;
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
