package com.example.demo.utils.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author LiWenMing
 * @Description 图片处理
 * @createTime 2021/2/2/0002 22:47
 */
public class ImageTest {
    public static void main(String[] args) throws Exception{
        ////////////////////////////////////////////
//        String fileName="D:\\2.png";
//        String text="第2幅图片";
//        createImage(fileName,text);
        ///////////////////////////////////////////
//        String file1="D:\\1.png";
//        String file2="D:\\2.png";
//        String fileName="D:\\12.png";
//        combineImages(file1,file2,fileName);
        //////////////////////////////////////////
        String s = imageToString("D:\\2.png");
        stringToImage(s,"D:\\2222.png");
    }
    /*/**
     * @description 图片生成
     * @author LiWenMing
     * @createTime 2021/2/3/0003 20:36
     */
    public static void createImage(String fileName,String text){
        BufferedImage bufferedImage = new BufferedImage(200,100,1);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(Color.BLUE);
        graphics.fillRect(10,20,160,60);
        graphics.setFont(new Font("xx",Font.BOLD,25));
        graphics.setColor(Color.GREEN);

        graphics.drawString(text,40,50);
        //输出文件
        File file = new File(fileName);
        try {
            ImageIO.write(bufferedImage, "GIF", file);
        } catch (Exception  e) {
            e.printStackTrace();
        }

        //释放资源
        graphics.dispose();
    }
    /*/**
     * @description  combined - 必应词典 美[kəm'baɪnd]英[kəm'baɪnd]
     * @author LiWenMing
     * @createTime 2021/2/3/0003 20:44
     */
    public static void combineImages(String fileName1,String fileName2,String newFileName){
        File file1=new File(fileName1);
        File file2=new File(fileName2);
        BufferedImage bufferedImage1= null;
        BufferedImage bufferedImage2= null;
        try {
            bufferedImage1 = ImageIO.read(file1);
            bufferedImage2 = ImageIO.read(file2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int height1 = bufferedImage1.getHeight();
        int height2 = bufferedImage2.getHeight();
        int newHeight=height1+height2+200;
        BufferedImage bufferedImage = new BufferedImage(200,newHeight,1);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(Color.yellow);
        graphics.fillRect(0,0,190,230);
        graphics.setFont(new Font("xx",Font.BOLD,25));
        graphics.drawString("合成新图片",10,newHeight-10);
        graphics.drawImage(bufferedImage1,10,10,null);
        graphics.drawImage(bufferedImage2,10,110,null);
        //输出文件
        File file = new File(newFileName);
        try {
            ImageIO.write(bufferedImage, "GIF", file);
        } catch (Exception  e) {
            e.printStackTrace();
        }

        //释放资源
        graphics.dispose();
    }
    /*/**
     * @description 图片转为字符串 imageToString
     * @author LiWenMing
     * @createTime 2021/2/3/0003 21:16
     */
    public static String imageToString(String filePath) throws Exception{
        BufferedImage image = ImageIO.read(new File(filePath));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image,"PNG",out);
//        String string = out.toString();
        StringBuffer sb2 = new StringBuffer();
        byte[] img = out.toByteArray();
        for (int i = 0; i < img.length; i++) {
            if (sb2.length() == 0) {
                sb2.append(img[i]);
            } else {
                sb2.append("," + img[i]);
            }
        }
        return sb2.toString();
//        System.out.println(sb2.toString());
    }

    /*/** 字符串转图片
     * @description
     * @author LiWenMing
     * @createTime 2021/2/3/0003 21:24
     */
    public static void stringToImage(String string,String filePath) throws Exception{
        if (string.contains(",")) {
            // 这里没有自带的那个分割方法，原因是分割速度没有这个快，有人考证在分割字符长度很大的情况下，系统的分割方法容易造成内存溢出。
            // 还有subString方法，不知道最新版本的jdk改了源码了么
            String[] imagetemp = string.split(",");
            byte[] image = new byte[imagetemp.length];
            for (int i = 0; i < imagetemp.length; i++) {
                image[i] = Byte.parseByte(imagetemp[i]);
            }
//        byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(image);
            BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
            File file = new File(filePath);
            ImageIO.write(bufferedImage,"PNG",file);
        } else {
            // 不能解析格式的字符串
        }
    }
}
