package com.example.demo.dao.impl;

import org.springframework.stereotype.Repository;

import com.example.demo.dao.NormalDao;
/**
 * @Resource @Repository（这个注解属于J2EE的），默认安照名称进行装配，名称可以通过name属性进行指定 ，如果没有指定name属性，当注解写在字段上时，默认取字段名进行按照名称查找，如果注解写在setter方法上默认取属性名进行装配。 当找不到与名称匹配的bean时才按照类型进行装配。但是需要注意的是，如果name属性一旦指定，就只会按照名称进行装配。
 * @ClassName: @Autowired  默认按类型装配（这个注解是属业spring的），默认情况下必须要求依赖对象必须存在，如果要允许null 值，可以设置它的required属性为false，如：@Autowired(required=false) ，如果我们想使用名称装配可以结合@Qualifier注解进行使用
 * @Description: TODO(描述)
 * @author LWM
 * @date 2020-12-10 04:30:34
 */
@Repository
//为什么 @Repository 只能标注在 DAO 类上呢？这是因为该注解的作用不只是将类识别为Bean，同时它还能将所标注的类中抛出的数据访问异常封装为 Spring 的数据访问异常类型。 Spring本身提供了一个丰富的并且是与具体的数据访问技术无关的数据访问异常结构，用于封装不同的持久层框架抛出的异常，使得异常独立于底层的框架。
public class NormalDaoImpl implements NormalDao {

	@Override
	public String test() {
		// TODO Auto-generated method stub
		return "空字符串";
	}

	@Override
	public String testParameter(String parameter) {
		// TODO Auto-generated method stub
		return parameter;
	}

}
