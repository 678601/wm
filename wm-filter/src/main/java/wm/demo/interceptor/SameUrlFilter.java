package wm.demo.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import wm.demo.filter.RequestWrapper;
import wm.demo.utils.RedisUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * SQL XSS过滤
 *
 */
@Component
public class SameUrlFilter implements Filter {

	private static final Logger log = LoggerFactory.getLogger(SameUrlFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
    @Autowired
	RedisUtils redisUtils;
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		RequestWrapper wrapper = new RequestWrapper(httpServletRequest);
		chain.doFilter(wrapper, response);			
	}

	@Override
	public void destroy() {

	}
}
