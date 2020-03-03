package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.NormalConfig;
import com.example.demo.config.YmlConfig;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @Description:   一个简单的模板
 * @author: LiWenming     
 * @date:   2020年3月3日 下午12:16:20   
 * @version V1.0
 */
@Api(tags="测试类controller")
@RestController
public class Controller {
	private Logger log =LoggerFactory.getLogger(Controller.class);
	@Autowired
	private YmlConfig ymlConfig;
	@Autowired
	private NormalConfig normalConfig;
	
	/**
	 * 
	 * @Title: test   
	 * @Description: 测试
	 * @param: @return      
	 * @return: String  
	 * @author: LiWenming
	 * @date:   2020年3月3日 下午12:16:42      
	 * @throws
	 */
	@ApiOperation("test方法")
	@GetMapping("test")
	public String test() {
		log.info("normalConfig.getName()={}",normalConfig.getName());
		log.info("ymlConfig={}",ymlConfig);
		return ymlConfig.toString();
		
	}
}
