package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.Config;

@RestController
public class Controller {
	
	@Autowired
	private Config config;
	/**
	 * 
	 * @Title: test    http://localhost:8080/wm-zk/test
	 * @Description: TODO(描述这个方法的作用)   
	 * @param: @return      
	 * @return: String 
	 * @author: LiWenMing     
	 * @throws
	 */
	@RequestMapping("test")
	public String test() {
		System.out.println("config="+config);
		return config.toString();
	}
}
