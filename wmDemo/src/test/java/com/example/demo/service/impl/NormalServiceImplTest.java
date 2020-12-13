package com.example.demo.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.dao.NormalDao;

@RunWith(SpringRunner.class)
@SpringBootTest
class NormalServiceImplTest {
	
	@Autowired
	NormalDao dao;

	@Test
	void testTest() {
//		fail("Not yet implemented");
		String  result = dao.test();
		assertThat(result).isEqualTo("空字符串");
	}

	@Test
	void testTestParameter() {
//		fail("Not yet implemented");
		String parameter = "XX";
		String  result = dao.testParameter(parameter);
		assertThat(result).isEqualTo(parameter);
	}

}
