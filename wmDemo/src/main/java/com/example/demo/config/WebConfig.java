package com.example.demo.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.interceptor.MyInterceptor1;
import com.example.demo.interceptor.MyInterceptor2;
/**
 * 
 * @ClassName: WebConfig
 * @Description: 拦截器测试
 * @author LWM
 * @date 2020-12-09 04:51:31
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Resource
	private MyInterceptor1 myInterceptor1;
	@Resource
	private MyInterceptor2 myInterceptor2;
	
	/**
	 * 拦截器需要注册
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(myInterceptor1)
		.addPathPatterns("/*")
		.excludePathPatterns("");
		registry.addInterceptor(myInterceptor2)
		.addPathPatterns("/*")
		.excludePathPatterns("");
	}
}
