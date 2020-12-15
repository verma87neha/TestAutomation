package com.Utility;

import java.util.List;

import com.pojo.Report;

public interface GenericParser {

	
	public Object[][] readInput(String filePath, String fileName, String testSuit) ;
	public Object[][] writeReport(String filePath, String fileName, List<Report> report) ;
}
