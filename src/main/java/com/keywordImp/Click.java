package com.keywordImp;

import org.openqa.selenium.By;

import com.TestScenarios.TestScript;

public class Click implements UIkeyword {

	@Override
	public String keywordAction(String input, String type, String errorMsg, String by) {

		TestScript.driver.findElement(By.xpath(by)).click();

		return "True";
	}

}
