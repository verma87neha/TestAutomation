package com.keywordImp;

import java.util.LinkedHashMap;

import com.pojo.Report;

public interface UIkeyword {
 public static final String action = "";
	public LinkedHashMap<String, Report> keywordAction(String input,String tc_id,String step,String type,String errorMsg,String by,String status);
}
