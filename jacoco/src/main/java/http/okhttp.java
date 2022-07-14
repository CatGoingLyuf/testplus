package http;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * @Author lyuf
 * @Date 2022/7/13 16:17
 * @Version 1.0
 */
@Slf4j
public class okhttp {
    private static final String host = "https://www.zxgj.cn/phpexcel/getgwylist";
    private static OkHttpClient okHttpClient = new OkHttpClient();

    public static void main(String[] args) {

    }

    @Test
    public void post1() throws IOException {
        String name = "";
        String year = "";
        System.out.println("请输入序号选择下载文件：");
        log.info("【1】 : 2021年国考行测真题(副省级)");
        log.info("【2】 : 2021年国考行测真题(地市级)");
        log.info("【3】 : 2020年国考行测真题(副省级)");
        log.info("【4】 : 2020年国考行测真题(地市级)");
        log.info("【5】 : 2019年国考行测真题(副省级)");
        log.info("【6】 : 2019年国考行测真题(地市级)");
        log.info("【7】 : 2018年国考行测真题(副省级)");
        log.info("【8】 : 2018年国考行测真题(地市级)");
        Scanner scanner = new Scanner(System.in);
        int i1 = scanner.nextInt();
        switch (i1) {
            case 1:name = "2021年国考行测真题(副省级)";year = "20211";
            case 2:name = "2021年国考行测真题(地市级)";year = "20212";
            case 3:name = "2020年国考行测真题(副省级)";year = "20201";
            case 4:name = "2020年国考行测真题(地市级)";year = "20202";
            case 5:name = "2019年国考行测真题(副省级)";year = "20191";
            case 6:name = "2019年国考行测真题(地市级)";year = "20192";
            case 7:name = "2018年国考行测真题(副省级)";year = "20181";
            case 8:name = "2018年国考行测真题(地市级)";year = "20182";
        }

        String destFilePath = "D:\\" + name + ".docx";
        File file = new File(destFilePath);
        FileWriter fileWriter = new FileWriter(file);
        long start = System.currentTimeMillis();
        // RequestBody 有三种构建方式:
        // 1.  FormBody.Builder，构建application/x-www-form-urlencoded类型的post表单
        // 2.  MultipartBody.Builder，构建multipart类型的body，多个内容合成到一个body里
        // 3.  RequestBody.create方法构建body体，传入两个参数：类型和内容
        log.info("创建连接...");
        RequestBody body = new FormBody.Builder()
                .add("name", year)
                .add("t", "xc")
                .build();

        Request request = new Request.Builder()
                .url(host)
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        Response execute = call.execute();
        log.info("正在获取 《" + name + "》...");
        if (execute.body() != null) {
            String result = execute.body().string();
            List<Topic> topics = JSONArray.parseArray(result, Topic.class);
            log.info("正在解析...");
            topics.forEach(topic -> {
                String text = "";

                String question = topic.getQuestion();
                String answer = topic.getAnswer();
                JSONObject object = JSON.parseObject(answer);

                StringBuilder title = new StringBuilder();
                Collection<Object> values = object.values();
                ArrayList<Object> objects = new ArrayList<>(values);

                for (int i = 0; i < objects.size(); i++) {
                    String s = objects.get(i).toString();
                    String title1 = JSON.parseObject(s).getString("title");
                    title.append(title1).append("\n");
                }

                String sel = topic.getSel();
                String jiexi = topic.getJiexi();

                text = question
                        + "\n"
                        + title
                        + "\n"
                        + "答案：" + sel
                        + "\n"
                        + "【解析 ：" + jiexi + "】"
                        + "\n"
                        + "\n";

                String replace = text.replace("<br>", "");
                String replace2 = replace.replace("<br/>", "");
                try {
                    fileWriter.write(replace2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else {
            log.error("response 为空");
        }
        fileWriter.flush();
        fileWriter.close();
        long end = System.currentTimeMillis();
        log.info("写入完成! 耗时：" + (end - start)/1000 + "秒" + "     位置：" + destFilePath);
    }

    @Test
    public void post2() throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), "{}");

        Request request = new Request.Builder()
                .url(host + "/rest/n/testApi/path")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        call.execute();
    }

//    @Test
//    public void multipart() throws IOException {
//        InputStream inputStream = new FileInputStream("src/test/resources/mapping.xlsx");
//        byte[] bs = inputStream.readAllBytes();
//
//        // 随便读一个excel文件，当做二进制流处理呗
//        RequestBody part1 = RequestBody.create(bs, MediaType.parse("application/octet-stream"));
//
//        // 通过构造方法指定boundary，一般不需要，只是知道有这么个用法
//        RequestBody multipart = new MultipartBody.Builder("MyBoundary")
//                .setType(MultipartBody.MIXED)
//                .addFormDataPart("m1", "v1")
//                .addFormDataPart("m2", "v2", part1)
//                .build();
//
//        Request request = new Request.Builder()
//                .url(host + "/rest/n/testApi/path")
//                .post(multipart)
//                .build();
//
//        Call call = okHttpClient.newCall(request);
//        call.execute();
//    }

    @Test
    public void get1() {
        // get没有body
        Request request = new Request.Builder()
                .url(host + "/rest/n/testApi/hello")
                .get()
                .build();

        Call call = okHttpClient.newCall(request);

        // sync
        try {
            Response response = call.execute();
            // process response
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void get2() {
        Request request = new Request.Builder()
                .url(host + "/rest/n/testApi/hello")
                .get()
                .build();

        Call call = okHttpClient.newCall(request);

        // async
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                // process error
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                // process response
            }
        });

        // wait async result
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
