package com.example.demo.common;

public enum EnumTest {
	MON("01","周1"), TUE("02","周2"), WED("03","周3"), THU("04","周4"), FRI("05","周5"), SAT("06","周6"), SUN("07","周7");
    //防止字段值被修改，增加的字段也统一final表示常量
    private final String key;
    private final String value;
	private EnumTest(String key,String value) {
		this.key=key;
		this.value=value;
	}
    //根据key获取枚举
    public static EnumTest getEnumByKey(String key){
        if(null == key){
            return null;
        }
        for(EnumTest temp:EnumTest.values()){
            if(temp.getKey().equals(key)){
                return temp;
            }
        }
        return null;
    }
  //根据key获取枚举
    public static String getEnumValueByKey(String key){
        if(null == key){
            return null;
        }
        for(EnumTest temp:EnumTest.values()){
            if(temp.getKey().equals(key)){
                return temp.getValue();
            }
        }
        return null;
    }
    public String getKey() {
        return key;
    }
    public String getValue() {
        return value;
    }
}
