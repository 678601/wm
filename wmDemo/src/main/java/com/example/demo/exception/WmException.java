package com.example.demo.exception;

/**
 * 
 * @ClassName: WmException
 * @Description: 系统自定义异常
 * @author LWM
 * @date 2020-12-09 03:57:41
 */
public class WmException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WmException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public WmException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
