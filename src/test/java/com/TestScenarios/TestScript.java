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
public static WebDriver driver = new ChromeDriver();
	

	@Test(dataProvider = "testData")
	public void login(String tc, String URL,String data1,String x) {
		System.out.println("values in login"+ " "+  tc +" "+ URL+ " "+ data1 );
		driver.get(URL);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input")).sendKeys(data1);
		driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[3]/center/input[1]")).click();
		driver.close();
	}

@DataProvider
public Object[][] testData()
{try {
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

@Test(dataProvider = "testData")
public String[][] readTestData(String sno, String tc,String URL, String data1 ) {
	
	System.out.println("values "+ sno +" "+  tc +" "+ URL+ " "+ data1 );
	return null;
}
	/*
	 * @Test public void quit() { BrowserFactory.closeAllDriver(); }
	 */
}
