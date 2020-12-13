package com.example.demo.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.NormalService;

/**
  * 模拟对象（英语：mock object）
 * @ClassName: NormalControllerTest
 * @Description: TODO(描述)
 * @author LWM
 * @date 2020-12-10 05:14:19
 */
@RunWith(SpringRunner.class)  //SpringBoot1.4版本之前用的是SpringJUnit4ClassRunner.class
@SpringBootTest //SpringBoot1.4版本之前用的是@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration//测试环境使用，用来表示测试环境使用的ApplicationContext将是WebApplicationContext类型的
class NormalControllerTest {
	
	private final static Logger LOG = LoggerFactory.getLogger(NormalControllerTest.class);
	
    private MockMvc mockMvc;
    
	@Autowired
	private WebApplicationContext webApplicationContext;
	
//	@Before 注解不再适用，官网有提示， @Before 和@After 被 @BeforeEach 和@AfterEach给替代了
	@BeforeEach
	public void setup() {
		// 实例化方式一
//		mockMvc = MockMvcBuilders.standaloneSetup(new NormalController()).build();
		// 实例化方式二
		LOG.info("------@Before-------");
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
 
	}
	
	@Test
	void testTest1() {
		LOG.info("1");
	}
	/*
	 * 1、mockMvc.perform执行一个请求。
	 * 2、MockMvcRequestBuilders.get("XXX")构造一个请求。
	 * 3、ResultActions.param添加请求传值
	 * 4、ResultActions.accept(MediaType.TEXT_HTML_VALUE))设置返回类型
	 * 5、ResultActions.andExpect添加执行完成后的断言。
	 * 6、ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情
	  *   比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息。
	 * 7、ResultActions.andReturn表示执行完成后返回相应的结果。
	 */
	@Test
	void testTest3() throws Exception { 
		
    	String url="/n/t3";
    	MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
    	map.put("parameter", Arrays.asList("1"));
    	MvcResult mvcResult = doFrom(url,map);
    	sysLog(mvcResult);
//    	System.out.println("result="+result);
//    	Assert.assertNotNull(result.getModelAndView().getModel().get("user")); //自定义断言 
	}
    /**
     * 
     * @Description: form请求
     * @author LWM
     * @date 2020-12-11 10:36:20
     */
   public MvcResult doFrom(String url,MultiValueMap<String, String> params){
       try {
    	   mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    	   LOG.info("mockMvc="+mockMvc);
           MvcResult mvcResult =
        		   mockMvc.perform(MockMvcRequestBuilders.post(url)
//                           .header("Authorization", authorization)
                           .contentType(MediaType.ALL)
                           .params(params))
                           .andExpect(status().isOk())
                           .andDo(print()).andReturn();
           LOG.info("mvcResult={}",mvcResult);
           return mvcResult;
       } catch (Exception e) {
           LOG.error("Please check the parameter or call mode", e);
           return null;
       }
   }
   /**
    * 
    * @Description: 处理json请求
    * @author LWM
    * @date 2020-12-11 11:13:51
    */
   public MvcResult doJson(String url, String authorization, Object obj){
       try {
           MvcResult mvcResult =
        		   mockMvc.perform(MockMvcRequestBuilders.post(url)
                           .header("Authorization", authorization)
                           .contentType(MediaType.APPLICATION_JSON)
                           .content(JSONObject.toJSONString(obj)))
                           .andExpect(status().isOk())
                           .andDo(print()).andReturn();
           return mvcResult;
       } catch (Exception e) {
           LOG.info("Please check the parameter or call mode",e);
           return null;
       }
   }
   /**
    * @Description 统一输出
    * @param mvcResult 返回值
    */
   public void sysLog(MvcResult mvcResult){
       MockHttpServletResponse response = mvcResult.getResponse();
	   MatcherAssert.assertThat("Service execution successful", response.getStatus(), equalTo(HttpServletResponse.SC_OK));
	   try {
		LOG.info("Service execution return value <---> {}",response.getContentAsString());
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		LOG.info("sysLog",e);
	}
   }
}
