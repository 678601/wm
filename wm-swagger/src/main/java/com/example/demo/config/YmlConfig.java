package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 
 * @Description:  前缀配置的使用   
 * @author: LiWenming     
 * @date:   2020年3月3日 下午12:17:14   
 * @version V1.0
 */
@Component
@ConfigurationProperties(prefix = "wm")
public class YmlConfig {
	
	private String name;
	private String salaryDouble;
	private String fallName;
	private String salaryIncrease;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSalaryDouble() {
		return salaryDouble;
	}

	public void setSalaryDouble(String salaryDouble) {
		this.salaryDouble = salaryDouble;
	}

	public String getFallName() {
		return fallName;
	}

	public void setFallName(String fallName) {
		this.fallName = fallName;
	}

	public String getSalaryIncrease() {
		return salaryIncrease;
	}

	public void setSalaryIncrease(String salaryIncrease) {
		this.salaryIncrease = salaryIncrease;
	}

	@Override
	public String toString() {
		return "YmlConfig [name=" + name + ", salaryDouble=" + salaryDouble + ", fallName=" + fallName
				+ ", salaryIncrease=" + salaryIncrease + ", message=" + message + "]";
	}
	
}
