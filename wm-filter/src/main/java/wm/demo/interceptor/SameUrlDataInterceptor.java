package wm.demo.interceptor;

import com.alibaba.fastjson.JSONObject;
import io.lettuce.core.RedisURI;
import wm.demo.filter.RequestWrapper;
import wm.demo.utils.RedisUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Title: 防止用户重复提交数据拦截器
 * @Description: 将用户访问的url和参数结合token存入redis，每次访问进行验证是否重复请求接口
 * @Auther: xhq
 * @Version: 1.0
 * @create 2019/3/26 10:35
 */
@Component
public class SameUrlDataInterceptor extends HandlerInterceptorAdapter {

    private static Logger log = LoggerFactory.getLogger(SameUrlDataInterceptor.class);
    @Autowired
	RedisUtils redisUtils;
    /**
     * 是否阻止提交,fasle阻止,true放行
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            SameUrlData annotation = method.getAnnotation(SameUrlData.class);
            if (annotation != null) {
                if(repeatDataValidator(request)){
                    //请求数据相同
                    log.warn("重复提交,url:"+ request.getServletPath());
                    JSONObject result = new JSONObject();
                    result.put("messageCode","500");
                    result.put("info","请勿重复请求");
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json; charset=utf-8");
                    PrintWriter printWriter = response.getWriter();
                    try {
                    	printWriter.write(result.toString());
                    	printWriter.close();						
					} catch (Exception e) {
						log.error("printWriter异常，{}",e);
					}finally{
						if (null!=printWriter) {
							try {
								printWriter.close();
							} catch (Exception e2) {
								log.error("printWriter异常，{}",e2);
							}							
						}
					}
                    return false;
                }else {//如果不是重复相同数据
                    return true;
                }
            }
            return true;
        } else {
            return super.preHandle(request, response, handler);
        }
    }
    /**
     * 验证同一个url数据是否相同提交,相同返回true
     * @param httpServletRequest
     * @return
     * @throws IOException 
     */
    public boolean repeatDataValidator(HttpServletRequest httpServletRequest) throws IOException{
        //获取请求参数map
    	RequestWrapper wrapper = new RequestWrapper(httpServletRequest);
    	String body = wrapper.getBody();
        HttpSession session = httpServletRequest.getSession();
        String address = httpServletRequest.getRemoteAddr();
        String host = httpServletRequest.getRemoteHost();
        //sessionId与url拼接key
        String key = session.getId()+address+httpServletRequest.getRequestURI();
        log.info("session.getId()={},address={},host={}",session.getId(),address,host);
        String redisValue = redisUtils.getValue(key);
        if(redisValue == null){
            //如果上一个数据为null,表示还没有访问页面
            //存放并且设置有效期，2秒
        	redisUtils.setValue(key, body);
            return false;
        }else{//否则，已经访问过页面
            if(redisValue.equals(body)){
                //如果上次url+数据和本次url+数据相同，则表示重复添加数据
                return true;
            }else{//如果上次 url+数据 和本次url加数据不同，则不是重复提交
//                smsRedisTemplate.opsForValue().set(redisKey, nowUrlParams, 1, TimeUnit.SECONDS);
                redisUtils.setValue(key, body);
                return false;
            }
        }
    }
}
