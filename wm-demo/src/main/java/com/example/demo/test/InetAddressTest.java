package com.example.demo.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class InetAddressTest {
	public static void main(String[] args) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
		System.out.println(dateFormat.format(new Date()));
		System.out.println(dateFormat1.format(new Date()));
		 Calendar calendar = Calendar.getInstance();
	        // 2014-12-31
	        calendar.set(2014, Calendar.DECEMBER, 31);
	        Date strDate1 = calendar.getTime();
	        // 2015-01-01
	        calendar.set(2015, Calendar.JANUARY, 1);
	        Date strDate2 = calendar.getTime();
	        // 大写YYYY
	        DateFormat formatUpperCase = new SimpleDateFormat("YYYY/MM/dd");
	        System.out.println("2014-12-31 to YYYY/MM/dd: " + formatUpperCase.format(strDate1)); //2014-12-31 to YYYY/MM/dd: 2015/12/31
	        System.out.println("2015-01-01 to YYYY/MM/dd: " + formatUpperCase.format(strDate2)); //2015-01-01 to YYYY/MM/dd: 2015/01/01
	        // 小写YYYY
	        DateFormat formatLowerCase = new SimpleDateFormat("yyyy/MM/dd");
	        System.out.println("2014-12-31 to yyyy/MM/dd: " + formatLowerCase.format(strDate1)); //2014-12-31 to yyyy/MM/dd: 2014/12/31
	        System.out.println("2015-01-01 to yyyy/MM/dd: " + formatLowerCase.format(strDate2)); //2015-01-01 to yyyy/MM/dd: 2015/01/01
	}
}
