package com.sh;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ExcuteLinux {

    public static String exeCmd(String commandStr) {

        String result = null;
        try {
            String[] cmd = new String[]{"/bin/sh", "-c", commandStr};
            Process ps = Runtime.getRuntime().exec(cmd);

            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                //执行结果加上回车
                sb.append(line).append("\n");
            }
            result = sb.toString();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }


    public static void main(String[] args) {
        String result = ExcuteLinux.exeCmd("ifconfig");
        System.out.println("获取的结果是" + "\n" + result);


    }
}