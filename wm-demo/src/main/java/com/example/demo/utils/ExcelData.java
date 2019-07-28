package com.example.demo.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ExcelData implements Serializable {
	
	private static final long serialVersionUID = 4444017239100620999L;
	// 中文列名
    private String []titles;
    // Map的key
    private String []keys;
    //数据
    private List<Map<String,String>> list;
    //sheetName
    private String sheetName;
    
	public String[] getTitles() {
		return titles;
	}
	public void setTitles(String[] titles) {
		this.titles = titles;
	}
	public String[] getKeys() {
		return keys;
	}
	public void setKeys(String[] keys) {
		this.keys = keys;
	}

	public List<Map<String, String>> getList() {
		return list;
	}
	public void setList(List<Map<String, String>> list) {
		this.list = list;
	}
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
}
