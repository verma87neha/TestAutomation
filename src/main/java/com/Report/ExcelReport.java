package com.Report;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.Utility.ExcelParser;
import com.pojo.Report;

public class ExcelReport implements Reports {
	 
	ExcelParser ep = new ExcelParser();
	public  LinkedHashMap<String, Report> testRep = new LinkedHashMap<String, Report> ();
	
	public  void generateReport(List<Report> testReport) {
		System.out.println(" print report");
		for(Report reports: testReport) {
			System.out.println(reports.toString());
		}
		
		
		ep.writeReport("", "", testReport);
	}

	public  LinkedHashMap<String, Report> createReport(String testCaseId, String step, String errorMessage,
			String status) {
		Report reports = new Report();
		reports.setTestCaseId(testCaseId);
		reports.setErrorMessage(errorMessage);
		reports.setStep(step);
		reports.setStatus(status);
		System.out.println("Value in report " + reports.toString());
		testRep.put(step, reports);

		return testRep;

	}

	public  void takeSnapShot(WebDriver driver, String destinaltio, String tc_id, String step) {
		// Convert web driver object to TakeScreenshot
		destinaltio = destinaltio + tc_id + step + ".png";
		TakesScreenshot scrShot = ((TakesScreenshot) driver);

		// Call getScreenshotAs method to create image file

		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

		// Move image file to new destination

		File DestFile = new File(destinaltio);
		boolean screenshotTaken = DestFile.exists();
		System.out.println(" Screen shot " + screenshotTaken);
		// Copy file at destination

		// FileUtils.copyFile(SrcFile, DestFile);

	}

	@Override
	public void generateReports(LinkedHashMap<String, List<Report>> testReport) {
		// TODO Auto-generated method stub

	}

}
