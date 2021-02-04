package com.example.demo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("controller")
public class Controller {

	@PostMapping("test")
	public void test() {
		int i=0;
		while (i!=100000000){
			i++;
			System.out.println("----test----"+i);
		}
	}
	
	@PostMapping("test1")
	public String test1(HttpServletRequest request) {
		System.out.println("-----------");
//		Enumeration<String> en = request.getHeaderNames();
//		while(en.hasMoreElements()) {
//			String el = en.nextElement();
//			System.out.println(el+"="+request.getHeader(el));
//		}
		try {
			readAsChars2(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			readAsChars2(request);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//自己保存map，可以重复获取
		String name = request.getParameter("name");
		String name1 = request.getParameter("name");
		System.out.println(name);

		return name+"---"+name1;
	}
	
	   public  void readAsChars2(HttpServletRequest request)
	    {
	        InputStream is = null;
	        try
	        {
	            is = request.getInputStream();
	            StringBuilder sb = new StringBuilder();
	            byte[] b = new byte[4096];
	            for (int n; (n = is.read(b)) != -1;)
	            {
	                sb.append(new String(b, 0, n));
	            }
	            System.out.println("sb.toString()"+sb.toString());
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }
	        finally
	        {
	            if (null != is)
	            {
	                try
	                {
	                    is.close();
	                }
	                catch (IOException e)
	                {
	                    e.printStackTrace();
	                }
	            }
	        }
	 
	    }
}
