package com.TestScenarios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.map.LinkedMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Utility.ExcelParser;
import com.Utility.ReadProperties;
import com.pojo.ObjectRepository;
import com.pojo.TestCase;
import com.pojo.TestData;

public class TestScript {
	ExcelParser exlReader = new ExcelParser();
	ReadProperties readProp = new ReadProperties();
	TestAtrributes attr = new TestAtrributes();
	TestData tData = new TestData();

	public static WebDriver driver = new ChromeDriver();

	String[][] objectRepo;
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

		testCases();
		// action(tData.getData());
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

	private void uiAction(String action, String objectRepo, String input, ObjectRepository repository) {
		// String xpath = objectRepo;
		System.out.println(" values fetch " + action + " " + objectRepo + " " + input + " " + repository.toString());
		if (action.equalsIgnoreCase("sendKeys")) {
			driver.findElement(By.xpath(objectRepo)).sendKeys(input);

		}

		if (action.equalsIgnoreCase("click")) {
			driver.findElement(By.xpath(objectRepo)).click();

		}
		
		if (action.equalsIgnoreCase("navigate")) {
			driver.navigate().to(objectRepo);

		}
		if (action.equalsIgnoreCase("close")) {
			driver.close();

		}
	}

	@DataProvider
	public Object[][] testData() {
		readproperties();

		Object[][] testdata = exlReader.readExcel(filePath, TestData, "TestSuit1");

		return testdata;

	}

	// @DataProvider
	public String[][] ObjectRepository() {

		int row = 3;
		int column = 6;
		readproperties();

		objectRepo = (String[][]) exlReader.readExcel(filePath, ObjectRepository, "TestSuit1");

		return objectRepo;
	}

	// @DataProvider
	public String[][] testCases() {
		String[][] testcase;
		String[][] objects;
		TestCase tCase;
		ObjectRepository objectValue;
		List<TestCase> tcList = new ArrayList<TestCase>();
		List<ObjectRepository> objectList = new ArrayList<ObjectRepository>();
		int row = 4;
		int objectRow = 3;
		int column = 6;

		Set<String> tcSet = new HashSet<>();
		readproperties();
		List<String> testCaseList = new ArrayList<String>();
		testcase = (String[][]) exlReader.readExcel(filePath, TestCase, "TestSuit1");
		for (int i = 0; i < row; i++) {
			testCaseList.add(testcase[i][0]);
		}
		objects = ObjectRepository();

		for (String t : testCaseList) {
			tcSet.add(t);

		}
		for (int k = 0, j = 0; k < objectRow; k++) {
			objectValue = new ObjectRepository();
			objectValue.setField_Name(objects[k][j]);
			objectValue.setField_Value(objects[k][j + 1]);
			objectValue.setError_Msg(objects[k][j + 2]);
			objectValue.setXpath(objects[k][j + 3]);
			objectValue.setMandatory(objects[k][j + 4]);
			objectValue.setType(objects[k][j + 5]);
			System.out.println(" Value of Object repo " + objectValue.toString());
			objectList.add(objectValue);
		}
		for (int i = 0, j = 0; i < row; i++)

		{
			tCase = new TestCase();

			tCase.setTc(testcase[i][j]);
			tCase.setTestStep(testcase[i][j + 1]);
			tCase.setDesc(testcase[i][j + 2]);
			tCase.setAction(testcase[i][j + 3]);
			tCase.setObjectRepo(testcase[i][j + 4]);
			tCase.setInput(testcase[i][j + 5]);

			tcList.add(tCase);

		}

		Map<String, List<TestCase>> testScenarios = new LinkedMap<String, List<TestCase>>();

		for (String scenarios : tcSet)

		{
			List<TestCase> tempList = new ArrayList<TestCase>();
			for (int i = 0; i < tcList.size(); i++) {

				if ((tcList.get(i).getTc()).equals(scenarios))

				{
					System.out.println("correct values " + tcList.get(i).getTc() + " " + scenarios);

					tempList.add(tcList.get(i));

					System.out.println(" print list" + tcList.get(i).getTestStep() + " " + scenarios);
				}

			}
			testScenarios.put(scenarios, tempList);
		}

		System.out.println("Print Test case set");
		ObjectRepository objectLocator = new ObjectRepository();
		for (String scenarios : tcSet) {
			System.out.println(" value in hashmap " + testScenarios.get(scenarios).toString());
			for (int k = 0; k < testScenarios.get(scenarios).size(); k++) {
				String action = testScenarios.get(scenarios).get(k).getAction();
				String object = testScenarios.get(scenarios).get(k).getObjectRepo();
				objectLocator = getObjectValue(object, objectList);
				String input = testScenarios.get(scenarios).get(k).getInput();
				if (!objectLocator.equals(null)) {
					uiAction(action, objectLocator.getXpath(), input, objectLocator);
				} else {
					System.out.println(" Incorrect locator");
				}
			}
		}
		return testcase;
	}

	private ObjectRepository getObjectValue(String object, List<ObjectRepository> objectList) {
		ObjectRepository objectInfo = new ObjectRepository();
		for (int i = 0; i < objectList.size(); i++) {
			if (object.equalsIgnoreCase(objectList.get(i).getField_Name())) {
				objectInfo = objectList.get(i);
				break;
			}
		}
		return objectInfo;
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
