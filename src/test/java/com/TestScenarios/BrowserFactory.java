package com.TestScenarios;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public  class BrowserFactory {
 
 private static Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();
 
 /*
 * Factory method for getting browsers
 */
 public static WebDriver getBrowser(String browserName) {
 WebDriver driver = null;
 
 switch (browserName) { 

 
 case "Chrome":
 driver = drivers.get("Chrome");
 if (driver == null) {
 System.setProperty("webdriver.chrome.driver", "C:\\Users\\sushant\\Desktop\\Neha\\chromedriver_win32\\chromedriver.exe");
 driver = new ChromeDriver();
 drivers.put("Chrome", driver);
 }
 break;
 }
 return driver;
 }
 
 public static void closeAllDriver() {
 for (String key : drivers.keySet()) {
 drivers.get(key).close();
 drivers.get(key).quit();
 }
 }
}

