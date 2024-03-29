package com.example.demo.temp;


import java.lang.reflect.Method;

/**
 * 通过获取类类型，进而获取Method对象，进而调用类的方法，
 * 和直接通过类的对象来调用方法可以达到一样的效果，这里的示例调用了对象的三个方法
 * @author Wang
 *
 */
public class MethodDemo1 {
    public static void main(String[] args) {
        
        // 1.要获取一个方法就是获取类的信息，获取类的信息首先要获取类的类类型，要获取print(int ,int )方法
        A a1 = new A();
        Class c = a1.getClass();
        
        // 2.获取方法 名称和参数列表来决定 getMethod获取的是public的方法 getDelcaredMethod自己声明的方法
        try {
            System.out.println("==================调用print(int a, int b)方法");
            // Method m = c.getMethod("print", new Class[]{int.class,int.class});
            Method m = c.getMethod("print", int.class, int.class);
            // 方法的反射操作
            // a1.print(10, 20);方法的反射操作是用m对象来进行方法调用 和a1.print调用的效果完全相同
            // 方法如果没有返回值返回null,有返回值返回具体的返回值
            // Object o = m.invoke(a1,new Object[]{10,20});
            Object o = m.invoke(a1, 10, 20);
            
            
            System.out.println("==================调用print(String a, String b)方法");
            
            
            // 获取方法print(String,String)
            Method m1 = c.getMethod("print", String.class, String.class);
            // 用方法进行反射操作
            // a1.print("hello", "WORLD");
            o = m1.invoke(a1, "hello", "WORLD");
            
            
            System.out.println("===================调用无参的print方法");
            
            
            // Method m2 = c.getMethod("print", new Class[]{});
            Method m2 = c.getMethod("print");
            // m2.invoke(a1, new Object[]{});
            m2.invoke(a1);
            
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}

class A {
    public void print() {
        System.out.println("helloworld");
    }

    public void print(int a, int b) {
        System.out.println(a + b);
    }

    public void print(String a, String b) {
        System.out.println(a.toUpperCase() + "," + b.toLowerCase());
    }
}
