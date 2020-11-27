package com.Utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Utilities {

	
	/**
	 * @param driver
	 * @return
	 */
	public boolean isDisplay(WebDriver driver,String locator) {
		return driver.findElement(By.xpath(locator)).isDisplayed();
	}
	
	/**
	 * @param driver
	 * @param email
	 */
	public void sendKeys(WebDriver driver, String locator,String value) {
		if (locator.contains("//"))
		{
		driver.findElement(By.xpath(locator)).sendKeys(value);
		}
		else
		{
			driver.findElement(By.name(locator)).sendKeys(value);
		}
	}
	
	/**
	 * @param driver
	 */
	public void click(WebDriver driver, String locator ) {
		//driver.findElement(By.name(locator)).click();
		if (locator.contains("//"))
		{
		driver.findElement(By.xpath(locator)).click();
		}
		
		else {
			driver.findElement(By.name(locator)).click();
		}
	}

	public void radio(WebDriver driver, String locator) {
		
		driver.findElement(By.id(locator)).click();
	}

	public void select(WebDriver driver, String locator,String value) {
	Select dropdown = new Select(driver.findElement(By.name(locator)));
	dropdown.selectByValue(value);//.selectByVisibleText(value);
	
	}
	
	public void selectbyId(WebDriver driver, String locator,int value) {
		Select dropdown = new Select(driver.findElement(By.name(locator)));
		dropdown.selectByIndex(value);//.selectByVisibleText(value);
		
		}

	public void clear(WebDriver driver, String locator) {
		driver.findElement(By.name(locator)).clear();
		
	}

	public void moveTo(WebDriver driver, String locator) {		
		
		WebElement ele = driver. findElement(By. xpath(locator));
		Actions action = new Actions(driver);
		action.moveToElement(ele).click();//.perform();
	}
	
}
