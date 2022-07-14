package com.jsoup;

import lombok.SneakyThrows;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * @Author lyuf
 * @Date 2022/6/10 14:27
 * @Version 1.0
 */
public class demo {
    @SneakyThrows
    public static void main(String[] args) {
        String destFilePath = "D:\\2021年国考行测真题(副省级).txt";
        File file = new File(destFilePath);
        FileWriter fileWriter = new FileWriter(file);
        long start = System.currentTimeMillis();
        // 爬虫工具包 jsoup
        try {
            // 首先要拿到我们请求的网页的地址
            String url = "https://www.zxgj.cn/g/xingce";
            // Jsoup 来模拟一下手动设置 cookie 方式
//            Connection.Response res = Jsoup.connect("https://user.qzone.qq.com/proxy/domain/m.qzone.qq.com/cgi-bin/new/get_msgb")
//                    .data("username", qq, "password", pwd)
//                    .method(Connection.Method.GET).execute();

            // 用Jsoup的parse()方法解析网页，传入连个参数第一个参数是new URL(url),第二个参数设置解析时间如果超过30秒就放弃,然后获取到一个Document对象
//            Document document = Jsoup.parse(new URL(url), 30000);
//            Document document = Jsoup.parse(input, "UTF-8", "http://example.com/");

            Document document = Jsoup.parse(new URL(url), 30000);
            Elements content = document.getElementsByClass("tw-flex-1 tw-text-size-base tw-overflow-hidden tw-overflow-ellipsis tw-whitespace-nowrap");
            String text = content.text();
            String replace = text.replace(" ", "\n");
            fileWriter.write(replace);

            // Document对象可以实现JS的所有操作

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fileWriter.flush();
            fileWriter.close();
            long end = System.currentTimeMillis();
            System.out.println("爬取完成，耗时：【" + (end - start) + "】ms");
        }


    }
}
