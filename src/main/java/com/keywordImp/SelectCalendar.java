package com.keywordImp;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.TestScenarios.TestScript;

public class SelectCalendar implements UIkeyword {

	@Override
	public String keywordAction(String input, String type, String errorMsg, String by) {
		String[] inputs = input.split("\\|");
		String[] bys = by.split("\\|");
		String year = inputs[0];
		String month = inputs[1];
		String date = inputs[2];
		String dateXpath= bys[3].substring(0, 23)+date+bys[3].substring(25,28);
		System.out.println("value of dateXpath" + dateXpath);
		TestScript.driver.findElement(By.xpath(bys[0])).click();

		
			
		new Select(TestScript.driver.findElement(By.xpath(bys[1]))).selectByVisibleText(month);
		new Select(	TestScript.driver.findElement(By.xpath(bys[2]))).selectByVisibleText(year);
		
		TestScript.driver.findElement(By.xpath(dateXpath)).click();
		
		return "True";
	}

	
}
