package com.example.demo.temp;

import java.time.LocalTime;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.util.concurrent.RateLimiter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
//http://localhost:8080/swagger-ui.html
//@Scope("prototype")  
@RestController
@RequestMapping("/wm")
@Api("swaggerDemoController相关的api")
public class WmController {

	@Value("${name}")
	private String name;
	@Value("${myArray}")
	private String []num;
	private static Logger log = LoggerFactory.getLogger(WmController.class);
	private RateLimiter limiter1 ;
//	static {
//		limiter1 = RateLimiter.create(1.0); // 每秒不超过1个任务被提交
//		log.info("init..........static............");
//	}
	@PostConstruct
	public void init() {
		limiter1 = RateLimiter.create(1.0); // 每秒不超过1个任务被提交
		log.info("init.........@PostConstruct.............");
	}
	
	@PostMapping("/test")
	@ApiOperation(value = "测试value", notes = "测试notes")
//    @ApiImplicitParam(name = "id", value = "学生ID", paramType = "path", required = true, dataType = "Integer")
	public String test(@RequestParam @ApiParam(value = "参数描述信息str", required = true) String str,
			@RequestParam(required = false) @ApiParam(name = "id", value = "参数描述信息id") Integer id) {
		log.info("input parm {},{},{},{}", str, id, name,num);
		Arrays.asList(num).stream().forEach(o->log.info(o));
		return str+","+name;
	}

	@PostMapping("/testGoogle")
	@ApiOperation(value = "接口访问次数限制", notes = "后台提供接口访问次数的阈值，展示接口的调用情况")
	public String testGoogle(@RequestParam @ApiParam(value = "参数描述信息str", required = true) String str,
			@RequestParam(required = false) @ApiParam(name = "id", value = "参数描述信息id") Integer id) {
		//当前时间，毫秒数
		Long start = System.currentTimeMillis();
		//当前线程ID
		long threadId=Thread.currentThread().getId();
		//当前线程名称
		String threadName=Thread.currentThread().getName();
		log.info("beginTime={},threadId={},threadName={}",LocalTime.now(),threadId,threadName);
//		RateLimiter limiter = RateLimiter.create(1.0); // 每秒不超过10个任务被提交
		for (int i = 0; i < 10; i++) {
			limiter1.acquire(); // 请求RateLimiter, 超过permits会被阻塞
			System.out.println("call execute.." + i+",threadId="+threadId+",LocalTime.now="+LocalTime.now());

		}
		Long end = System.currentTimeMillis();
		log.info("endTime={},threadId={},耗时 {}",LocalTime.now(),threadId,(end - start)/1000.0);
//		log.info("endTime={},thread={}",LocalTime.now(),threadId);
		return str;
	}
}
