package com.example.demo.temp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
/**
 * 
 * @Description:    ZK比较工具类 
 * @date:   2019年6月17日 下午4:54:20   
 * @version V1.0
 */
public class FileCompare {
	
	public static void main(String[] args) throws IOException {
		// 文件夹路径 
        String path1 = "D:\\test.txt";
//        String path1 = "D:\\zk.txt";
        List<String> scanListPath1 = readFile02(path1);
        Map<String,String> map1 = getMap(scanListPath1," = ");
//        String path2 = "D:\\test.txt";
        String path2 = "D:\\zk.txt";
        List<String> scanListPath2 = readFile02(path2);
        Map<String,String> map2 = getMap(scanListPath2," = ");      
        System.out.println("map1.size()="+map1.size()+",scanListPath1.size()="+scanListPath1.size());
        System.out.println("map2.size()="+map2.size()+",scanListPath2.size()="+scanListPath2.size());
        for (Entry<String, String> entry : map1.entrySet()) {
			String key1=entry.getKey();
			String value1=entry.getValue();
			if (map2.containsKey(key1)) {
				if (value1.equals(map2.get(key1))) {
//					System.out.println("key="+key1+"，值相等");
				}else {
					System.out.println("----------"+key1+"-----开始----");
					System.out.println(value1);
					System.out.println(map2.get(key1));
					System.out.println("----------"+key1+"-----结束----");
					
				}
			}else {
				System.out.println("没有key="+key1);
			}
		}
	}
	public static Map<String,String> getMap(List<String> list,String split){
		Map<String,String> map = new HashMap<String, String>();
        for (int i = 0; i < list.size(); i++) {
        	String str = list.get(i);
        	if (!StringUtils.isBlank(str)) {
//        		System.out.println("str="+str);
        		String []arr = str.split(split);
        		if (arr.length>1) {
        			map.put(arr[0], arr[1]);							
				}else {
					map.put(arr[0], "null");
				}
			}
		}
		return map;
	}
	public static List<String> readFile02(String path) throws IOException {
        // 使用一个字符串集合来存储文本中的路径 ，也可用String []数组
        List<String> list = new ArrayList<String>();
        FileInputStream fis = new FileInputStream(path);
        // 防止路径乱码   如果utf-8 乱码  改GBK     eclipse里创建的txt  用UTF-8，在电脑上自己创建的txt  用GBK
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        while ((line = br.readLine()) != null) {
            // 如果 t x t文件里的路径 不包含---字符串       这里是对里面的内容进行一个筛选
//            if (line.lastIndexOf("---") < 0) {
//            }
            list.add(line);
        }
        br.close();
        isr.close();
        fis.close();
        return list;
    }
}
