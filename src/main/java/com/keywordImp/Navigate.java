package com.keywordImp;

import org.openqa.selenium.By;

import com.TestScenarios.TestScript;

public class Navigate implements UIkeyword {

	@Override
	public String keywordAction(String input, String type, String errorMsg, String by) {
		
		if (input.equalsIgnoreCase("Blank")) {
			TestScript.driver.navigate().to(by);
		} 
		else if (input.equalsIgnoreCase("forward")) {
			TestScript.driver.navigate().forward();
		}

		else if (input.equalsIgnoreCase("back")) {
			TestScript.driver.navigate().back();
		}

		else if (input.equalsIgnoreCase("refresh")) {
			TestScript.driver.navigate().refresh();
		}
		
		else
		{
			System.out.println("Incorrect input");
		}
		return "True";
	}

}