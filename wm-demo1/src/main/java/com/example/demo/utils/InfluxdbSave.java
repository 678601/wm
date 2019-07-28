package com.example.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;

import com.example.demo.vo.AlarmMsgSendDetailDo;

import okhttp3.OkHttpClient;

/**
 * influxdb中保存数据
 * 
 * @author LiWenming 2019年5月8日下午8:48:48
 */
public class InfluxdbSave {
	private static InfluxDB influxdb;

	public static void main(String[] args) throws ParseException {
		for (int i = 0; i < 3; i++) {
			AlarmMsgSendDetailDo detailDo = new AlarmMsgSendDetailDo();
			detailDo.setService("test42999");
			detailDo.setRuleCategory("03");
			detailDo.setDestSys("KMS");
			detailDo.setMetricType("2");
			detailDo.setValue(1);
			InfluxdbSave test = new InfluxdbSave();
			test.writeAlarmStatistic(detailDo);
		}

	}

	// rp_10day.serviceStatisticHourData
	public void writeAlarmStatistic(AlarmMsgSendDetailDo detail) throws ParseException {
//		String service = StringUtils.isEmpty(detail.getService()) ? "-" : detail.getService(); show continuous queries
		String[] ppalarm = { "http://10.18.8.239:8086", "pp", "pp", "ppmonitor", "rp_30day", "alarmStatistic", "0" };
		String[] esb = { "http://10.253.127.17:8086", "esb", "esb", "esbmonitor", "", "serviceInfo", "1" };
		String[] ppservice = { "http://10.18.8.239:8086", "pp", "pp", "ppmonitor", "rp_1day",
				"serviceStatisticMinuteData", "2" };
		String[] ppservice10day = { "http://10.18.8.239:8086", "pp", "pp", "ppmonitor", "rp_10day",
				"serviceStatisticHourData", "2" };
		String[] test = ppservice10day;
		// 时间
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = ft.parse("2019-04-29 15:12:10");
//		Date date = new Date();
		if (null == influxdb) {
			OkHttpClient okhc = new OkHttpClient();
			OkHttpClient.Builder builder = okhc.newBuilder().readTimeout(60, TimeUnit.SECONDS).connectTimeout(60,
					TimeUnit.SECONDS);
			influxdb = InfluxDBFactory.connect(test[0], test[1], test[2], builder);
			influxdb.setDatabase(test[3]).setRetentionPolicy(test[4]);
		}
		Point point = null;
		if ("0".equals(test[6])) {
			point = Point.measurement(test[5]).time(date.getTime(), TimeUnit.MILLISECONDS)
					.tag("ruleCategory", detail.getRuleCategory()).tag("sysCode", detail.getDestSys())
					.tag("service", detail.getService()).tag("metricType", detail.getMetricType())
					.addField("value", detail.getValue()).build();
		} else if ("1".equals(test[6])) {
			/*
			 * branchNo destSys host metric_type oriSys retMsgCode service
			 */
			point = Point.measurement(test[5]).time(date.getTime(), TimeUnit.MILLISECONDS)
					.tag("branchNo", detail.getRuleCategory()).tag("destSys", detail.getDestSys())
					.tag("host", detail.getService()).tag("metric_type", detail.getMetricType())
					.tag("oriSys", detail.getService()).tag("retMsgCode", detail.getService())
					.tag("service", detail.getService()).addField("count", detail.getValue()).build();
		} else {
//			addTag("destSys", destSys)
//			.addTag("destSysIP", destSysIP)
//			.addTag("destApp", destApp)
//			.addTag("service", ServiceUrl)
//			.addTag("oriSys", oriSys)
//			.addTag("oriApp", oriApp)
//			.addTag("retMsgCode", retMsgCode)
//			.addField("requestCount", requestCount)
//			.addField("responseTime", responseTime)
//			.addField("satisfactory", satisfied)
//			.addField("tolerating",tolerable)
//			.addField("frustrated",intolerable)
//		requestCount,responseTime,maxResponseTime,minResponseTime,meanResponseTime,medianResponseTime,
//			percentile_90,satisfactory,tolerating,frustrated 		 
			point = Point.measurement(test[5]).time(date.getTime(), TimeUnit.MILLISECONDS)
					.tag("destSys", detail.getDestSys()).tag("destSysIP", detail.getRuleCategory())
					.tag("service", detail.getService()).tag("destApp", detail.getMetricType())
					.tag("oriSys", detail.getService()).tag("retMsgCode", detail.getService())
					.addField("requestCount", detail.getValue()).addField("responseTime", detail.getValue() + 10)
					.build();
		}
		influxdb.write(point);
	}
}
