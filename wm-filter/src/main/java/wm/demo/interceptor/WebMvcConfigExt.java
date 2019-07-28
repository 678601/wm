package wm.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfigExt extends WebMvcConfigurationSupport {

    /**
     * 防止重复提交拦截器
     */
    @Autowired
    private SameUrlDataInterceptor sameUrlDataInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sameUrlDataInterceptor).addPathPatterns("/**");// 重复请求
    }
}