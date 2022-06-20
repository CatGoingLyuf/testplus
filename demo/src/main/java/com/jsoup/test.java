package com.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @Author lyuf
 * @Date 2022/6/20 15:30
 * @Version 1.0
 */
//抑制所有的黄色警告
@SuppressWarnings("all")
public class test {
    // 读取 形成txt文档
    public static void main(String[] args) {
        // 创建存放文本的地址和文件名
        File file = new File("D:\\jsoup.txt");
        try {



            // 方式一  输入你要爬取的URL
            Document document = Jsoup.connect("http://www.baidu.com/").get();
            // 方式二
            Document parse = Jsoup.parse(new URL("http://www.baidu.com/"), 1000 * 10);

            // 获取HTML文本的内容
            String title = document.title();

            // 通过HTML文本的内容的id来获取内容





        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
