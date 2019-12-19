package com.example.demo.temp;

import org.springframework.util.StringUtils;

public class TestReplace {
	public static void main(String[] args) {
		String s = "<<<";
		cleanXSS(s);
	}
	private static String cleanXSS(String valueP) {
		if (StringUtils.isEmpty(valueP)) {
			return valueP;
		}
		// You'll need to remove the spaces from the html entities below
		String value = valueP.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
		value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
		value = value.replaceAll("'", "& #39;");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		value = value.replaceAll("script", "");
		System.out.println("------"+value);
		return value;
	}
}
