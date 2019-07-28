package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.demo.utils.ResultHandle;

/**
 * 切麵類，統一處理返回信息
 * @author LiWenming
 * 2019年5月14日下午9:54:40
 */
@Aspect
@Component
public class ReturnCodeAspect {
//	websocket 類需要排除，否則啓動報錯 http://www.pianshen.com/article/721751997/
//    @Pointcut("execution(public * com.*..controller.*.*(..))&&!execution(public * com.*..controller.*.getamppagestatistic(..))")
    @Pointcut("execution(public * com.*..controller.*.*(..))"
    		+ "&&!execution(public * com.*..controller.MyWebSocket.*(..))"
    		+ "&&!execution(public * com.*..controller.PageController.*(..))")
    public void returnCode() {
    }

    @Around("returnCode()")
    public Object around(ProceedingJoinPoint pjp) {
        Logger logger = LoggerFactory.getLogger(pjp.getTarget().getClass());
        try {
            Object o = pjp.proceed();
            for (ResultHandle handel : ResultHandle.values()) {
				if (handel==o) {
					return new ResultInfo(handel.getCode(), handel.getValue());
				}
			}
            return new ResultInfo("0000", o);
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
          //  return new ResultInfo("0021", "内部错误", ExceptionUtil.getMessage(e));
            //安全测试规定发生错误 异常信息不能往前端抛送，故此改动
            return new ResultInfo("0021", "内部错误");
        }
    }

    public class ResultInfo {

        private String messageCode = "0000";
        private Object info;

        public String getMessageCode() {
            return messageCode;
        }

        public void setMessageCode(String messageCode) {
            this.messageCode = messageCode;
        }

        public Object getInfo() {
            return info;
        }

        public void setInfo(Object info) {
            this.info = info;
        }

        public ResultInfo() {
        }

        public ResultInfo(Object info) {
            this.info = info;
        }

        public ResultInfo(String messageCode, Object info) {
            this.messageCode = messageCode;
            this.info = info;
        }
    }

}