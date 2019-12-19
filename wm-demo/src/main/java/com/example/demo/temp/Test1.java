package com.example.demo.temp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

//public 
public class Test1 {
	public static void main(String[] args) {
		Map<String,String> map = new HashMap<>();
		map.put("f","f");
		Map<String,Object> map2 = new HashMap<>();
		map2.put("dd","dd");
		map2.put("vv",map);
		System.out.println(map2.toString());
		JSONObject jsonObject1 = new JSONObject();
		jsonObject1.put("d", "d");
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("a", "a");
		jsonObject2.put("b", jsonObject1);
		System.out.println(jsonObject2);
//		JSONObject jsonObject = new JSONObject(true);
//		for (int i = 0; i < 100; i++) {
//			jsonObject.put(i+"", i);
//		}
//		String s = jsonObject.toJSONString();
//		System.out.println(s);
//		System.out.println(JSONObject.parse(s, Feature.OrderedField));
		List<JSONObject> list = new ArrayList<JSONObject>();
		for (int i = 0; i < 8; i++) {
			JSONObject jsonObject = new JSONObject(true);
			for (int j = 0; j < 20; j++) {
				jsonObject.put(j+"", j);				
			}
			list.add(jsonObject);
		}
		String str = JSONObject.toJSONString(list);
		System.out.println(str);
		JSONArray array = JSONObject.parseArray(str);
		System.out.println(array.toString());
//		JSONArray array1 = JSONObject.parseArray(str,true); 
//		System.out.println(array1.toString());
	}
}
