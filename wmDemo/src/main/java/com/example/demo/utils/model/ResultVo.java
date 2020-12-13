package com.example.demo.utils.model;
/**
 * 
 * @ClassName: ResultVo
 * @Description: 统一的返回实体
 * @author LWM
 * @date 2020-12-09 03:54:11
 */
public class ResultVo {
	private int code;
	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ResultVo [code=" + code + ", message=" + message + "]";
	}

}
