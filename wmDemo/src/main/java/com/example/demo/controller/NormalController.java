package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.NormalService;
import com.example.demo.utils.model.ResultVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="常规3层")
@RestController
@RequestMapping("n")
public class NormalController {
	
	@Autowired
	NormalService service;

	/**
	 * 
	 * @Title: test1
	 * @Description: 全局异常与自定义异常验证
	 * @param key
	 * @return
	 * @author LWM
	 * @date 2020-12-09 04:08:09
	 */
	@ApiOperation("无参数")
	@PostMapping("t1")
	public ResultVo test1() {
		ResultVo vo = new ResultVo();
		vo.setCode(0);
		vo.setMessage("ok");
		return vo;
	}
	
	/**
	 * 
	 * @Description: TODO(描述)
	 * @author LWM
	 * @date 2020-12-10 04:38:33
	 */
	@ApiOperation("带参数")
	@PostMapping("t2")
	public ResultVo test2(String parameter) {
		ResultVo vo = new ResultVo();
		vo.setCode(0);
		vo.setMessage(parameter);
		return vo;
	}
	
	@ApiOperation("带参数")
	@PostMapping("t3")
	public String test3(String parameter) {
		ResultVo vo = new ResultVo();
		vo.setCode(0);
		vo.setMessage(parameter);
		return "XXS";
	}
}
