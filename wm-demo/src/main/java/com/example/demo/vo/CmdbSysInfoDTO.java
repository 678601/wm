package com.example.demo.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Cmdb模块实体类
 *
 * @author yaozhinan
 * @createDate 20180418
 * cmdb信息在redis中的存储规则：
 * 1、应用系统：map结构存储，固定hash的key为sysinfo；应用的key为sysinfo-应用编码，value为CmdbSysInfoVo对象
 * 例子：redisClient.opsForHash().get("sysinfo","sysinfo-"+"AMP");
 * 2、应用模块：map结构存储，固定hash的key为sysmodule；模块的key为sysmodule-模块编码，value为CmdbSysModuleVo对象
 * 例子： redisClient.opsForHash().get("sysmodule","sysmodule-"+"AMP-02-manage");
 * 3、服务器信息：
 * 1) 全部：map结构存储，固定hash的key为serverinfo；服务器的key为serverinfo-服务器ip，value为CmdbServerInfoVo对象
 * 例子： redisClient.opsForHash().get("serverinfo","serverinfo-"+"10.10.10.10");
 * 4、服务器信息-hostname为key： map结构存储，固定hash的key为serverinfo-hostname；
 * 服务器的key为serverinfo-服务器hostname，value为ip地址
 * <p>
 * 下面为对外提供的存储单元：
 * 1、根据应用编码获取全部模块信息：key为sysinfo-module-应用编码，value为模块编码数据，用|分隔
 * <p>
 * 2、1）根据应用编码获取全部服务器ip：key为sysinfo-server-应用编码
 * 2）根据应用编码获取数据库（数云）服务器ip：key为sysinfo-server-db-应用编码
 * 3）根据应用编码获取非数据库（zabbix）服务器ip：key为sysinfo-server-other-应用编码
 * value均为ip数据，用|分隔
 * <p>
 * 3、1）根据模块编码获取全部服务器ip：key为sysmodule-server-模块编码
 * 2）根据模块编码获取数据库（数云）服务器ip：key为sysmodule-server-db-模块编码
 * 3）根据模块编码获取非数据库（zabbix）服务器ip：key为sysmodule-server-other-模块编码
 * value均为ip数据，用|分隔
 */
public class CmdbSysInfoDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2601695818681755373L;

    public CmdbSysInfoDTO() {
        super();
    }

    public CmdbSysInfoDTO(String description, String code) {
		super();
		Description = description;
		Code = code;
	}

	/**
     * 主键 id
     */
    private String _id;

    /**
     * 应用系统描述 sysName
     */
    private String Description;

    /**
     * 应用系统编码，统一定义的标准系统编码 sysCode
     */
    private String Code;

    public String get_Id() {
        return _id;
    }

    public void set_Id(String _id) {
        this._id = _id;
    }

    public String getDescription() {
        return Code + "-" + Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCode() {
        return Code;
    }

    @Override
	public String toString() {
		return "CmdbSysInfoDTO [_id=" + _id + ", Description=" + Description + ", Code=" + Code + "]";
	}

	public void setCode(String code) {
        Code = code;
    }


}
