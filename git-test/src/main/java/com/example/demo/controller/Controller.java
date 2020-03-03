package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @ClassName:  Controller   
 * @Description:TODO(描述这个类的作用)   
 * @author: LiWenMing
 * @date:   2019年12月25日 上午10:45:10      
 * @Copyright:
 */
public class Controller {
	public static void main(String[] args) {
		List<JSONObject> list = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name", i);
			list.add(jsonObject);
		}
		System.out.println(list);
		Collections.reverse(list);
		System.out.println(list);
	}
}
