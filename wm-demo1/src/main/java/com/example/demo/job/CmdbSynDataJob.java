package com.example.demo.job;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.example.demo.annotation.ElasticJobConfig;

/**
 * ElasticJob作业定时执行类，暂定为每日上午4点执行，每隔8小时执行一次
 * @author yaozhinan
 * @createDate 20180423
 * @verson 0.1
 */

//上线后的corn配置：2-2 0 4/8 1/1 * ?      说明：每日上午4点执行，每隔8小时执行一次 2-2 0 4/8 1/1 * ?              //test:2-2 11/5 * * * ? *，修改为每10分钟执行一次
//@ElasticJobConfig(cron = "2/2 * * * * ?", shardingTotalCount = 6, shardingItemParameters = "0=Beijing,1=Shanghai,2=Guangzhou")
@ElasticJobConfig(cron = "* * * * * ?", shardingTotalCount = 1, shardingItemParameters = "0=Beijing")

@Component
public class CmdbSynDataJob implements SimpleJob {


	private final Logger logger = LoggerFactory.getLogger(CmdbSynDataJob.class);
	
//	@Autowired
//	private CmdbService cmdbService ;
	
    @Override
    public void execute(final ShardingContext shardingContext) {
        try {
        	logger.info("cmdb syn data start at " + new Date());
//			cmdbService.syncSysServerInfoFromCmdb();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    }
}
