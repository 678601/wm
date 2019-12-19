package wm.demo.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
//http://localhost:8080/swagger-ui.html
//@Scope("prototype")  
@RestController
@RequestMapping("/wm")
@Api("swaggerDemoController相关的api")
public class Controller {

	@Value("${name}")
	private String name;
	@Value("${myArray}")
	private String []num;
	
	private static Logger log = LoggerFactory.getLogger(Controller.class);

	
	@PostMapping("/post")
	@ApiOperation(value = "测试value", notes = "测试notes")
//    @ApiImplicitParam(name = "id", value = "学生ID", paramType = "path", required = true, dataType = "Integer")
	public String test(@RequestBody @ApiParam(value = "参数描述信息str", required = true) Map<String,String> map) {
		log.info("input parm = {}", map.toString());
		
		return "";
	}

	@GetMapping("/get")
	@ApiOperation(value = "接口访问次数限制", notes = "后台提供接口访问次数的阈值，展示接口的调用情况")
	public String testGoogle() {
		log.info("get");
//		log.info("endTime={},threadId={},耗时 {}",LocalTime.now(),threadId,(end - start)/1000.0);
//		log.info("endTime={},thread={}",LocalTime.now(),threadId);
		return "";
	}
}
