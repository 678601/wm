package com.example.demo.temp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestWm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Map<String,String>> list = new ArrayList<>();
		Map<String,String> map0 = new HashMap<String, String>();
		map0.put("key", "ww");
		map0.put("value", "5");
		Map<String,String> map1 = new HashMap<String, String>();
		map1.put("key", "ww");
		map1.put("value", "5");
		Map<String,String> map2 = new HashMap<String, String>();
		map2.put("key", "ww");
		map2.put("value", "5");
		list.add(map0);
		list.add(map1);
		list.add(map2);
		list.forEach(m->m.put("key",m.get("key")+"56"));
		list.forEach(m->System.out.println(m));
		
	}

}
