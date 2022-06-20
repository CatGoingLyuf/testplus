package com.jsoup;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * @Author lyuf
 * @Date 2022/6/20 15:41
 * @Version 1.0
 */
public class readAndWrite {

    public static void main(String[] args) {
        String s = readStringFromtxt("D:\\jsoup.txt");
    }
    // 读取
    public static String readStringFromtxt(String txtpath) {
        File file = new File(txtpath);
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while ((s = br.readLine()) != null) {
                result.append(System.lineSeparator() + s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    // 写入
    public static int writeStringToTxt(String targetTxt, String str) {
        File file = new File(targetTxt);
        BufferedWriter bwriter;
        try {
            bwriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            bwriter.write(str);
            bwriter.flush();
            bwriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    // pdf文件转换为base64
    public static String pdfFileToBase64String(String pdfpath) {
        File file = new File(pdfpath);
        FileInputStream fin = null;
        BufferedInputStream bin = null;
        ByteArrayOutputStream baos = null;
        BufferedOutputStream bout = null;
        try {
            fin = new FileInputStream(file);// 建立读取文件的文件输出流
            bin = new BufferedInputStream(fin);// 在文件输出流上安装节点流（更大效率读取）
            baos = new ByteArrayOutputStream();// 创建一个新的 byte 数组输出流，它具有指定大小的缓冲区容量
            bout = new BufferedOutputStream(baos);// 创建一个新的缓冲输出流，以将数据写入指定的底层输出流
            byte[] buffer = new byte[1024];
            int len = bin.read(buffer);
            while (len != -1) {
                bout.write(buffer, 0, len);
                len = bin.read(buffer);
            }
            // 刷新此输出流并强制写出所有缓冲的输出字节，必须这行代码，否则有可能有问题
            bout.flush();
            byte[] bytes = baos.toByteArray();
            // sun公司的API
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encodeBuffer(bytes).trim();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fin.close();
                bin.close();
                bout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    // base64编码转换为pdf文件
    public static int base64ToPdf(String base64String, String pdfpath) {
        BufferedInputStream bin = null;
        FileOutputStream fout = null;
        BufferedOutputStream bout = null;
        BASE64Decoder decoder=new BASE64Decoder();
        try {
            byte[] bytes = decoder.decodeBuffer(base64String);
            // 创建一个将bytes作为其缓冲区的ByteArrayInputStream对象
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            // 创建从底层输入流中读取数据的缓冲输入流对象
            bin = new BufferedInputStream(bais);
            // 指定输出的文件
            File file = new File(pdfpath);
            // 创建到指定文件的输出流
            fout = new FileOutputStream(file);
            // 为文件输出流对接缓冲输出流对象
            bout = new BufferedOutputStream(fout);

            byte[] buffers = new byte[1024];
            int len = bin.read(buffers);
            while (len != -1) {
                bout.write(buffers, 0, len);
                len = bin.read(buffers);
            }

            bout.flush();// 刷新此输出流并强制写出所有缓冲的输出字节，必须这行代码，否则有可能有问题

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bin.close();
                fout.close();
                bout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}
