package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.cglib.proxy.Callback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.stereotype.Component;

@Configuration //相同，@Configuration 中所有带 @Bean 注解的方法都会被动态代理，因此调用该方法返回的都是同一个实例
//@Component //不同
public class TestConfig {
	
    private static final Logger LOG = LoggerFactory.getLogger(TestConfig.class);
    ConfigurationClassPostProcessor configurationClassPostProcessor;
//    ConfigurationClassEnhancer con;
//    https://blog.csdn.net/isea533/article/details/78072133
    Callback  c;
    ProxyFactory  p;
    @Bean
    public Config config() {
    	Config config = new Config();
    	LOG.info("@Bean注解下，初始化bean，config={}",config);
        return config;
    }
    
    @Bean
    public ConfigOut configOut() {
        Config c1 = config();
        Config c2 = config();
//        Config c1 = this.config();
//        Config c2 = this.config();
        LOG.info("@Bean注解下，多次调用生成bean的方法，[c1 == c2]={}",c1 == c2);
//        ConfigOut configOut = new ConfigOut(this.config());
        ConfigOut configOut = new ConfigOut(config());
        return configOut;
    }

    public static class Config {}
    
    public static class ConfigOut {
        
        private Config config;
        
        private ConfigOut(Config config) {
            this.config = config;
        }
        
    }
    
}
