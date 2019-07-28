package com.example.demo.job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.example.demo.annotation.ElasticJobConfig;

/**
 * 
 * @Description:    amp JOB迁移，只有一个节点时，每个时间点执行次数=shardingTotalCount
 * @author: LiWenming     
 * @date:   2019年5月16日 下午3:38:13   
 * @version V1.0
 */
//@ElasticJobConfig(cron = "2/2 * * * * ?", shardingTotalCount = 6, shardingItemParameters = "0=Beijing,1=Shanghai,2=Guangzhou")
@ElasticJobConfig(cron = "0/5 * * * * ?", shardingTotalCount = 2, shardingItemParameters = "0=Beijing")

@Component
public class MyJob implements SimpleJob {


	private final Logger logger = LoggerFactory.getLogger(MyJob.class);
	
//	@Autowired
//	private CmdbService cmdbService ;
	
    @Override
    public void execute(final ShardingContext shardingContext) {
        try {
        	logger.info("cmdb syn data start at ,0/5 * * * * ?,shardingContext={}",shardingContext);
//			cmdbService.syncSysServerInfoFromCmdb();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    }
}
