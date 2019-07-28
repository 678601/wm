package com.example.demo.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * zk比较帮助类，便于分析键值对
 * @author LiWenming
 * 2019年6月17日下午9:21:53
 */
public class FIleUtils {

private static final Logger log = LoggerFactory.getLogger(FIleUtils.class);

public static void main(String[] args) throws IOException {
        // 文件夹路径 
        String path1 = "C:\\testFile\\fileTest.txt";
//        String path2 = "C:\\testFile\\fileTest.txt";
        Map<String,String> map1 = getPathMap(path1);
//        Map<String,String> map2 = getPathMap(path1);
//        for (Entry<String,String> myMap : map1.entrySet()) {
//			String key = myMap.getKey();
//			String value = myMap.getValue();
//			log.info("key={},value={}",key,value);
//		}
        writerTxt(map1, "C:\\testFile12\\","1112.txt");
    }

	/**
	 * @Description: 读取一个文本 一行一行读取
	 * @author: LiWenming
	 * @date: 2019年6月17日下午9:11:48
	 */
	public static List<String> readLine(String path) throws IOException {
		// 使用一个字符串集合来存储文本中的路径 ，也可用String []数组
		List<String> list = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(path);
		// 防止路径乱码 如果utf-8 乱码 改GBK eclipse里创建的txt 用UTF-8，在电脑上自己创建的txt 用GBK
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String line = "";
		while ((line = br.readLine()) != null) {
			// 如果 t x t文件里的路径 不包含---字符串 这里是对里面的内容进行一个筛选
//            if (line.lastIndexOf("---") < 0) {
//            }
			list.add(line);
		}
		br.close();
		isr.close();
		fis.close();
		return list;
	}

	/**
	 * @Description: list 返回切分后的map
	 * @author: LiWenming
	 * @date: 2019年6月17日下午9:11:17
	 */
	public static Map<String, String> getMap(List<String> list) {
		Map<String, String> map = new HashMap<>();
		for (int i = 0; i < list.size(); i++) {
			String line = list.get(i);
			// 切割，或通过标记分割
			int index = line.indexOf(":");
			if (-1==index) {
				continue;
			}
			String index0 = line.substring(0, index);
			String index1 = line.substring(index + 1, line.length());
			map.put(index0, index1);
		}
		return map;
	}
	/**
	 * @Description:  通过文件路径生成map
	 * @author: LiWenming
	 * @date: 2019年6月17日下午9:25:03
	 */
	public static Map<String, String> getPathMap(String path) throws IOException {
        List<String> lines = readLine(path);
        Map<String,String> map = getMap(lines);
        for (Entry<String,String> myMap : map.entrySet()) {
			String key = myMap.getKey();
			String value = myMap.getValue();
			key=key.replace("\"", "");
			value=value.replace("\\\"", "\"");
			log.info("key={},value={}",key,value);
		}
		return map;
	}
	/**
	 * @Description:  写文件
	 * @author: LiWenming
	 * @date: 2019年6月17日下午9:52:51
	 */
	public static void writerTxt(Map<String, String> map,String path,String fileName) throws IOException {
		File f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}
		File file = new File(path+fileName);
		FileOutputStream fos = new FileOutputStream(file);
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");//一行一行读取文件，解决读取中文字符时出现乱码
		BufferedWriter bw = new BufferedWriter(osw);
        for (Entry<String,String> myMap : map.entrySet()) {
			String key = myMap.getKey();
			String value = myMap.getValue();
			key=key.replace("\"", "");
			value=value.replace("\\\"", "\"");
			log.info("key={},value={}",key,value);
			bw.write(key + " " + value + "\r\n");
		}
		// 注意关闭的先后顺序，先打开的后关闭，后打开的先关闭
		bw.close();
		osw.close();
		fos.close();
		}
}
