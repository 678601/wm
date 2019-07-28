package wm.demo.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 
 * @Description: redis工具类，保存数据字典，分两层，如DIC_KEY为channel，则保存一个key为dic.channel的k，v为DIC_KEY为channel的所有数据字典的id，第二层为每个数据字典的kv
 * @author: LiWenming
 * @date: 2019年5月15日 上午9:14:33
 * @version V1.0
 */
@Component
public class RedisUtils {
	
	@Autowired
	public  RedisTemplate<String, Object> redisTemplate;

	// 键过期时间1，单位 秒
	private  final long EXPIRE_TIME = 600;
	// 键过期时间2，单位 秒,防止重复提交
	private  final long EXPIRE_TIME_SAME_URL = 3;

	/**
	 * 
	 * @Description: 获得Map @author: LiWenming @date: 2019年4月28日 下午6:27:17 @throws
	 */
	public  Map<String, String> getRedisValue(String tableName, String dicType) {
		Object o = redisTemplate.opsForValue().get(tableName + "." + dicType);
		Map<String, String> map = null;
		if (o == null || StringUtils.isEmpty(o.toString())) {
			return null;
		} else {
			String[] dickKeys = o.toString().split(",");
			map = new HashMap<String, String>();
			for (int i = 0; i < dickKeys.length; i++) {
				Object o1 = redisTemplate.opsForValue().get(tableName + "." + dicType + "." + dickKeys[i]);
				map.put(dickKeys[i], o1.toString());
			}
			return map;
		}
	}

	/**
	 * 
	 * @Description: redis set value @param tableName 表名 @param dicType
	 *               数据字典类型 @param list 需要保存的值 @return @author: LiWenming @date:
	 *               2019年5月14日 下午4:27:48 @throws
	 */
	public  void setRedisValue(String tableName, String dicType, List<Map<String, String>> list) {
		// 数据字典的DIC_KEY 存一份数据，各键值再存储
		StringBuilder dickKeys = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			if (i != 0) {
				dickKeys.append(",");
			}
			dickKeys.append(list.get(i).get("DIC_KEY"));
			String thisKey = list.get(i).get("DIC_KEY");
			String dicValue = list.get(i).get("DIC_VALUE");
			// 不存在的数据，默认无匹配
			dicValue = dicValue == null ? "无匹配" : dicValue;
			redisTemplate.opsForValue().set(tableName + "." + dicType + "." + thisKey, dicValue, EXPIRE_TIME,
					TimeUnit.SECONDS);
		}
		redisTemplate.opsForValue().set(tableName + "." + dicType, dickKeys.toString(), EXPIRE_TIME, TimeUnit.SECONDS);
	}
	/**
	 * 
	 * @Description: 获得值，处理url重复提交  
	 * @param key
	 * @return
	 * @date:   2019年7月2日 上午9:04:29 
	 * @author  LMW
	 * @throws
	 */
	public String getValue(String key) {
		return (String) redisTemplate.opsForValue().get(key);
	}
	/**
	 * 
	 * @Description: 设置值，2秒的过期时间，处理url重复提交   
	 * @param key
	 * @param value
	 * @date:   2019年7月2日 上午9:05:07 
	 * @author  LMW
	 * @throws
	 */
	public void setValue(String key,String value) {
	 redisTemplate.opsForValue().set(key, value, EXPIRE_TIME_SAME_URL, TimeUnit.SECONDS);
	}
}
