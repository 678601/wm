package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Test {
	private String s = "444";
//public static void main(String[] args) {
//	List<String> list = new ArrayList<String>();
//	list.add("1");
//	list.add("2");
//	test(list);
//	System.out.println(list);
//}
//
//private static void test(List<String> list) {
//	// TODO Auto-generated method stub
//	list=new ArrayList<String>();
//	list.add("a");
//	list.add("b");
//	System.out.println(list);
//}
//	public static void main(String[] args) {
//
//		// demo1
//		String str = new String("hello");
//		char[] chs = { 'w', 'o', 'r', 'l', 'd' };
//		change(str, chs);
//		System.out.println(str + " " + new String(chs));
//
//		// -------------------------------------------------
//		// demo2
//
//		StringBuffer sb = new StringBuffer("hello");
//		change(sb);
//		System.out.println(sb);
//
//	}
//
//	public static void change(StringBuffer sb) {
//		sb.append(" world");
////      sb.deleteCharAt(0);
//	}
//
//	public static void change(String str, char[] chs) {
//		str.replace('h', 'H');
//		chs[0] = 'W';
//	}
	 public static void main(String sgf[]) {
		 ListNode n = null;
//		 InnerClass inn = new InnerClass();
		 new Test().doInner();
		 BigDecimal d = new BigDecimal("2.3");
		 BigDecimal d1 = new BigDecimal(2.3);
		 System.out.println(d);
		 System.out.println(d1);
//		    new BigDecimal("2.3") ！=2.3;
	        StringBuffer a=new StringBuffer("A");
	        StringBuffer b=new StringBuffer("B");
	        operate(a,b);
	        System.out.println(a+"."+b);

	    }
	 public void doInner(){
	        InnerClass inner = new InnerClass();
	        inner.pp();
	    }

	    static void operate(StringBuffer x,StringBuffer y) {

	        x.append(y);
	        y=x;

	    }
	    class InnerClass{
	    	public void pp() {
	    		System.out.println(s);
	    	}
	    }
	    
}
