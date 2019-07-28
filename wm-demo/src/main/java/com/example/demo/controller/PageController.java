package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试 webSocket 
 * @author LiWenming
 * 2019年5月18日下午1:22:12
 */
@Controller //不可使用@RestController
public class PageController {
	/**
	 * @Description:  websocket
	 * @author: LiWenming
	 * @date: 2019年5月22日下午9:09:35
	 */
	@RequestMapping("/ws")
	public String websocket() {
		return "/webs.html";
	}
	@RequestMapping("/d")
	public String download() {
		return "/down.html";
	}
}
