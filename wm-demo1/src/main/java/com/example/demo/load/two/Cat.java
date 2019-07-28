package com.example.demo.load.two;

import org.springframework.scheduling.annotation.Async;

public class Cat {
	@Async
	public void print(){
		for (int i = 0; i < 10; i++) {
			System.out.println("----------,"+i);			
		}
	}
}
