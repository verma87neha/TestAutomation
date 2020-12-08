package com.Utility;

public class ReaderFactory {

	public GenericParser readInputType(String reader) {
		GenericParser gp = null;
		if(reader.equalsIgnoreCase("excel"))
		{
			gp = new ExcelParser();
		}
		
		if(reader.equalsIgnoreCase("YML"))
		{
			//gp = new ExcelParser();
		}
		return gp;
	}
}
