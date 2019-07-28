package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
/**
 * WebSocket test
 * @author LiWenming
 * 2019年5月18日上午7:18:09
 */
@Configuration
public class WebSocketConfig {
	 @Bean
	    public ServerEndpointExporter serverEndpointExporter() {
	        return new ServerEndpointExporter();
	    }
}
