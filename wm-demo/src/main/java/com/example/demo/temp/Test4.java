package com.example.demo.temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.demo.vo.Employee;

public class Test4 {
	public static void main(String[] args) {
		List<Employee> list = new ArrayList<Employee>();
		for (int i = 0; i < 6; i++) {
			Employee employee = new Employee(i+"", i, i);
			list.add(employee);
			list.add(employee);
		}
		list.sort(Comparator.comparing(Employee::getName).reversed());
		//相同名称的一块
		Map<String, List<Employee>> map = list.stream().collect(Collectors.groupingBy(Employee::getName));
		//分组
		Map<String, Long> map1 = list.stream().collect(Collectors.groupingBy(Employee::getName,Collectors.counting()));
		System.out.println(list);
		System.out.println(map);
		System.out.println(map1);

	}
}
