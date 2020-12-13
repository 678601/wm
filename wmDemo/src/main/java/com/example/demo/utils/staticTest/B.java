package com.example.demo.utils.staticTest;

public class B extends A{//子类B
	public static String staticStr = "B改写后的静态属性";
	public String nonStaticStr = "B改写后的非静态属性";
	public static void staticMethod(){
		System.out.println("B改写后的静态方法");
	}
}