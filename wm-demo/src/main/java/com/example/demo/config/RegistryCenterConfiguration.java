package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

/**
 * 
 * @Description: 作业初始化数据类，namespace下，只有一个节点，则执行所有片
 * @author: LiWenming     
 * @date:   2019年5月24日 上午11:09:37   
 * @version V1.0
 */
//@Configuration
//@ConditionalOnExpression("'${elaticjob.zookeeper.server-lists}'.length() > 0")
public class RegistryCenterConfiguration {

    @Bean(initMethod = "init")
	public ZookeeperRegistryCenter regCenter(@Value("${elaticjob.zookeeper.server-lists:10.253.128.112:2181,10.253.128.113:2182,10.253.128.114:2183}") final String serverList, @Value("${elaticjob.zookeeper.namespace:amp-elasticJob-dev}") final String namespace,
            @Value("${elaticjob.zookeeper.baseSleepTimeMilliseconds:1000}") final Integer baseSleepTimeMilliseconds, @Value("${elaticjob.zookeeper.maxSleepTimeMilliseconds:3000}") final Integer maxSleepTimeMilliseconds,
            @Value("${elaticjob.zookeeper.maxRetries:6}") final Integer maxRetries
    ) {
        ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration(serverList, namespace);
        if (baseSleepTimeMilliseconds != null) zookeeperConfiguration.setBaseSleepTimeMilliseconds(baseSleepTimeMilliseconds);
        if (maxSleepTimeMilliseconds != null) zookeeperConfiguration.setMaxSleepTimeMilliseconds(maxSleepTimeMilliseconds);
        if (maxRetries != null) zookeeperConfiguration.setMaxRetries(maxRetries);
        return new ZookeeperRegistryCenter(zookeeperConfiguration);
    }
}