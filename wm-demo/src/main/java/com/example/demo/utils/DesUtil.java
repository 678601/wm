package com.example.demo.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @Description: 加解密工具类
 * @date: 2019年6月26日 下午4:05:36
 * @version V1.0
 */
public class DesUtil {
	
	private final static Logger log = LoggerFactory.getLogger(DesUtil.class);
	
	public final static String CHARSET = "UTF-8";
	/**
	 * 
	 * @Description: 加密 @param unEncrypt @return @date: 2019年6月26日
	 * 下午4:05:07 @author @throws
	 * 加密流程：3DES先加密，Base64再编码
	 */
	public static String encrypt(String unEncrypt) {
		byte[] encryptResult = null;
		try {
			encryptResult = SecretUtils.encryptMode(unEncrypt.getBytes(CHARSET));
		} catch (Exception e) {
			log.error("encrypt加密异常，",e);
			return null;
		}
		return Base64.getEncoder().encodeToString(encryptResult);
	}

	/**
	 * @throws UnsupportedEncodingException
	 * 
	 * @Description: 解密 @param encrypt @return @date: 2019年6月26日
	 * 下午4:05:18 @author @throws
	 * 解密流程：Base64先解码，3DES再解密
	 */
	public static String decrypt(String encrypt)  {
		byte[] secretArr = Base64.getDecoder().decode(encrypt);
		byte[] myMsgArr = SecretUtils.decryptMode(secretArr);
		String result="";
		try {
			result = new String(myMsgArr, CHARSET);
		} catch (UnsupportedEncodingException e) {
			log.error("decrypt解密异常，",e);
		}
		return result;
	}

//	public static void main(String[] args) throws UnsupportedEncodingException {
//		String msg = "5192";
//		log.info("【加密前】：{}", msg);

//		// 加密
//		String m = DesUtil.encrypt(msg);
//		log.info("【加密后】：{}" , m);
//
//		// 解密
//		String b = DesUtil.decrypt(m);
//		log.info("【解密后】：{}" , b);
//		String test ="这是";
//		String b = Base64.getEncoder().encodeToString(test.getBytes(CHARSET));
//		log.info("b={}",b);
//	}
}
