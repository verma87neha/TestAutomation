package com.Report;

import java.util.LinkedHashMap;
import java.util.List;

import com.pojo.Report;

public interface Reports {

	public void generateReports(LinkedHashMap<String,List<Report>> testReport);
	
	
}
