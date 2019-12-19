package com.example.demo.vo;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*所有属性的get和set方法
toString 方法
hashCode方法
equals方法*/
//我的选择是包装类好！原因如下： 
//1。所有的sql使用的默认类型都是null，如果你把POJO中的映射属性类型写为基本类型，当查找不到记录的时候，返回null赋给基本类型就会出错 
//2。包装类型都可以相应的转化为基本类型，如果你设置为基本类型比如int的话，它默认初始化为0，但0本身就代表着一种含义，如果为null的话，既好理解，也可以方便开发人员转化！而且很多xml配置中默认都是null。
@Data // lombok 注解
@NoArgsConstructor
public class Employee {
	private String name;
	private Integer age;
	private Integer age1;
	private Double salary;


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

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Employee(String name, int age, double salary) {
		super();
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	public Integer getAge1() {
		return age1;
	}

	public void setAge1(Integer age1) {
		this.age1 = age1;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", age=" + age + ", age1=" + age1 + ", salary=" + salary + "]";
	}

}
