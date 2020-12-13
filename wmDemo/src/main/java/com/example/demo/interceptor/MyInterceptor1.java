package com.example.demo.interceptor;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class MyInterceptor1 implements HandlerInterceptor {
	
	private final static Logger LOG = LoggerFactory.getLogger(MyInterceptor1.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOG.info("拦截器-1执行,url={}",request.getRequestURI());
		Enumeration<String> headers = request.getHeaderNames();
		while (headers.hasMoreElements()) {
			String key = headers.nextElement();
			String value = request.getHeader(key);
			LOG.info("拦截器-1,header,{}:{}",key,value);
		}
		 Map<String, String[]> map =request.getParameterMap();
		 for (Map.Entry<String, String[]> entry : map.entrySet()) {
			 LOG.info("拦截器-1,entry={}:{}",entry.getKey(),Arrays.asList(entry.getValue()));
		}
		return true;
	}
}
