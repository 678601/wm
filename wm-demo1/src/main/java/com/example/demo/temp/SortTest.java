package com.example.demo.temp;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.vo.CmdbSysInfoDTO;

public class SortTest {
	private final static Comparator<Object> CHINA_COMPARE = Collator.getInstance(java.util.Locale.CHINA);

	public static void main(String[] args) {
		List<CmdbSysInfoDTO> sysInfoList = new ArrayList<CmdbSysInfoDTO>();
		sysInfoList.add(new CmdbSysInfoDTO("集","集团 - UCP"));
		sysInfoList.add(new CmdbSysInfoDTO("资","资产 - ECP"));
		sysInfoList.add(new CmdbSysInfoDTO("财","财产险 - SAP"));
		sysInfoList.add(new CmdbSysInfoDTO("a","财产险 - SAP"));
		sysInfoList.add(new CmdbSysInfoDTO("ZJzs - Web - 浙江舟山分公司网站系统","财产险 - SAP"));
		sysInfoList.add(new CmdbSysInfoDTO("e-chinalife-外部网站系统 ","财产险 - SAP"));
        //获取中文环境
        Comparator<Object> com = Collator.getInstance(java.util.Locale.CHINA);
		// TODO Auto-generated method stub
//		sysInfoList.stream().sorted(Comparator.comparing(CmdbSysInfoDTO::getDescription)).collect(Collectors.toList());
		sysInfoList.stream().sorted(new Comparator<CmdbSysInfoDTO>(){
		    @Override
		    public int compare(CmdbSysInfoDTO o1, CmdbSysInfoDTO o2) {
		        return com.compare(o1.getDescription(),o2.getDescription());
		    }
		}).forEach(o->System.out.println(o));
	}

}
