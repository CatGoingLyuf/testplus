package com.easyexcel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.metadata.Table;
import org.junit.Test;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;

public class ExcelWriteTest {

   /**
    * 每行数据是List<String>无表头
    * 
    * @throws IOException
    */
   @Test
   public void writeWithoutHead() throws IOException {
      try (OutputStream out = new FileOutputStream("withoutHead2.xlsx");) {
         ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, false);
         Sheet sheet1 = new Sheet(1, 0);
         sheet1.setSheetName("sheet1");
         List<List<String>> data = new ArrayList<>();
         for (int i = 0; i < 100; i++) {
            List<String> item = new ArrayList<>();
            item.add("行标签" + i);
            item.add("新装宽带总数" + i);
            item.add("其中有一次性礼包" + i);
            item.add("其中有包月礼包" + i);
            item.add("WIFI合计" + i);
            item.add("渗透率" + i);
            data.add(item);
         }
         writer.write0(data, sheet1);
         writer.finish();
      }
   }

   /**
    * 每行数据是List<String>有表头
    *
    * @throws IOException
    */
   @Test
   public void writeHead() throws IOException {
      try (OutputStream out = new FileOutputStream("withHead.xlsx");) {
         ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
         Sheet sheet1 = new Sheet(1, 0);
         sheet1.setSheetName("sheet1");
         List<List<String>> data = new ArrayList<>();
         for (int i = 0; i < 100; i++) {
            List<String> item = new ArrayList<>();
            item.add("item0" + i);
            item.add("item1" + i);
            item.add("item2" + i);
            data.add(item);
         }
         List<List<String>> head = new ArrayList<List<String>>();
         List<String> headCoulumn1 = new ArrayList<String>();
         List<String> headCoulumn2 = new ArrayList<String>();
         List<String> headCoulumn3 = new ArrayList<String>();
         headCoulumn1.add("第一列");
         headCoulumn2.add("第二列");
         headCoulumn3.add("第三列");
         head.add(headCoulumn1);
         head.add(headCoulumn2);
         head.add(headCoulumn3);
         Table table = new Table(1);
         table.setHead(head);
         writer.write0(data, sheet1, table);
         writer.finish();
      }
   }
}