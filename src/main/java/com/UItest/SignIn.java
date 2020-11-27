package com.UItest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.Utility.ObjectRepository;
import com.Utility.Utilities;

public class SignIn {
	
	SoftAssert softassert = new SoftAssert();
	ObjectRepository constant = new ObjectRepository();
	Utilities util = new Utilities();
	
	public void signIn(WebDriver driver, String email, String pwd)
	{
		util.sendKeys(driver, constant.email_signin, email);
		util.sendKeys(driver, constant.password_signin, pwd);
		util.click(driver, constant.SignIn);
		
	}

}
