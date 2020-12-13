package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.NormalDao;
import com.example.demo.service.NormalService;

@Service
public class NormalServiceImpl implements NormalService {
	
	@Autowired
	NormalDao dao;

	@Override
	public String test() {
		// TODO Auto-generated method stub
		return dao.test();
	}

	@Override
	public String testParameter(String parameter) {
		// TODO Auto-generated method stub
		return dao.testParameter(parameter);
	}

}
