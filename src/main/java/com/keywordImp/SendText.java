package com.keywordImp;

import org.openqa.selenium.By;

import com.TestScenarios.TestScript;

public class SendText implements UIkeyword{

	@Override
	public String keywordAction(String input, String type, String errorMsg, String by) {
		if(TestScript.driver.findElement(By.xpath(by)).isEnabled()&&TestScript.driver.findElement(By.xpath(by)).isDisplayed())
		{
		TestScript.driver.findElement(By.xpath(by)).sendKeys(input);
		}
		return "True";
	}

}
