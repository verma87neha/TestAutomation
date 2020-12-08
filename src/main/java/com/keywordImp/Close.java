package com.keywordImp;

import com.TestScenarios.TestScript;

public class Close implements UIkeyword {

	@Override
	public String keywordAction(String input, String type, String errorMsg, String by) {
		TestScript.driver.close();
		return "True";
	}

}

