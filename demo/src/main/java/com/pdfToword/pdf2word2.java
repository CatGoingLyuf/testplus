package com.pdfToword;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;

/**
 * @Author lyuf
 * @Date 2022/7/1 15:24
 * @Version 1.0
 */
public class pdf2word2 {
    public static void main(String[] args) {
        String pdfPath = "D:\\云宽带用户下挂终端能力接口规范.pdf";
        PDDocument doc = null;
        OutputStream fos = null;
        Writer writer = null;
        PDFTextStripper stripper = null;
        try {
            doc = PDDocument.load(new File(pdfPath));
            fos = new FileOutputStream(pdfPath.substring(0, pdfPath.indexOf(".")) + ".docx");
            writer = new OutputStreamWriter(fos, "UTF-8");
            stripper = new PDFTextStripper();
            int pageNumber = doc.getNumberOfPages();
            // 设置是否排序
            stripper.setSortByPosition(true);
            // 设置起始页
            stripper.setStartPage(1);
            // 设置结束页
            stripper.setEndPage(pageNumber);

            stripper.setAddMoreFormatting(true);
            stripper.writeText(doc, writer);
            writer.close();
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("pdf转换解析结束");

    }
}
