package com.example.demo.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.WmDemoApplication;

@RunWith(SpringRunner.class)
@WebMvcTest(WmDemoApplication.class)
public class Controller1Test {

	@Autowired
    private MockMvc mockMvc;
	
	@Test
	public void testTest1() {
//		fail("Not yet implemented");
//        given(this.modelProductService.get(anyLong()))
//        .willReturn(null);
//String jsonStr = "{\"data\":{\"debit_account_balance_code\":40,\"credit_consume_count\":1,\"debit_start_age\":1,\"debit_consume_sum_code\":2,\"age\":38},\"modelProductId\":5}";
//RequestBuilder requestBuilder = null;
//requestBuilder = post("/scoreApi/score").contentType(MediaType.APPLICATION_JSON).content(jsonStr);
//this.mockMvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.content().string("{}"));
	}

}
