package com.example.demo.utils;

import java.io.UnsupportedEncodingException;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


//【Java使用3DES加密解密的流程】
//①传入共同约定的密钥（keyBytes）以及算法（Algorithm），来构建SecretKey密钥对象
//    SecretKey deskey = new SecretKeySpec(keyBytes, Algorithm);    
//②根据算法实例化Cipher对象。它负责加密/解密
//    Cipher c1 = Cipher.getInstance(Algorithm);    
//③传入加密/解密模式以及SecretKey密钥对象，实例化Cipher对象
//    c1.init(Cipher.ENCRYPT_MODE, deskey);    
//④传入字节数组，调用Cipher.doFinal()方法，实现加密/解密，并返回一个byte字节数组
//    c1.doFinal(src);
 /**
  * 
  * @Description:   {3DES加密解密的工具类 } 
  * @date:   2019年6月26日 下午3:42:54   
  * @version V1.0
  */
@Component
public class SecretUtils {
 
    //定义加密算法，有DES、DESede(即3DES)、Blowfish
//    private static  String Algorithm = "DESede";    
    private static  String PASSWORD_CRYPT_KEY ;
    //在java的IvParameterSpec中，偏移量必须是8位的，否则直接报错
    private static final String VI = " 2019amp20190704";
    
    @Value("${des.password}")
    public void setPassWord(String passWord) {
	   PASSWORD_CRYPT_KEY=passWord;
	   try{
		   //处理 异常 No such provider: BC
		   Security.addProvider(new BouncyCastleProvider());
		   }catch(Exception e){
		   e.printStackTrace();
		   }

    }
    
    /**
     * 加密方法，CBC,  OFB,  CFB三个模式，都是根据前面加密块的内容，对key进行新一轮处理后再，
     * 再对下一数据块进行处理，如此类推下去，这样一来，加密的强度也有所增强。他们都需要用到初始化向量IV
     * PKCS5Padding：缺几个字节就补充几个字节的几； 少1个byte,就补一个byte的 0x01;
     * @param src 源数据的字节数组
     * @return 
     */
    public static byte[] encryptMode(byte[] src) {
        try {
//             SecretKey deskey = new SecretKeySpec(build3DesKey(PASSWORD_CRYPT_KEY), Algorithm);    //生成密钥
//             Cipher c1 = Cipher.getInstance(Algorithm);    //实例化负责加密/解密的Cipher工具类
             SecretKeySpec deskey = new SecretKeySpec(build3DesKey(PASSWORD_CRYPT_KEY), "AES");
             Cipher c1 = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");
             IvParameterSpec iv = new IvParameterSpec(VI.getBytes());
             c1.init(Cipher.ENCRYPT_MODE, deskey,iv);    //初始化为加密模式
             return c1.doFinal(src);
         } catch (java.security.NoSuchAlgorithmException e1) {
             e1.printStackTrace();
         } catch (javax.crypto.NoSuchPaddingException e2) {
             e2.printStackTrace();
         } catch (java.lang.Exception e3) {
             e3.printStackTrace();
         }
         return null;
     }
    
    
    /**
     * 解密函数
     * @param src 密文的字节数组
     * @return
     */
    public static byte[] decryptMode(byte[] src) {      
        try {
            SecretKey deskey = new SecretKeySpec(build3DesKey(PASSWORD_CRYPT_KEY), "AES"); 
            Cipher c1 = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");
            IvParameterSpec iv = new IvParameterSpec(VI.getBytes());
            c1.init(Cipher.DECRYPT_MODE, deskey,iv);    //初始化为解密模式
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
     }
    
    
    /*
     * 根据字符串生成密钥字节数组 
     * @param keyStr 密钥字符串
     * @return 
     * @throws UnsupportedEncodingException
     */
    public static byte[] build3DesKey(String keyStr) throws UnsupportedEncodingException{
        byte[] key = new byte[24];    //声明一个24位的字节数组，默认里面都是0
        byte[] temp = keyStr.getBytes(DesUtil.CHARSET);    //将字符串转成字节数组
        
        /*
         * 执行数组拷贝
         * System.arraycopy(源数组，从源数组哪里开始拷贝，目标数组，拷贝多少位)
         */
        if(key.length > temp.length){
            //如果temp不够24位，则拷贝temp数组整个长度的内容到key数组中
            System.arraycopy(temp, 0, key, 0, temp.length);
        }else{
            //如果temp大于24位，则拷贝temp数组24个长度的内容到key数组中
            System.arraycopy(temp, 0, key, 0, key.length);
        }
        return key;
    } 
}
