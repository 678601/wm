package com.example.demo.temp;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
 
/**
 * 测试类
 * @author Administrator
 */
public class ClassLoaderTest {
 
	@SuppressWarnings("rawtypes")
	public static void main(String[] args){
		String path="D:\\nop.jar";
		String classNmae="JarTest";
		//输出ClassLoaderText的类加载器名称
		System.out.println("ClassLoaderText类的加载器的名称:"+ClassLoaderTest.class.getClassLoader().getClass().getName());
		System.out.println("System类的加载器的名称:"+System.class.getClassLoader());
		System.out.println("List类的加载器的名称:"+List.class.getClassLoader());
		
		System.out.println("默认的类加载器:"+ClassLoaderTest.class.getClassLoader().getSystemClassLoader());
		
		ClassLoader cl = ClassLoaderTest.class.getClassLoader();
		while(cl != null){
			System.out.print(cl.getClass().getName()+"->");
			cl = cl.getParent();
		}
		System.out.println(cl);
		
		try {
//			Class classDate = new MyClassLoader(path).loadClass(classNmae);
			Class classDate = ClassLoader.getSystemClassLoader().loadClass(classNmae);
			Object obj =  classDate.newInstance();
			//输出ClassLoaderAttachment类的加载器名称
			System.out.println("ClassLoader:"+obj.getClass().getClassLoader().getClass().getName());
			System.out.println(obj);
			Method method = classDate.getDeclaredMethod("syso", null);

			System.out.println(method.getName());

//			Object result = method.invoke(obj, "elephant", 32);
			Object result = method.invoke(obj);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	 
 
	
}

