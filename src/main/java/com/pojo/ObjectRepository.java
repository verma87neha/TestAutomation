package com.pojo;

public class ObjectRepository {

	//Object Repository
		String field_Id;
		String field_Name;
		String field_Value;
		String error_Msg;
		String mandatory;
		String type;
		String xpath;
		public String getField_Id() {
			return field_Id;
		}
		public void setField_Id(String field_Id) {
			this.field_Id = field_Id;
		}
		public String getField_Name() {
			return field_Name;
		}
		public void setField_Name(String field_Name) {
			this.field_Name = field_Name;
		}
		public String getField_Value() {
			return field_Value;
		}
		public void setField_Value(String field_Value) {
			this.field_Value = field_Value;
		}
		public String getError_Msg() {
			return error_Msg;
		}
		public void setError_Msg(String error_Msg) {
			this.error_Msg = error_Msg;
		}
		public String getMandatory() {
			return mandatory;
		}
		public void setMandatory(String mandatory) {
			this.mandatory = mandatory;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getXpath() {
			return xpath;
		}
		public void setXpath(String xpath) {
			this.xpath = xpath;
		}
		@Override
		public String toString() {
			return "ObjectRepository [field_Id=" + field_Id + ", field_Name=" + field_Name + ", field_Value="
					+ field_Value + ", error_Msg=" + error_Msg + ", mandatory=" + mandatory + ", type=" + type
					+ ", xpath=" + xpath + "]";
		}
		
		
}
