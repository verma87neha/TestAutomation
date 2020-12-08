package com.keywordImp;

public class Keywordfactory {

	String action;

	public UIkeyword keyFactory(String action) {
		UIkeyword uiKey = null;
		if (action.equalsIgnoreCase("Click")) {
			uiKey = new Click();
		}

		if (action.equalsIgnoreCase("SendKeys")) {
			uiKey = new SendText();
		}
		
		if (action.equalsIgnoreCase("navigate")) {
			uiKey = new Navigate();
		}
		
		if (action.equalsIgnoreCase("close")) {
			uiKey = new Close();
		}

		return uiKey;
	}
}
