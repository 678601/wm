package com.example.demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.example.demo.filter.MyFilter1;
import com.example.demo.filter.MyFilter2;
/**
 * 
 * @ClassName: WebFilterConfig
 * @Description: 不注册也可以,但是filter需要加@Component 
  * 注册之后，注意url，否则易踩坑
 * https://cloud.tencent.com/developer/article/1362809
  * 第一步，定义过滤器的类，该类必须继承 Filter 接口。
  * 第二步，注册过滤器，这步骤有 3 种方法。
  * 方法 1. 在配置类中实现一个 FilterRegistrationBean 对象。最灵活
  * 方法 2. 使用@Component 和@Order 注解注册,URL模式默认过滤器URL模式为"/*",无法修改
  * 方法 3. 使用@WebFilter 和@ServletComponentScan 注解，
	@WebFilter 注解是 Servlet3.0 中的注解，SpringBoot 能够支持该注解，通过@ServletComponentScan 注解，能够扫描并注册到 Servlet 容器中。
	@WebFilter 方式，可以支持 UrlPatterns 的设置，但是不支持 Order 的设置。
 * @author LWM
 * @date 2020-12-09 04:53:40
 */
@Configuration
public class WebFilterConfig {
	
	   @Bean
	   public FilterRegistrationBean<MyFilter1> abcFilter1() {
		   FilterRegistrationBean<MyFilter1> filterRegBean = new FilterRegistrationBean<>();
		   filterRegBean.setFilter(new MyFilter1());
		   filterRegBean.addUrlPatterns("/*");
		   filterRegBean.setOrder(Ordered.LOWEST_PRECEDENCE -1);
//		   filterRegBean.setOrder(Ordered.HIGHEST_PRECEDENCE +1);
		   return filterRegBean;
	   }
//	   @Bean
//	   public FilterRegistrationBean<MyFilter2> abcFilter2() {
//		   FilterRegistrationBean<MyFilter2> filterRegBean = new FilterRegistrationBean<>();
//		   filterRegBean.setFilter(new MyFilter2());
//		   filterRegBean.addUrlPatterns("/*");
//		   filterRegBean.setOrder(2);
//		   return filterRegBean;
//	   }
}
