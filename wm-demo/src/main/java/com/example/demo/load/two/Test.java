package com.example.demo.load.two;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Test {

	public static void main(String[] args) throws ParseException {
		String a1 = String.format("%.3f", 2.34567d);
		String a2 = String.format("%.2f", 0.34567d);
		System.out.println("a1="+a1+",a2="+a2);
//		jsonTest();
//		setTest();
//		System.out.println(getFirstDayOfMonth());
//		List<String> list1 = getListMinute(1554267185000l,1554267285101l);
//		System.out.println(list1.toString());
//		System.out.println(StatisticDataTypeEnum.ESB);
//		mapJSON();
//		format();
//		testMap();
//		sortTest();
//		String s = null;
//		System.out.println(Integer.parseInt(String.valueOf(s)));
//		System.out.println(s.toString());
		List<String> list = new ArrayList<String>();
		list.add("0");
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		for (int i = list.size()-1; i >=0; i--) {
			System.out.println("xx="+list.get(i));
		}
		List<String> list1 = new ArrayList<String>();
		list1.add("0");
		list1.add("1");
		list1.add("2");
		list1.add("3");
		list1.add("4");
		list.addAll(list1);
		System.out.println(list.size());
		list.subList(0, 5);
		for (String string : list1) {
			System.out.println(string);
		}
		Date now = new Date();
		System.out.println(normalToInfluxDate(now));
		String str ="";
		StringBuffer code = new StringBuffer();
		System.out.println(str.split("\\|").length);
		System.out.println(code.toString().equals(""));

		System.out.println("-");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("a", "2.1");
		jsonObject.put("c", "3.1");
		jsonObject.put("d", "4.1");
		jsonObject.put("f", "2.1");
		jsonObject.put("b", "5.1");
		System.out.println(jsonObject);
		System.out.println(sortJson(jsonObject));
		double a = Double.parseDouble(null);
		System.out.println("a="+a);
		// TODO Auto-generated method stub
//		String s = "{\"messageCode\": \"0000\",\"info\": {\"5dc691481326\": [{\"serviceType\": \"SPRING_BOOT\"}],\"d68ce7cf34dc\": [{\"serviceType\": \"SPRING_BOOT\"}]}}";
//		List<Map<String, Object>> list = new ArrayList<>();
//		Map<String, Object> map1 = new HashMap<>();
//		Map<String, Object> map2 = new HashMap<>();
//		map1.put("name", "li");
//		map1.put("age", "1");
//		map2.put("name", "z");
//		map2.put("age", "2");
//		list.add(map2);
//		list.add(map1);
//		JSONArray array = JSONArray.parseArray(JSONObject.toJSONString(list));
//		System.out.println(array);
//        NumberFormat nf=NumberFormat.getInstance();
//        nf.setMinimumFractionDigits(3);
//        System.out.println("格式化后显示数字："+nf.format(10000000));
//        System.out.println("格式化后显示数字："+nf.format(10l/3l));
//        BigDecimal rate = new BigDecimal(0.44356d).setScale(4, BigDecimal.ROUND_HALF_UP);
//        System.out.println(influxToNormalDate("2019-03-14T00:00:00Z"));
////		for (int i = 0; i < ss.length; i++) {
////			System.out.println(ss[i]);
////		}
//		String jsonObject = "{\"w\":{\"A\":\"2\",\"B\":\"1\",\"C\":\"4\"}}";
//		JSONObject object = JSONObject.parseObject(jsonObject);
//		String s1 = String.valueOf(object.get("w"));
//		System.out.println(s1.split(","));
//		String []dd = s1.substring(1, s1.length()-1).split(",");
//		LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();
//		for (int i = 0; i < dd.length; i++) {
//			System.out.println(dd[i]);
//			String []ss = dd[i].split(":");
//			hashMap.put(ss[0], ss[1]);
//		}
//		System.out.println(s1.split(","));
	}
	public static String influxToNormalDate(String influxTime) {
		SimpleDateFormat isdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		isdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		try {
			String normalTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(isdf.parse(influxTime));
			return normalTime;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void jsonTest() {
		JSONObject object = new JSONObject();
		JSONObject object1 = new JSONObject();
		JSONObject object2 = new JSONObject();
		object1.put("a", 1);
		object1.put("b", 2);
		object2.put("c", 1);
		object2.put("d", 1);
		object.putAll(object1);
		object.putAll(object2);
		System.out.println(object.toJSONString());
	}
	public static void setTest() {
		Set<String> hashsSet = new HashSet<String>();
		hashsSet.add("a");
		hashsSet.add("b");
		hashsSet.add("c");
		hashsSet.add("d");
		System.out.println(hashsSet.toString());
	}
	/**
	 * 
	 * @Description: 获得当月第一天的毫秒值   
	 * @author: LiWenming 
	 * @date:   2019年4月3日 上午10:24:17 
	 * @throws
	 */
	public static long getFirstDayOfMonth() throws ParseException {
		Calendar calendar = Calendar.getInstance();  
		calendar.set(Calendar.DAY_OF_MONTH,1);    
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);
		return calendar.getTime().getTime();
	}

	public static List<String> getListMinute(long startMs, long endMs) throws ParseException {
		List<String> betweenList = new ArrayList<String>();
		if (startMs > endMs) {
			return betweenList;
		}
		SimpleDateFormat min_sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		// 毫秒值转为年月日时分
		String end = min_sdf.format(new Date(endMs));

		try {
			Calendar startMin = Calendar.getInstance();
			startMin.setTime(new Date(startMs));
			startMin.add(Calendar.MINUTE, -1);

			while (true) {
				startMin.add(Calendar.MINUTE, 1);
				Date newDate = startMin.getTime();
				String newend = min_sdf.format(newDate);
				betweenList.add(newend);
				if (end.equals(newend)) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return betweenList;
	}	
	/**
	 * 
	 * @Description: json键值对排序    
	 * @author: LiWenming 
	 * @date:   2019年4月7日 下午8:07:12 
	 * @throws
	 */
	private static JSONObject sortJson(JSONObject json) {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject(true);
		JSONArray array = new JSONArray();
		for (String key : json.keySet()) {
			JSONObject object = new JSONObject();
			object.put(key, json.get(key));	
			array.add(object);
			}
		List<JSONObject> list = JSONArray.parseArray(array.toJSONString(), JSONObject.class);
		Collections.sort(list, new Comparator<JSONObject>() {
			@Override
			public int compare(JSONObject o1, JSONObject o2) {
				// TODO Auto-generated method stub
				String key1="";
				for (String key : o1.keySet()) {
					key1=key;
				}
				String key2="";
				for (String key : o2.keySet()) {
					key2=key;
				}
				double v1 = o1.getDoubleValue(key1);
				double v2 = o2.getDoubleValue(key2);
				if (v1>v2) {
					return 1;
				} else if(v1==v2){
					return 0;
				}else {
					return -1;
				}
			}
		});
		for (JSONObject jsonObject2 : list) {
			String key0="";
			for (String key : jsonObject2.keySet()) {
				key0=key;
			}
			jsonObject.put(key0, jsonObject2.get(key0));
		}
		return jsonObject;
	}
	
	private static String alarmContent(String str) {
		String result="";
//			if (!StringUtils.isEmpty(str)) {
				JSONObject jsonObject = JSONObject.parseObject(str);
				result = String.valueOf(jsonObject.get("alarmContent"));
//			}			
System.out.println("result="+result);
		return result;		
	}
	public static String normalToInfluxDate(Date normalTime) throws ParseException {
		SimpleDateFormat isdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		isdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		String influxTime = isdf.format(normalTime);
		return  influxTime;
	}
	public static void sortTest() {
		List <Double> list = new ArrayList<Double>();
		list.add(3d);
		list.add(5d);
		list.add(2d);
		list.add(6d);
		list.sort((o1,o2)->o2.compareTo(o1));
//		Collections.sort(list, new Comparator<Double>() {
//			@Override
//			public int compare(Double o1, Double o2) {
//				// TODO Auto-generated method stub
//				return o2.compareTo(o1);
//			}
//		});
		List s = list.subList(0, 2);
		System.out.println(""+JSONArray.parseArray(JSON.toJSONString(s)));
		System.out.println(""+JSONArray.parseArray(JSON.toJSONString(list)));
	}

	public static void format() {
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(2);
		System.out.println(nf.format(0.6));
	}
	public static void lsitTest() {
		List <Double> list = new ArrayList<Double>();
		list.add(3d);
		list.add(5d);
		list.add(2d);
		list.add(6d);
		list.sort((o1,o2)->o2.compareTo(o1));
//		Collections.sort(list, new Comparator<Double>() {
//			@Override
//			public int compare(Double o1, Double o2) {
//				// TODO Auto-generated method stub
//				return o2.compareTo(o1);
//			}
//		});
		System.out.println(""+JSONArray.parseArray(JSON.toJSONString(list)));
	}
	public static void testMap() {
		 HashMap<String, String> CHANNEL_MAP = new HashMap<String, String>();
		// 01:浏览器告警 02:服务告警 03:服务器告警 04:数据库告警 05:批作业告警 06:日志告警 08:消息队列告警
			CHANNEL_MAP.put("0x", "AMP告警");
			CHANNEL_MAP.put("09", "响尾蛇告警");
			CHANNEL_MAP.put("10", "AAP平台告警");
			CHANNEL_MAP.put("11", "星云告警");
			CHANNEL_MAP.put("12", "海东青告警");
			CHANNEL_MAP.put("14", "数云告警");
			CHANNEL_MAP.put("15", "PAAS告警");
			System.out.println(CHANNEL_MAP.values());
			System.out.println(CHANNEL_MAP.values().toArray());
	}
	public static void mapJSON() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("a", "2.1");
		map.put("c", "3.1");
		map.put("d", "4.1");
		map.put("f", "2.1");
		map.put("b", "5.1");
		System.out.println(map.toString());
		System.out.println(JSONObject.parseObject(JSON.toJSONString(map)));
	}
	public enum StatisticDataTypeEnum {
		//esb类型
		ESB,
		//pinpoint类型
		PP
	}
}
