package com.example.demo.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.example.demo.common.VisiableThreadPoolTaskExecutor;

/**
 * 创建一个配置类ExecutorConfig，用来定义如何创建一个ThreadPoolTaskExecutor，要使用@Configuration和@EnableAsync这两个注解，表示这是个配置类，并且是线程池的配置类
 * 
 * @author LiWenming 2019年5月6日下午9:31:31
 */
@Component
@EnableAsync
public class ExecutorConfig {

	private static final Logger log = LoggerFactory.getLogger(ExecutorConfig.class);

	@Bean
	public Executor asyncServiceExecutor() {
		log.info("start asyncServiceExecutor");
		//1.直接使用池
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		//2.打印辅助信息 使用VisiableThreadPoolTaskExecutor
		ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
		// 配置核心线程数
		executor.setCorePoolSize(5);
		// 配置最大线程数
		executor.setMaxPoolSize(5);
		// 配置队列大小
		executor.setQueueCapacity(99999);
		// 配置线程池中的线程的名称前缀
		executor.setThreadNamePrefix("async-service-");

		// rejection-policy：当pool已经达到max size的时候，如何处理新任务
		// CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		// 执行初始化
		executor.initialize();
		return executor;
	}
}
