package com.example.demo.utils.fileUtils;

import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author LiWenMing
 * @Description TODO
 * @createTime 2021/2/4/0004 20:15
 */
//@Test
public class FileUtil {

    final static String parentPath="D:/fileTest20210204";

    /*/** 新建文件夹
     * @description  Java 在 UNIX 和 Windows 自动按约定分辨文件路径分隔符。如果你在 Windows 版本的 Java 中使用分隔符 (/) ，路径依然能够被正确解析。
     * @author LiWenMing
     * @createTime 2021/2/4/0004 20:27
     */
//    @Test
    public static void createFloder(){
//        String path="D:\\fileTest\\";
        String path="D:/fileTest11/";
        String name="test.txt";
//        String name="test.txt";
        File file =new File(path+name);
        file.mkdirs();
    }
    /*/**
     * @description 带目录创建，报错，找不到指定路径，需要判断目录是否存在，不存在，先建目录
     * @author LiWenMing
     * @createTime 2021/2/4/0004 20:37
     */
//    @Test
    public static void test1(){
////        String path="D:\\fileTest\\";
//        String path="D:/fil/12";
//        String name="1.txt";
////        String name="test.txt";
//        File file =new File(path+name);
//        try {
//            file.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        File file = new File("D:\\12311\\123.txt");
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static File createFile(String path){
        File file = new File(path);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
    /*/** 读写文件
     * @description
     * @author LiWenMing
     * @createTime 2021/2/4/0004 21:01
     */
//    @Test
    public static void testStream() {
        try {
//            byte bWrite[] = { 0, 21, 3, 40, 5 };
            byte bWrite[] = "测试".getBytes(StandardCharsets.UTF_8);
            OutputStream os = new FileOutputStream("D:/test.txt");
            for (int x = 0; x < bWrite.length; x++) {
                os.write(bWrite[x]); // writes the bytes
            }
            os.close();

            InputStream is = new FileInputStream("D:/test.txt");
            int size = is.available();

            for (int i = 0; i < size; i++) {
                System.out.print((char) is.read() + "  ");
            }
            is.close();
        } catch (IOException e) {
            System.out.print("Exception");
        }
    }
    /*/** 上面例子可能乱码，以下更好
     * @description
     * @author LiWenMing
     * @createTime 2021/2/4/0004 21:09
     */
    @Test
    public static void test4() throws IOException {

        File f = createFile(parentPath+"/1.txt");
        FileOutputStream fop = new FileOutputStream(f);
        // 构建FileOutputStream对象,文件不存在会自动新建（无路径时）

        OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");
        // 构建OutputStreamWriter对象,参数可以指定编码,默认为操作系统默认编码,windows上是gbk

        writer.append("中文输入");
        // 写入到缓冲区

        writer.append("\r\n");
        // 换行

        writer.append("English");
        // 刷新缓存冲,写入到文件,如果下面已经没有写入的内容了,直接close也会写入

        writer.close();
        // 关闭写入流,同时会把缓冲区内容写入文件,所以上面的注释掉

        fop.close();
        // 关闭输出流,释放系统资源

        FileInputStream fip = new FileInputStream(f);
        // 构建FileInputStream对象

        InputStreamReader reader = new InputStreamReader(fip, "UTF-8");
        // 构建InputStreamReader对象,编码与写入相同

        StringBuffer sb = new StringBuffer();
        while (reader.ready()) {
            sb.append((char) reader.read());
            // 转成char加到StringBuffer对象中
        }
        System.out.println(sb.toString());
        reader.close();
        // 关闭读取流

        fip.close();
        // 关闭输入流,释放系统资源

    }
}
