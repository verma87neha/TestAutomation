package com.pojo;

public class TestData {

	//TestData proprties
		String testdata_Sno;
		String testcaseID;
		String URL;
		String data;
		public String getTestdata_Sno() {
			return testdata_Sno;
		}
		public void setTestdata_Sno(String testdata_Sno) {
			this.testdata_Sno = testdata_Sno;
		}
		public String getTestcaseID() {
			return testcaseID;
		}
		public void setTestcaseID(String testcaseID) {
			this.testcaseID = testcaseID;
		}
		public String getURL() {
			return URL;
		}
		public void setURL(String uRL) {
			URL = uRL;
		}
		public String getData() {
			return data;
		}
		public void setData(String data) {
			this.data = data;
		}
		@Override
		public String toString() {
			return "TestData [testdata_Sno=" + testdata_Sno + ", testcaseID=" + testcaseID + ", URL=" + URL + ", data="
					+ data + "]";
		}
		
		
}
