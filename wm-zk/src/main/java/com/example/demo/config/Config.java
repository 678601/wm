package com.example.demo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;



//@ConfigurationProperties(prefix = "wm")
@Component
@RequestMapping
public class Config {
	@Value("${wm.name}")
	private String name;
	@Value("${wm.age}")
	private Integer age;
	@Value("${wm.array}")
	private String [] array;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String[] getArray() {
		return array;
	}
	public void setArray(String[] array) {
		this.array = array;
	}
	@Override
	public String toString() {
		return "Config [name=" + name + ", age=" + age + ", array=" + Arrays.toString(array) + "]";
	}
	
}
