package com.keywordImp;

import com.TestScenarios.TestScript;

public class Aletrs implements UIkeyword{

	@Override
	public String keywordAction(String input, String type, String errorMsg, String by) {
		String response = null;
		if(input.equalsIgnoreCase("dismiss"))
		{
			TestScript.driver.switchTo().alert().dismiss();
		}
		
		if(input.equalsIgnoreCase("accept"))
		{
			TestScript.driver.switchTo().alert().accept();
		}
		
		if(input.equalsIgnoreCase("getText"))
		{
			response = TestScript.driver.switchTo().alert().getText();
		}
		
		if(input.equalsIgnoreCase("sendText"))
		{
			 TestScript.driver.switchTo().alert().sendKeys(input);
		}
		if(!response.isEmpty())
		{
			return response;
		}
		else 
		{
		return "True";
		}
	}

}
