package com.example.demo.jdk;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.vo.Employee;
/**
 * jdk8 test
 * @author LiWenming
 * 2019年5月18日下午9:51:10
 */
public class JDK8Tset {
	
	private static final Logger log = LoggerFactory.getLogger(JDK8Tset.class);
	
	
	public static void main(String[] args) {
		log.info("main---begin");
		Employee []students = new Employee[] {new Employee("奔驰一", 15, 17d),new Employee("阿一", 15, 17d),new Employee("大众", 30, 50d)};
		List<Employee> list = Arrays.asList(students);
		//list 排序
		sortList(list);
		log.info("main---end");
	}

	/**
	 * @Description:  
	 * @author: LiWenming
	 * @date: 2019年5月18日下午9:56:15
	 */
	private static void sortList(List<Employee> list) {
		// TODO Auto-generated method stub
//		list.stream().
	}
	private static void listUtils(List<Employee> list) {
		// TODO Auto-generated method stub
//		list.stream().map(o->{o.getAge();}).collect(Collectors.toList());
	}
}
