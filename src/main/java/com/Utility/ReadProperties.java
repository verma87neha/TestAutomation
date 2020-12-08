
package com.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

public class ReadProperties {

public String result = "";
public	String filePath ="";
public	String TestData ="";
public	String TestCase ="";
public	String ObjectRepository ="";

HashMap<String, String> properties = new HashMap<String, String>();
	public HashMap<String, String> getPropValues() throws IOException {

		try {
			Properties prop = new Properties();
			String propFileName = "C:\\Users\\sushant\\Desktop\\Neha\\workspace\\UITesting\\src\\main\\resources\\config.properties.txt";
			
			try (InputStream inputStream = new FileInputStream(new File(propFileName));/*ReadProperties.class.getClassLoader().getResourceAsStream(propFileName)*/) {
				System.out.println(inputStream);
				prop.load(inputStream);
			}
			Date time = new Date(System.currentTimeMillis());

			// get the property value and print it out
			properties.put("filePath", prop.getProperty("filePath"));
			properties.put("TestData", prop.getProperty("TestData"));
			properties.put("TestCase", prop.getProperty("TestCase"));
			properties.put("ObjectRepository", prop.getProperty("ObjectRepository"));
			properties.put("TestSuit", prop.getProperty("TestSuit"));
			properties.put("reader", prop.getProperty("reader"));
			
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} 
		return properties;
	}

}
