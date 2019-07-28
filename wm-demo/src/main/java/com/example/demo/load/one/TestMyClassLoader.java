package com.example.demo.load.one;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalTime;

import com.google.common.util.concurrent.RateLimiter;

public class TestMyClassLoader {
	
	private static RateLimiter limiter;
    public static void main(String []args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, InterruptedException{
        //自定义类加载器的加载路径
        MyClassLoader myClassLoader=new MyClassLoader("D:\\testJar");
//        Thread.currentThread().sleep(10000);
        //包名+类名
        Class c=myClassLoader.loadClass("load.Test");
        //测试运行中是否可以删除
//        Thread.currentThread().sleep(10000);
        if(c!=null){
            Object obj=c.newInstance();
            Method method=c.getMethod("say");
            double limit = 1.0d;
            limiter = RateLimiter.create(limit); // 每秒不超过10个任务被提交
            method.invoke(obj);
            System.out.println(c.getClassLoader().toString());
            System.out.println(ClassLoader.getSystemClassLoader());
            System.out.println(c.getClassLoader().getParent());
        }
    }
    public void syso() {
    	limiter.acquire();
    	System.out.println("*******TestMyClassLoader******3*22*");
    }
}
