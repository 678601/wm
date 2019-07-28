package com.example.demo.temp;

import java.io.File;
import java.lang.reflect.Method;

import java.net.URL;

import java.net.URLClassLoader;

public class RunLoad {

	public static void main(String[] args) {

		RunLoad load = new RunLoad();

		load.load();
 		//扩展类加载器Main
        ClassLoader classLoader = RunLoad.class.getClassLoader();
        //表示当前线程的类加载器——应用程序类加载器
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        //—启动类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();

        System.out.println(classLoader);
        System.out.println(contextClassLoader);
        System.out.println(systemClassLoader);
	}

	private void load() {

		String jarPath = "D:\\nop.jar";

		File file = new File(jarPath);

		try {

			URL url = file.toURI().toURL();// 将File类型转为URL类型，file为jar包路径

			URLClassLoader urlClassLoader = new URLClassLoader(new URL[] { url });
			URLClassLoader urlClassLoader1 = new URLClassLoader(new URL[] { url });

//	Class<?> c = urlClassLoader.loadClass("Animal");//类名字

//	Method method = c.getDeclaredMethod("ouputAnimal",String.class).invoke(c.newInstance(),"fjs");//带参数

			Class c = urlClassLoader.loadClass("JarTest");
//			Class c = urlClassLoader.loadClass("com.example.demo.test.JarTest");
			Object obj = c.newInstance();

			System.out.println("this is c: " + c);

			Class[] parameterTypes = { String.class, Integer.class };

			Method method = c.getDeclaredMethod("syso", null);

			System.out.println(method.getName());

//			Object result = method.invoke(obj, "elephant", 32);
			Object result = method.invoke(obj);


			System.out.println(result);

			/**
			 * 
			 * 
			 * 
			 * 1、确定需求：包括：
			 * 
			 * 1）API JAR包规范，暂定！ 唯一类入口方法
			 * 
			 * 2）大小及内容，只允许有业务class，防止冲突
			 * 
			 * 3）用户上传规范，API JAR到固定目录下，
			 * 
			 * 按系统区分目录，同一个API JAR只允许有一个
			 * 
			 * 4）记录上传内容到数据库中，并缓存信息在redis中
			 * 
			 * 2、amp-center控制中心
			 * 
			 * 1）控制中心中，用户API执行的时间范围，需固定，类似告警
			 * 
			 * 2）读取redis中信息，获取符合执行时间范围的信息
			 * 
			 * 3）按系统、JAR名、执行类、执行方法、入参等规则，
			 * 
			 * 先找到JAR包及类和方法，实例化并执行方法
			 * 
			 * 4）执行后返回结果可入库或调用业务系统远程接口推送
			 * 
			 * 3、用户操作：
			 * 
			 * 1）上传功能，进行API JAR包上传并填写对应信息，如
			 * 
			 * 执行类名称、方法名称、入参（包含入参对应AMP的数据项值）
			 * 
			 * 数据返回接口等等等
			 * 
			 * 2）可立即点击”执行“，或等待定时任务执行
			 * 
			 */

			/*
			 * 
			 * JarFile jarFile = new JarFile(new File(jarPath));
			 * 
			 * URL url = new URL("file:" + jarPath);
			 * 
			 * ClassLoader loader = new URLClassLoader(new URL[] { url });//
			 * 自己定义的classLoader类，把外部路径也加到load路径里，使系统去该路经load对象
			 * 
			 * Enumeration<JarEntry> es = jarFile.entries();
			 * 
			 * Class myclass = loader.loadClass("Animal");
			 * 
			 * Animal animal = (Animal)myclass.newInstance();
			 * 
			 * animal.ouputAnimal();
			 * 
			 */

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
