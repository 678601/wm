package com.example.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.utils.model.ResultVo;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
    /**
     * 
     * @Title: handleException
     * @Description: 处理未知异常
     * @param e
     * @return
     * @author LWM
     * @date 2020-12-09 03:51:00
     */
    @ExceptionHandler(Exception.class)
    ResultVo handleException(Exception e){
    	LOG.error(e.getMessage(), e);

    	ResultVo vo = new ResultVo();
    	vo.setCode(0);
        vo.setMessage("未知异常，操作失败！");
        return vo;
    }

    /**
     * 处理所有业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(WmException.class)
    ResultVo handleBusinessException(WmException e){
    	LOG.error(e.getMessage(), e);
    	ResultVo vo = new ResultVo();
    	vo.setCode(0);
        vo.setMessage("自定义异常，操作失败！");
        return vo;
    }
}
