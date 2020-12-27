package com.example.demo.controller;

import com.example.demo.exception.WmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.NormalService;
import com.example.demo.utils.model.ResultVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @description 使用3层，为测试单元测试的不同层级处理
 * @author LiWenMing
 * @createTime 2020/12/27/0027 17:02
 */
@Api(tags="常规测试")
@RestController
@RequestMapping("normal")
public class NormalController {
	//日志记录器应当是一个类内部的东西，不允许其子类或者其他类使用因此被private修饰为私有的。
	//对于所有该类的对象也就是该类的所有实例只需要一个logger所以使用static修饰。
	//logger不能被替换或者修改、所以使用final再做修饰
	private static final Logger LOGGER = LoggerFactory.getLogger(NormalController.class);
	
	@Autowired
	NormalService service;

	/**
	 * 
	 * @Title: test1
	 * @Description: 全局异常与自定义异常验证
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
