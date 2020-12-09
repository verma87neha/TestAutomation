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
		if (action.equalsIgnoreCase("RadioButton")) {
			uiKey = new RadioButton();
		}

		if (action.equalsIgnoreCase("CheckBox")) {
			uiKey = new CheckBox();
		}
		if (action.equalsIgnoreCase("DropDown")) {
			uiKey = new DropDown();
		}
		if (action.equalsIgnoreCase("Aletrs")) {
			uiKey = new Aletrs();
		}
		if (action.equalsIgnoreCase("Calendar")) {
			uiKey = new SelectCalendar();
		}
		if (action.equalsIgnoreCase("BrowseFile")) {
			uiKey = new BrowseFile();
		}
		
		return uiKey;
	}
}
