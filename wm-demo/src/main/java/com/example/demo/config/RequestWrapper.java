package com.example.demo.config;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author 流的方式获取JSON数据
 */
public class RequestWrapper extends HttpServletRequestWrapper {
	
	private static final Logger log = LoggerFactory.getLogger(RequestWrapper.class);
	
    // 存放JSON数据主体
    private String body;
    
    private static String key = "and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+";
    private static Set<String> notAllowedKeyWords = new HashSet<String>(0);
    private static String replacedString="INVALID";
    private String currentUrl;
    static {
        String keyStr[] = key.split("\\|");
        for (String str : keyStr) {
            notAllowedKeyWords.add(str);
        }
    }

    public RequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        currentUrl = request.getRequestURI();
//        long start = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
//        long end = System.currentTimeMillis();
//        log.info("time 1 = {}",end-start);
        body = stringBuilder.toString();
    	if (StringUtils.isEmpty(body)) {
    		return ;
    	}
    	Map<String, String> result=new HashMap<>();
    	JSONObject jsonObject = JSON.parseObject(body);
    	for (String key : jsonObject.keySet()) {
//    		long start1 = System.currentTimeMillis();
    		String encodedKey=cleanXSS(key);
    		String encodedValue = cleanXSS(jsonObject.getString(key));
    		result.put(encodedKey,encodedValue);
//    		long end1 = System.currentTimeMillis();
//    		log.info("time 循环 = {}",end1-start1);
		}
//    	start = System.currentTimeMillis();
//    	log.info("time 2 = {}",start-end);
    	body=JSONObject.toJSONString(result);
//    	end = System.currentTimeMillis();
//    	log.info("time 3 = {}",end-start);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes("UTF-8"));
        ServletInputStream servletInputStream = new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }
        };
        return servletInputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public String getBody() {
        return this.body;
    }
    private String cleanXSS(String valueP) {
        // You'll need to remove the spaces from the html entities below
        String value = valueP.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
        value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
        value = value.replaceAll("'", "& #39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");
        value = cleanSqlKeyWords(value);
        return value;
    }
    private String cleanSqlKeyWords(String value) {
        String paramValue = value;
        for (String keyword : notAllowedKeyWords) {
            if (paramValue.length() > keyword.length() + 4
                    && (paramValue.contains(" "+keyword)||paramValue.contains(keyword+" ")||paramValue.contains(" "+keyword+" "))) {
                paramValue = StringUtils.replace(paramValue, keyword, replacedString);
                log.error("url=【{}】已被过滤，因为参数中包含不允许sql的关键词=【{}】，参数=【{}】，过滤后参数=【{}】" ,this.currentUrl, keyword,
                        value,paramValue);
            }
        }
        return paramValue;
    }
}
