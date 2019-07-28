package wm.demo.config;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class TraceIdIntercept implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {

        HttpServletRequest httpServletRequest = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();

		request.getHeaders().add("x-request-id", httpServletRequest.getHeader("x-request-id"));
		request.getHeaders().add("x-b3-traceid", httpServletRequest.getHeader("x-b3-traceid"));
		request.getHeaders().add("x-b3-spanid", httpServletRequest.getHeader("x-b3-spanid"));
		request.getHeaders().add("x-b3-parentspanid", httpServletRequest.getHeader("x-b3-parentspanid"));
		request.getHeaders().add("x-b3-sampled", httpServletRequest.getHeader("x-b3-sampled"));
		request.getHeaders().add("x-b3-flags", httpServletRequest.getHeader("x-b3-flags"));
		request.getHeaders().add("x-ot-span-context", httpServletRequest.getHeader("x-ot-span-context"));
		return execution.execute(request, body);
	}
}
