package com.example.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//@Slf4j 需要插件？log？
public class DateUtils {
	private final static Logger log = LoggerFactory.getLogger(DateUtils.class);
	
	public static final String DAY_FORMATTER = "yyyy-MM-dd";
	public static final String MIN_FORMATTER = "yyyy-MM-dd HH:mm";
	public static final String HOUR_FORMATTER = "yyyy-MM-dd HH";
	public static final String FORMATTER = "yyyy-MM-dd HH:mm:ss";
	public static final String INFLUX_FORMATTER = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	public static final long ONEDAY = 24*60*60*1000;
	/** 锁对象 */
	private static final Object lockObj = new Object();

	/** 存放不同的日期模板格式的sdf的Map */
	private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();

	/**
	 * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
	 * 
	 * @param pattern
	 * @return
	 */
	private static SimpleDateFormat getSdf(final String pattern) {
		ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);

		synchronized (lockObj) {
			tl = sdfMap.get(pattern);
			if (tl == null) {
				// 只有Map中还没有这个pattern的sdf才会生成新的sdf并放入map
				System.out.println("put new sdf of pattern " + pattern + " to map");

				// 这里是关键,使用ThreadLocal<SimpleDateFormat>替代原来直接new
				// SimpleDateFormat
				tl = new ThreadLocal<SimpleDateFormat>() {

					@Override
					protected SimpleDateFormat initialValue() {
						System.out.println("thread: " + Thread.currentThread() + " init pattern: " + pattern);
						return new SimpleDateFormat(pattern);
					}
				};
				sdfMap.put(pattern, tl);
			}
		}

		return tl.get();
	}

	/**
	 * 是用ThreadLocal
	 * <SimpleDateFormat>来获取SimpleDateFormat,这样每个线程只会有一个SimpleDateFormat
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		return getSdf(pattern).format(date);
	}

	public static Date parse(String dateStr, String pattern) throws ParseException {
		return getSdf(pattern).parse(dateStr);
	}
	
	public static String influxToNormalDate(String influxTime) {
		SimpleDateFormat isdf = getSdf(INFLUX_FORMATTER);
		isdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		try {
			String normalTime = getSdf(FORMATTER).format(isdf.parse(influxTime));
			return normalTime;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String normalToInfluxDate(String normalTime) throws ParseException {
		SimpleDateFormat isdf = getSdf(INFLUX_FORMATTER);
		isdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		String influxTime = isdf.format(getSdf(FORMATTER).parse(normalTime));
		return  influxTime;
	}
	/**
	 * 
	 * @Description: 增加一个日期类型转Influxdb时间格式的方法
	 * @author: LiWenming 
	 * @date:   2019年4月17日 下午1:53:27 
	 * @throws
	 */
	public static String normalDateToInfluxDate(Date normalTime) throws ParseException {
		SimpleDateFormat isdf = getSdf(INFLUX_FORMATTER);
		isdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		String influxTime = isdf.format(normalTime);
		return  influxTime;
	}
	
	public static String reduceInfluxDate(String normalTime, int reduceDay) throws ParseException {
		Date nowDate = getSdf(FORMATTER).parse(normalTime);
		Calendar calendar = Calendar.getInstance();    
		calendar.setTime(nowDate);    
		calendar.add(Calendar.DATE, reduceDay);
		nowDate = calendar.getTime();
		SimpleDateFormat isdf = getSdf(INFLUX_FORMATTER);
		isdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		return isdf.format(nowDate);
	}
	/**
	 * 
	 * @Description: 年月日时分秒 日期加减天数后获得  年月日时分秒 
	 * @author: LiWenming 
	 * @date:   2019年3月21日 下午4:47:46 
	 * @throws
	 */
	public static String addDate(String date, int reduceDay) throws ParseException {
		Date nowDate = getSdf(FORMATTER).parse(date);
		Calendar calendar = Calendar.getInstance();    
		calendar.setTime(nowDate);    
		calendar.add(Calendar.DATE, reduceDay);
		nowDate = calendar.getTime();
		SimpleDateFormat isdf = getSdf(FORMATTER);
		return isdf.format(nowDate);
	}
	
	public static String reduceNormalZoreDate(String normalTime, int reduceDay) throws ParseException {
		Date nowDate = getSdf(FORMATTER).parse(normalTime);
		Calendar calendar = Calendar.getInstance();    
		calendar.setTime(nowDate);    
		calendar.add(Calendar.DATE, reduceDay);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		nowDate = calendar.getTime();
		return getSdf(FORMATTER).format(nowDate);
	}
	
	
	/**
	 * 获取当前时间的零点时间
	 * @param currentTime
	 * @return
	 */
	public static Date getDateZoreTime(String currentTime) {
		try {
			Calendar currentDate = Calendar.getInstance();
			currentDate.setTime(getSdf(FORMATTER).parse(currentTime));
			currentDate.set(Calendar.HOUR_OF_DAY, 0);
			currentDate.set(Calendar.MINUTE, 0);
			currentDate.set(Calendar.SECOND, 0);
			return currentDate.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取当前时间的零点的influxdb时间
	 * @param currentTime
	 * @return
	 */
	public static String getDateZoreInfuxTime(String currentTime) {
		return getSdf(INFLUX_FORMATTER).format(getDateZoreTime(currentTime));
	}
	/**
	 * 
	 * @Description: 获得当月的第一天0时0分0秒的毫秒值    
	 * @author: LiWenming 
	 * @date:   2019年4月3日 上午10:08:05 
	 * @throws
	 */
	 
	public static long getFirstDayOfMonth() throws ParseException {
		Calendar calendar = Calendar.getInstance();  
		calendar.set(Calendar.DAY_OF_MONTH,1);    
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);
		return calendar.getTime().getTime();
	}
/**
 * 
 * @Description: 返回两个时间点之间的所有分钟，天，小时 
 * @author: LiWenming 
 * @date:   2019年4月3日 下午2:56:21 
 * @throws
 */
	public static List<String> getListTime(long startMs, long endMs,String type) {
		SimpleDateFormat sdf =null;
		int field =0;
		switch (type) {
		//小时
		case "h":
			sdf = getSdf(HOUR_FORMATTER);
			field =Calendar.HOUR;
			break;
			//分钟
		case "m":
			sdf = getSdf(MIN_FORMATTER);
			field =Calendar.MINUTE;
			break;
			//天
		case "d":
			sdf = getSdf(DAY_FORMATTER);
			field =Calendar.DAY_OF_MONTH;
			break;
		default:
			break;
		}
		List<String> betweenList = new ArrayList<String>();
		// 开始结束时间校验
		if (startMs > endMs) {
			return betweenList;
		}
		//结束日期转换
		String end = sdf.format(new Date(endMs));
		Calendar start = Calendar.getInstance();
		start.setTime(new Date(startMs));
		start.add(field, -1);
		while (true) {
			start.add(field, 1);
			Date newDate = start.getTime();
			String newend = sdf.format(newDate);
			betweenList.add(newend);
			if (end.equals(newend)) {
				break;
			}
		}
		return betweenList;
	}
	/**
	 * 
	 * @Description: 判断当前毫秒值是否为今天    
	 * @author: LiWenming 
	 * @date:   2019年4月23日 下午6:29:36 
	 * @throws
	 */
	public static boolean isToday(long startMs) {
		SimpleDateFormat sft = getSdf(DAY_FORMATTER);
		String dateString = sft.format(new Date(startMs));
		String today = sft.format(new Date());
		return today.equals(dateString);
	}
	public static Date getDateFromString(String str) throws ParseException {
		SimpleDateFormat sft = getSdf(FORMATTER);
		Date date = sft.parse(str);
		return date;
	}
	public static String getStringFromDate(long str) throws ParseException {
		SimpleDateFormat sft = getSdf(FORMATTER);
		String date = sft.format(new Date(str));
		return date;
	}
	public static boolean isCurrentMonth(Date date) {
		
		Calendar currentDate = Calendar.getInstance();
		currentDate.setTime(date);
		int month = currentDate.get(Calendar.MONTH);
		currentDate.setTime(new Date());
		int currentMonth = currentDate.get(Calendar.MONTH);
		System.out.println("month="+month+",currentMonth="+currentMonth+",result="+(month==currentMonth));
		return month==currentMonth;
}
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sft = getSdf(DAY_FORMATTER);
		Date d = sft.parse("2019-06-06");
		isCurrentMonth(d);
	}
}
