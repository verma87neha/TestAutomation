package com.pojo;

public class TestCase {

	//Test cases
	
	String tc;
	String testStep;
	String desc;
	String action;
	String objectRepo;
	String input;
	public String getTc() {
		return tc;
	}
	public void setTc(String tc) {
		this.tc = tc;
	}
	public String getTestStep() {
		return testStep;
	}
	public void setTestStep(String testStep) {
		this.testStep = testStep;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getObjectRepo() {
		return objectRepo;
	}
	public void setObjectRepo(String objectRepo) {
		this.objectRepo = objectRepo;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	@Override
	public String toString() {
		return "TestCase [tc=" + tc + ", testStep=" + testStep + ", desc=" + desc + ", action=" + action
				+ ", objectRepo=" + objectRepo + ", input=" + input + "]";
	}
	
	
}
