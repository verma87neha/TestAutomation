package com.TestScenarios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.map.LinkedMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Utility.ExcelParser;
import com.Utility.GenericParser;
import com.Utility.ReadProperties;
import com.Utility.ReaderFactory;
import com.keywordImp.Keywordfactory;
import com.keywordImp.UIkeyword;
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
	String TestSuit;
	String reader;
	GenericParser parser;
	public HashMap<String, String> readproperties() {
		try {
			valuesMeta = readProp.getPropValues();
			filePath = valuesMeta.get("filePath");
			TestData = valuesMeta.get("TestData");
			TestCase = valuesMeta.get("TestCase");
			TestSuit = valuesMeta.get("TestSuit");
			reader = valuesMeta.get("reader");
			ObjectRepository = valuesMeta.get("ObjectRepository");
			 parser = new ReaderFactory().readInputType(reader);
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

	}

	
	@DataProvider
	public Object[][] testData() {
		readproperties();

		Object[][] testdata = parser.readInput(filePath, TestData, TestSuit);

		return testdata;

	}

	// @DataProvider
	public String[][] ObjectRepository() {

		// int row = 3;
		// int column = 6;
		readproperties();

		objectRepo = (String[][]) parser.readInput(filePath, ObjectRepository, TestSuit);

		return objectRepo;
	}

	// @DataProvider
	public String[][] testCases() {

		int column = 6;

		readproperties();

		String[][] testcase = (String[][]) parser.readInput(filePath, TestCase,TestSuit);

		int row = testcase.length;
		Set<String> tcSet = new LinkedHashSet<>();
		for (int i = 0; i < row; i++) {
			tcSet.add(testcase[i][0]);
		}

		System.out.println(" Value of set " + tcSet.toString());
		String[][] objects = ObjectRepository();

		int objectRow = objects.length;
		List<ObjectRepository> objectList = new ArrayList<ObjectRepository>();
		for (int k = 0, j = 0; k < objectRow; k++) {
			ObjectRepository objectValue = new ObjectRepository();
			objectValue.setField_Name(objects[k][j]);
			objectValue.setField_Value(objects[k][j + 1]);
			objectValue.setError_Msg(objects[k][j + 2]);
			objectValue.setXpath(objects[k][j + 3]);
			objectValue.setMandatory(objects[k][j + 4]);
			objectValue.setType(objects[k][j + 5]);
			System.out.println(" Value of Object repo " + objectValue.toString());
			objectList.add(objectValue);
		}

		List<TestCase> tcList = new ArrayList<TestCase>();
		for (int i = 0, j = 0; i < row; i++)

		{
			TestCase tCase = new TestCase();

			tCase.setTc(testcase[i][j]);
			tCase.setTestStep(testcase[i][j + 1]);
			tCase.setDesc(testcase[i][j + 2]);
			tCase.setAction(testcase[i][j + 3]);
			tCase.setObjectRepo(testcase[i][j + 4]);
			tCase.setInput(testcase[i][j + 5]);

			tcList.add(tCase);

		}

		Map<String, List<TestCase>> testScenarios = new LinkedMap<String, List<TestCase>>();

		for (String scenarios : tcSet) {
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
				if (!action.equalsIgnoreCase("LAUNCH")) {
					Keywordfactory key = new Keywordfactory();
					UIkeyword keyword = key.keyFactory(action);

					if (!objectLocator.equals(null)) {
						
						keyword.keywordAction(input, objectLocator.getType(), objectLocator.getError_Msg(),
								objectLocator.getXpath());
					} else {
						System.out.println(" Incorrect locator");
					}
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
