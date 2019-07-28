package wm.demo.config;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @Description:    restTemplate 请求过程中，自定义header 
 * @date:   2019年7月24日 下午6:39:13   
 * @version V1.0 LWM
 */
@Configuration
public class RestTemplateConfig {
	
    @Value("${maxTotalConnect}")
	private int maxTotalConnect; //连接池的最大连接数
	@Value("${maxConnectPerRoute}")
	private int maxConnectPerRoute; //单个主机的最大连接数（并发数）
	@Value("${connectTimeout}")
	private int connectTimeout; //连接超时默认
	@Value("${readTimeout}")
	private int readTimeout; //读取超时默认
	
	
	private final static Logger logger = LoggerFactory.getLogger(RestTemplateConfig.class);
	
	//创建HTTP客户端工厂
	private ClientHttpRequestFactory createFactory() {
	    if (this.maxTotalConnect <= 0) {
	      //SimpleClientHttpRequestFactory每次都会创建一个http连接
	      SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
	      factory.setConnectTimeout(this.connectTimeout);
	      factory.setReadTimeout(this.readTimeout);
	      return factory;
	    }
	    //重试次数，默认是3次，没有开启(setRetryHandler);保持长连接配置，需要在头添加Keep-Alive(setKeepAliveStrategy)
	   /* CloseableHttpClient httpClient = HttpClientBuilder.create().setRetryHandler(new DefaultHttpRequestRetryHandler(2, true)).setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy()).setMaxConnTotal(this.maxTotalConnect)
		        .setMaxConnPerRoute(this.maxConnectPerRoute).build();*/
	    
	    CloseableHttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(this.maxTotalConnect)
	        .setMaxConnPerRoute(this.maxConnectPerRoute).build();
	    //HttpComponentsClientHttpRequestFactory会走连接池
	    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(
	        httpClient);
	    factory.setConnectTimeout(this.connectTimeout);
	    factory.setReadTimeout(this.readTimeout);
	    return factory;
	  }
	
	
	@Bean
    public RestTemplate restTemplate(){
		logger.info("RestTemplateConfig--->restTemplate开始");
		RestTemplate restTemplate = null;
		try {
			restTemplate = new RestTemplate(this.createFactory());
		    List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();

		    //重新设置StringHttpMessageConverter字符集为UTF-8，解决中文乱码问题
		    HttpMessageConverter<?> converterTarget = null;
		    for (HttpMessageConverter<?> item : converterList) {
		      if (StringHttpMessageConverter.class == item.getClass()) {
		        converterTarget = item;
		        break;
		      }
		    }
		    if (null != converterTarget) {
		      converterList.remove(converterTarget);
		    }
		    converterList.add(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

		    //加入FastJson转换器
		    //converterList.add(new FastJsonHttpMessageConverter4());
		    
		    //解决微信返回text/plain的解析
		    //restTemplate.getMessageConverters().add(new WxMappingJackson2HttpMessageConverter());
		    //增加header信息
		   List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		   interceptors.add(new TraceIdIntercept());
		   restTemplate.setInterceptors(interceptors);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("RestTemplateConfig--->restTemplate异常:"+e.getMessage());
		}
		logger.info("RestTemplateConfig--->restTemplate结束");
		return restTemplate;
    }
}
