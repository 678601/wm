package com.example.demo.vo;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 告警消息发送详情实体
 * @author zhaoyangxia
 * @date 2018/4/2
 */
public class AlarmMsgSendDetailDo {

	private Long id;
	private Long ruleId;	//规则ID，1-5：对应各个告警规则表的主键，6：对应kafka的warningParameterId字段，也就是AMP_WARNING_PARAMETER的主键，7：对应于日志配置表BATCH_JOB_PROPERTY的主键
	private String ruleCategory;	//规则分类，1:服务告警 2:服务器告警 3:前端告警 4:oracle告警 5:批作业告警 6:日志告警7:批作业巡检过程中告警
	private Double boundaryFrom;//阈值上限，6：对应kafka的logErrorLimit字段
	private Double boundaryTo;//阈值下限
	private String metricType;	//指标类型，对应各个告警规则表的metric_type字段，1-5：对应各个告警规则表的主键，6：kefka中metric_type字段，0：连续笔数，1：累计笔数，2：日志占比
	private String metricName;	//指标名称
	private Integer value;	//扫描出来的值，6：对应kafka的logErrorCount字段或者logErrorPercent字段
	private int logTimeRange;//日志时间范围，6：对应kafka的logTimeRange字段（ 1：累计笔数，2：日志占比才涉及这个字段）
	private String destSys;	//目标系统
	private String service;	//服务
	private String oriSys;	//请求系统
	private String branchNo;	//分支机构号
	private String hostIp;	//主机IP
	private String dbInstanceName;	//数据库实例名
	private String batchJobName;		//批作业名
	private String queueName;
	private String nodeName; //节点名称
	private Integer logWindows;	//日志错误告警窗口大小
	private Integer alarmType;	//告警类型，0：批量统计告警，1：实时日志错误告警，2：实时日志错误撤销告警，3：响尾蛇告警，4：日志巡检过程中告警
	private String content;	//告警内容
	private String  sendTo;	//发送对象
	private Date sendTime;	//发送时间
	private Date gmtCreate;	//创建时间
	private Date gmtModified;	//修改时间

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public int getLogTimeRange() {
		return logTimeRange;
	}

	public void setLogTimeRange(int logTimeRange) {
		this.logTimeRange = logTimeRange;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRuleId() {
		return ruleId;
	}
	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}
	public String getMetricName() {
		return metricName;
	}
	public void setMetricName(String metricName) {
		this.metricName = metricName;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getDestSys() {
		return destSys;
	}
	public void setDestSys(String destSys) {
		this.destSys = destSys;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getOriSys() {
		return oriSys;
	}
	public void setOriSys(String oriSys) {
		this.oriSys = oriSys;
	}
	public String getBranchNo() {
		return branchNo;
	}
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}
	public String getHostIp() {
		return hostIp;
	}
	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}
	public String getDbInstanceName() {
		return dbInstanceName;
	}
	public void setDbInstanceName(String dbInstanceName) {
		this.dbInstanceName = dbInstanceName;
	}
	public String getBatchJobName() {
		return batchJobName;
	}
	public void setBatchJobName(String batchJobName) {
		this.batchJobName = batchJobName;
	}
	public Integer getLogWindows() {
		return logWindows;
	}
	public void setLogWindows(Integer logWindows) {
		this.logWindows = logWindows;
	}
	public Integer getAlarmType() {
		return alarmType;
	}
	public void setAlarmType(Integer alarmType) {
		this.alarmType = alarmType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSendTo() {
		return sendTo;
	}
	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public Date getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	public String getRuleCategory() {
		return ruleCategory;
	}
	public void setRuleCategory(String ruleCategory) {
		this.ruleCategory = ruleCategory;
	}
	
	public Double getBoundaryFrom() {
		return boundaryFrom;
	}
	public void setBoundaryFrom(Double boundaryFrom) {
		this.boundaryFrom = boundaryFrom;
	}
	public Double getBoundaryTo() {
		return boundaryTo;
	}
	public void setBoundaryTo(Double boundaryTo) {
		this.boundaryTo = boundaryTo;
	}
	public String getMetricType() {
		return metricType;
	}
	public void setMetricType(String metricType) {
		this.metricType = metricType;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
}