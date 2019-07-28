package com.example.demo.utils;

import java.util.HashMap;

public class ConstantsUtil {
	
	public static final String IDSITE_OR_SYSCODE_IS_NULL = "0011";
	
	public static final String BUTTONCH_IS_NULL = "0012";
	
	public static final String STARTTIME_IS_NULL = "0013";
	
	public static final String ENDTIME_IS_NULL = "0014";
	
	public static final String IDSITE_IS_NOT_NUMBER = "0015";
	
	public static final String BUTTONCH_IS_ERROR = "0016";
	
	public static final HashMap<String, String> ERROR_MAP = new HashMap<String, String>();
	
	static {
		ERROR_MAP.put(IDSITE_OR_SYSCODE_IS_NULL, "系统对照码和系统简称不可同时为空");
		ERROR_MAP.put(BUTTONCH_IS_NULL, "菜单名称不可为空");
		ERROR_MAP.put(STARTTIME_IS_NULL, "开始时间不可为空");
		ERROR_MAP.put(ENDTIME_IS_NULL, "结束时间不可为空");
		ERROR_MAP.put(IDSITE_IS_NOT_NUMBER, "系统对照码必须是数字类型");
		ERROR_MAP.put(BUTTONCH_IS_ERROR, "菜单名称的格式不对");
	}

}
