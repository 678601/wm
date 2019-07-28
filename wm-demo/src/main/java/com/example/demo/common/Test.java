package com.example.demo.common;

public class Test {
	public static void main(String[] args) {
        for (EnumTest e : EnumTest.values()) {
        	System.out.println("枚举="+e+",key="+e.getKey()+",value="+e.getValue()+","+EnumTest.getEnumValueByKey("01"));
//            System.out.println(e.toString());
        }
         
        System.out.println("----------------我是分隔线------------------");
         
        EnumTest test = EnumTest.TUE;
        switch (test) {
        case MON:
            System.out.println("今天是星期一"+test.getKey());
            break;
        case TUE:
            System.out.println("今天是星期二");
            break;
        // ... ...
        default:
            System.out.println(test);
            break;
        }
    }
}
