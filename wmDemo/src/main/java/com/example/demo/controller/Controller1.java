package com.example.demo.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.WmException;
import com.example.demo.utils.model.ResultVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="异常验证")
@RestController
@RequestMapping("c1")
public class Controller1 {

	/**
	 * 
	 * @Title: test1
	 * @Description: 全局异常与自定义异常验证
	 * @param key
	 * @return
	 * @author LWM
	 * @date 2020-12-09 04:08:09
	 */
	@ApiOperation("验证")
	@PostMapping("t1")
	public ResultVo test1(int key) {
		ResultVo vo = new ResultVo();
		int i=0;
		switch (key) {
		case 0:
		i =1/0;		
			break;
		case 1:
			if(key==1) {
				throw new WmException("xx");				
			}
			break;
		default:
			break;
		}
		vo.setCode(i);
		vo.setMessage("ok");
		return vo;
	}
}
