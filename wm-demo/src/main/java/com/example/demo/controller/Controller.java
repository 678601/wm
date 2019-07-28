package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.AsyncService;
import com.example.demo.utils.ResultHandle;

import io.swagger.annotations.Api;

@Api("Controller相关的api-01")
@RestController
public class Controller {

	public static final Logger log = LoggerFactory.getLogger(Controller.class);

	@Autowired
	private AsyncService asyncService;
	
	@Autowired
	private RestTemplate restTemplate;

	/*
	 * controller的执行线程是”nio-8080-exec-8”，这是tomcat的执行线程，而service层的日志显示线程名为“async-
	 * service-1”， 显然已经在我们配置的线程池中执行了，并且每次请求中，controller的起始和结束日志都是连续打印的，表明每次请求都快速响应了，
	 * 而耗时的操作都留给线程池中的线程去异步执行
	 */
	@PostMapping("async")
	public Object executeAsync() {
		log.info("start executeAsync");
		asyncService.executeAsync();
		log.info("end executeAsync");
		log.info("asyncService={},restTemplate={}",asyncService,restTemplate);
		// 1.通过appid与secret获得access_token
    	String ip = "http://10.18.69.11";
    	String url_tocken="/v2/cgi-bin/token?grant_type=client_credential&appid=ea892173-d9dd-4c77-8742-3d7bd1d645cb&secret=7a7d4fd8-ea0d-4ff0-9ac1-da988aae62c7";
    	Map<String,String> map = new HashMap<String, String>();
    	map.put("appid", "ea892173-d9dd-4c77-8742-3d7bd1d645cb");
    	map.put("secret", "7a7d4fd8-ea0d-4ff0-9ac1-da988aae62c7");
    	JSONObject json =null;
    	try {
			json = restTemplate.getForEntity(ip+url_tocken, JSONObject.class).getBody();
		} catch (RestClientException e) {
			log.error("云助理接口调用异常，e={}",e);
			return json;
		}
		return ResultHandle.SYSCODEISNULL;
//		return "ResultHandle.SYSCODEISNULL";
	}
	/**
	 * 
	 * @Description: 调用云助理接口发送告警消息  
	 * @param list
	 * @author: LiWenming 
	 * @date:   2019年5月29日 上午10:14:45 
	 * @throws
	 */
	@PostMapping("sendAlarmDetaiMsgToCloudAssistant")
	public Object sendAlarmDetaiMsgToCloudAssistant() {
		log.info("asyncService={},restTemplate={}",asyncService,restTemplate);
		// 1.通过appid与secret获得access_token
    	String ip = "http://10.18.69.11";
//    	String url_tocken="/v2/cgi-bin/token?grant_type=client_credential&appid=ea892173-d9dd-4c77-8742-3d7bd1d645cb&secret=7a7d4fd8-ea0d-4ff0-9ac1-da988aae62c7";
    	String url_tocken="/v2/cgi-bin/token?grant_type=client_credential&appid={appid}&secret={secret}";
    	Map<String,String> map = new HashMap<String, String>();
    	map.put("secret", "7a7d4fd8-ea0d-4ff0-9ac1-da988aae62c7");
    	map.put("appid", "ea892173-d9dd-4c77-8742-3d7bd1d645cb");
    	JSONObject json =null;
    	try {
//			json = restTemplate.getForEntity(ip+url_tocken, JSONObject.class).getBody();
			json = restTemplate.getForEntity(ip+url_tocken, JSONObject.class, map).getBody();
		} catch (RestClientException e) {
			log.error("云助理接口调用异常，e={}",e);
			return json;
		}

    	if ("01".equals(json.getString("errcode"))) {
    		String tocken = json.getString("access_token");
    		log.info("tocken = {}",tocken);
    		String url_send_message="/v2/cgi-bin/message/custom/send?access_token="+tocken;
        	JSONObject object = new JSONObject();
        	object.put("touser", "touserName");
        	object.put("msgtype", "text");
        	JSONObject text = new JSONObject();
        	text.put("title", "titleTest");
        	text.put("content", "测试Test");
        	object.put("text", text);
        	JSONObject jsonObject = restTemplate.postForEntity(ip+url_send_message, object, JSONObject.class).getBody();
        	log.info("jsonObject = {}",jsonObject);
        	return jsonObject;
		}
		return json;   	
    }
}
