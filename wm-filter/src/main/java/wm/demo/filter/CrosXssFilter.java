package wm.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * SQL XSS过滤
 *
 */
@Component
public class CrosXssFilter implements Filter {

	private static final Logger log = LoggerFactory.getLogger(CrosXssFilter.class);
	
	public static Set<String> notAllowedKeyWords = new HashSet<String>(0);
	
	@Value("${filter.sensitiveWord}")
	public void setKey(String []keys) {
		for (String str : keys) {
			notAllowedKeyWords.add(str);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		RequestWrapper wrapper = new RequestWrapper(httpServletRequest);
		String body = wrapper.getBody();
		JSONObject jsonObject = JSON.parseObject(body);
		boolean invalid=false;
		Map<String, String> result = new HashMap<>();
		for (String key : jsonObject.keySet()) {
			String value = jsonObject.getString(key);
			String encodedKey = cleanXSS(key);
			String encodedValue = cleanXSS(value);
			result.put(encodedKey, encodedValue);
			invalid = cleanSqlKeyWords(value);
			if (invalid) {
				log.error("url=【{}】已被过滤，因为参数中包含不允许sql的关键词，参数=【{}】，值=【{}】", httpServletRequest.getRequestURI(), key, value);
				break;
			}
		}
		if (invalid) {
            JSONObject re = new JSONObject();
            re.put("messageCode","500");
            re.put("info","非法参数");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write(re.toString());
            response.getWriter().close();				
		}else {
			body = JSONObject.toJSONString(result);
			wrapper.setBody(body);
			chain.doFilter(wrapper, response);						
		}
	}

	@Override
	public void destroy() {

	}

	private String cleanXSS(String valueP) {
		if (StringUtils.isEmpty(valueP)) {
			return valueP;
		}
		// You'll need to remove the spaces from the html entities below
		String value = valueP.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
		value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
		value = value.replaceAll("'", "& #39;");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		value = value.replaceAll("script", "");
		return value;
	}

	private boolean cleanSqlKeyWords(String value) {
		if (StringUtils.isEmpty(value)) {
			return false;
		}
		//value值转小写，因为预定义的过滤字段均为小写
		String paramValue = value.toLowerCase();
		for (String keyword : notAllowedKeyWords) {
			if (paramValue.length() > keyword.length() + 4 && (paramValue.contains(" " + keyword)
					|| paramValue.contains(keyword + " ") || paramValue.contains(" " + keyword + " "))) {
				return true;
			}
		}
		return false;
	}
}
