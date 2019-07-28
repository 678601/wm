package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.AsyncService;

import io.swagger.annotations.Api;

@Api("Controller相关的api-01")
@RestController
public class Controller {

	public static final Logger log = LoggerFactory.getLogger(Controller.class);

	@Autowired
	private AsyncService asyncService;

	/*
	 * controller的执行线程是”nio-8080-exec-8”，这是tomcat的执行线程，而service层的日志显示线程名为“async-
	 * service-1”， 显然已经在我们配置的线程池中执行了，并且每次请求中，controller的起始和结束日志都是连续打印的，表明每次请求都快速响应了，
	 * 而耗时的操作都留给线程池中的线程去异步执行
	 */
	@PostMapping("async")
	public String executeAsync() {
		log.info("start executeAsync");
		asyncService.executeAsync();
		log.info("end executeAsync");
		return "success";
	}
}
