package com.keywordImp;

import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.TestScenarios.TestScript;

public class DropDown implements UIkeyword  {
	private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
	@Override
	public String keywordAction(String input, String type, String errorMsg, String by) {
		Select selectedItem;
		
		if(type.equals("name"))
		{
		selectedItem = new Select(TestScript.driver.findElement(By.name(by)));
		if(pattern.matcher(input).matches())
		{
		selectedItem.selectByIndex(Integer.parseInt(input));
		}
		else {
		selectedItem.selectByVisibleText(input);
		}
		}
		else if(type.equals("id"))
		{
			selectedItem = new Select(TestScript.driver.findElement(By.id(by)));
			
			if(pattern.matcher(input).matches())
			{
			selectedItem.selectByIndex(Integer.parseInt(input));
			}
			else {
			selectedItem.selectByVisibleText(input);
			}
		}
		else {
			System.out.println("Incorrect selection");
		}
		
		return "True";
	}

}
