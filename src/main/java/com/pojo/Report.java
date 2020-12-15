package com.pojo;

public class Report {

	String TestCaseId;
	String step;
	String Screenshot;
	String ErrorMessage;
	String status;
	public String getTestCaseId() {
		return TestCaseId;
	}
	public void setTestCaseId(String testCaseId) {
		TestCaseId = testCaseId;
	}
	
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public String getScreenshot() {
		return Screenshot;
	}
	public void setScreenshot(String screenshot) {
		if(screenshot.isEmpty())
		{
		Screenshot = "Blank";}
		else
			Screenshot = screenshot;
	}
	public String getErrorMessage() {
		return ErrorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Report [TestCaseId=" + TestCaseId + ", step=" + step + ", ErrorMessage=" + ErrorMessage + ", status="
				+ status + "]";
	}
	
	
}
