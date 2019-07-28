package wm.demo.controller;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 
 * @Description:     
 * @date:   2019年7月24日 下午6:49:49   
 * @version V1.0
 */
@RestController
public class Controller {
	
	@Autowired
	RestTemplate restTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(Controller.class);
	
	@PostMapping("test")
	public String test(@RequestBody Map<String,String> map) {
		log.info("--test----header测试开始------");
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        log.info("-------------{}",request);
        //request header 遍历
        Enumeration<String> en = request.getHeaderNames();
        while (en.hasMoreElements()) {
			String name = (String) en.nextElement();
			log.info("-------------key={},value={}",name,request.getHeader(name));
		}
        log.info("----test--header测试结束------");
        log.info("AlarmQueryController-->alarmNumQuery开始：入参"+ map.toString());
		String result="";
//		String url="";
//		result = restTemplate.postForEntity(url, map, String.class).getBody();
		return result;		
	}
	
	@PostMapping("test2")
	public String test2(@RequestBody Map<String,String> map) {
		log.info("------test2--header测试开始------");
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        log.info("-------------{}",request);
        //request header 遍历
        Enumeration<String> en = request.getHeaderNames();
        while (en.hasMoreElements()) {
			String name = (String) en.nextElement();
			log.info("-------------key={},value={}",name,request.getHeader(name));
		}
        log.info("------test2--header测试结束------");
        log.info("AlarmQueryController-->alarmNumQuery开始：入参"+ map.toString());
		String result="";
		String url="http://localhost:8080/test";
		result = restTemplate.postForEntity(url, map, String.class).getBody();
		return result;		
	}
}
