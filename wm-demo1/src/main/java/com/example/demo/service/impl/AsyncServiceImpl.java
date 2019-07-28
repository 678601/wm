package com.example.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.service.AsyncService;

@Service
public class AsyncServiceImpl implements AsyncService {
	
	public static final Logger log = LoggerFactory.getLogger(AsyncServiceImpl.class);
	
	@Override
	@Async("asyncServiceExecutor")//asyncServiceExecutor是前面ExecutorConfig.java中的方法名，表明executeAsync方法进入的线程池是asyncServiceExecutor方法创建的
	public void executeAsync() {
		// TODO Auto-generated method stub
		log.info("start executeAsync");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("end executeAsync");
	}

}
