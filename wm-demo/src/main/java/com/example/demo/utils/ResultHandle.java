package com.example.demo.utils;
/**
 * 異常處理枚舉
 * @author LiWenming
 * 2019年5月14日下午9:33:03
 */
public enum ResultHandle {
	
	// 定义枚举中的常量
	SYSCODEISNULL("0001", "系統編碼不可為空"), STARTTIMEISNULL("0002", "開始時間不可爲空");

	private String code;
	private String value;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String name) {
		this.value = name;
	}

	private ResultHandle(String code, String value) {
		// TODO Auto-generated constructor stub
		this.code = code;
		this.value = value;
	}
}
