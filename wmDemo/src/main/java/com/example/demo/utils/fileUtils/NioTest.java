package com.example.demo.utils.fileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/** nio 测试
 * @author LiWenMing
 * @Description TODO
 * @createTime 2021/2/4/0004 21:11
 */
public class NioTest {
    public static void main(String[] args) {
        try {
            Files.createDirectories(Paths.get("D:/123"));
            System.out.println("创建文件夹目录成功！");
            //中间路径不存在依然报错
            Files.createFile(Paths.get("D:/123/1/123.txt"));
            System.out.println("创建文件成功！");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
