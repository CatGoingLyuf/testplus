package com.example.iodemo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author lyuf
 * @Date 2021/7/12 17:01
 * @Version 1.0
 */
public class iodemo4 {
    public static void main(String[] args) throws IOException {

        File file = new File("D:/test/e.doc");
        FileWriter fileWriter = new FileWriter(file);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            stringBuilder.append("阿嘎撒大噶三国杀打发士大夫");
        }
        String string = stringBuilder.toString();
        fileWriter.write(string);
        fileWriter.flush();
        fileWriter.close();
    }
}
