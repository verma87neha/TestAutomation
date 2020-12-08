package com.keywordImp;

import org.openqa.selenium.By;

import com.TestScenarios.TestScript;

public class Navigate implements UIkeyword{

	@Override
	public String keywordAction(String input, String type, String errorMsg, String by) {
		//System.out.println("value of url "+by);
		TestScript.driver.navigate().to(input);
		return "True";
	}

}