package com.keywordImp;

import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import com.Report.ExcelReport;
import com.TestScenarios.TestScript;
import com.Utility.Constants;
import com.pojo.Report;

public class Click implements UIkeyword {
	ExcelReport reports = new ExcelReport();

	@Override
	public LinkedHashMap<String, Report> keywordAction(String tc_id, String step, String input, String type,
			String errorMsg, String by, String status) {
		LinkedHashMap<String, Report> stepReport;
		try {

			TestScript.driver.findElement(By.xpath(by)).click();
			status = "Pass";
			System.out.println("print status " + status);
			errorMsg = Constants.clickSuccessMsg;
			stepReport = reports.createReport(tc_id, step, errorMsg, status);
			return stepReport;
		} catch (NoSuchElementException e) {
			status = "Fail";
			// Capture screenshot
			System.out.println("print status " + status);
			//reports.takeSnapShot(TestScript.driver, "C://Users//sushant//Desktop//Neha//ErrorScreenShots//", tc_id,
			//		step);
			stepReport = reports.createReport(tc_id, step, errorMsg, status);
			return stepReport;
		}
	}

}
