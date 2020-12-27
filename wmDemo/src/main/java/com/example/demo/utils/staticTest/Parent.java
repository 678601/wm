package com.example.demo.utils.staticTest;

/**
 * 
 * @ClassName: A
 * @Description: TODO(描述)
 * @author author
 * @date 2020-12-07 10:16:41
 */
public class Parent {
	public static String staticStr = "A静态属性";
	public String nonStaticStr = "A非静态属性";

	public static void staticMethod() {
		System.out.println("A静态方法");
	}

	public void nonStaticMethod() {
		System.out.println("A非静态方法");
	}
}