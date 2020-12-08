package com.keywordImp;

import org.openqa.selenium.By;

import com.TestScenarios.TestScript;

public class CheckBox implements UIkeyword{

	@Override
	public String keywordAction(String input, String type, String errorMsg, String by) {
		if(!TestScript.driver.findElement(By.xpath(by)).isSelected()&&input.equalsIgnoreCase("Blank"))
		{
		TestScript.driver.findElement(By.xpath(by)).click();
		}
		
		else if(!TestScript.driver.findElement(By.xpath(by)).isSelected())
		{//input.split;//pipe delimited value
			by = by.substring(0, 10)+input+by.substring(12, 14);
			TestScript.driver.findElement(By.xpath(by)).click();
		}
		return "True";
	}

}
