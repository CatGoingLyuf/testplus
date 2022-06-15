package provider.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import provider.bean.SomeThing;
import provider.utils.ExcelListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelReadTest {

   @Test
   public void test() {
      // 读取Excel路径
      String readFileName = "C:\\Users\\lyuf\\Desktop\\新装宽带wifi渗透率0513-0519.xlsx";
      // 生成Excel路径
      String fileName = "C:\\Users\\lyuf\\Desktop\\aaaaaaaa.xlsx";
      ExcelListener excelListener = new ExcelListener();
      EasyExcel.read(readFileName, excelListener).sheet().doRead();
      // 表格头数据
      Map<String, Integer> importHeads = excelListener.getImportHeads();
      // 每一行数据
      List<JSONObject> dataList = excelListener.getDataList();
      List<SomeThing> demo = demo();
      // 处理数据
      demo3(dataList,demo);

      demo.forEach(someThing -> {
         if (someThing.getB() != null && someThing.getC() != null && someThing.getD() != null) {
            someThing.setE(someThing.getC() + someThing.getD());
            Long e = Long.valueOf(someThing.getE());
            Long b = Long.valueOf(someThing.getB());
            long l = Math.floorDiv(e * 100, b);
            someThing.setF(l + "%");
         }
      });
      // 根据对象写入
      EasyExcel.write(fileName, SomeThing.class).registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).sheet("模板").doWrite(demo);



   }


   // 创建对象
   public List<SomeThing> demo() {
      ArrayList<SomeThing> someThings = new ArrayList<>();
      SomeThing hangz = new SomeThing();
      hangz.setA("杭州");
      someThings.add(hangz);
      SomeThing huz = new SomeThing();
      huz.setA("湖州");
      someThings.add(huz);
      SomeThing jiax = new SomeThing();
      jiax.setA("嘉兴");
      someThings.add(jiax);
      SomeThing jinh = new SomeThing();
      jinh.setA("金华");
      someThings.add(jinh);
      SomeThing lis = new SomeThing();
      lis.setA("丽水");
      someThings.add(lis);
      SomeThing ningb = new SomeThing();
      ningb.setA("宁波");
      someThings.add(ningb);
      SomeThing xuz = new SomeThing();
      xuz.setA("衢州");
      someThings.add(xuz);
      SomeThing shaox = new SomeThing();
      shaox.setA("绍兴");
      someThings.add(shaox);
      SomeThing taiz = new SomeThing();
      taiz.setA("台州");
      someThings.add(taiz);
      SomeThing wenz = new SomeThing();
      wenz.setA("温州");
      someThings.add(wenz);
      SomeThing zhous = new SomeThing();
      zhous.setA("舟山");
      someThings.add(zhous);
      // 类型
      SomeThing aaaa = new SomeThing();
      aaaa.setA("  ");
      someThings.add(aaaa);

      SomeThing dkbn = new SomeThing();
      dkbn.setA("单宽包年");
      someThings.add(dkbn);
      SomeThing dkby = new SomeThing();
      dkby.setA("单宽包月");
      someThings.add(dkby);
      SomeThing ek = new SomeThing();
      ek.setA("二宽");
      someThings.add(ek);
      SomeThing fgtrh = new SomeThing();
      fgtrh.setA("非高套融合");
      someThings.add(fgtrh);
      SomeThing gt = new SomeThing();
      gt.setA("高套");
      someThings.add(gt);
      // 融合套餐
      SomeThing bbbb = new SomeThing();
      bbbb.setA("  ");
      someThings.add(bbbb);
      SomeThing cccc = new SomeThing();
      cccc.setA("融合套餐");
      someThings.add(cccc);


      return someThings;
   }

   public void demo2(SomeThing someThing,JSONObject object){
      if (someThing.getB() == null) {
         someThing.setB(0);
      }
      if (someThing.getC() == null) {
         someThing.setC(0);
      }
      if (someThing.getD() == null) {
         someThing.setD(0);
      }
      someThing.setB(someThing.getB() + Integer.parseInt(object.getString("10")));
      someThing.setC(someThing.getC() + Integer.parseInt(object.getString("5")));
      someThing.setD(someThing.getD() + Integer.parseInt(object.getString("6")));
   }

   public void demo3(List<JSONObject> dataList, List<SomeThing> demo) {
      for (JSONObject object : dataList) {
         if (object.getString("0") != null) {
            if (object.getString("0").equals("杭州")) {
               SomeThing someThing = demo.get(0);
               demo2(someThing,object);
            }
            if (object.getString("0").equals("湖州")) {
               SomeThing someThing = demo.get(1);
               demo2(someThing,object);
            }
            if (object.getString("0").equals("嘉兴")) {
               SomeThing someThing = demo.get(2);
               demo2(someThing,object);
            }
            if (object.getString("0").equals("金华")) {
               SomeThing someThing = demo.get(3);
               demo2(someThing,object);
            }
            if (object.getString("0").equals("丽水")) {
               SomeThing someThing = demo.get(4);
               demo2(someThing,object);
            }
            if (object.getString("0").equals("宁波")) {
               SomeThing someThing = demo.get(5);
               demo2(someThing,object);
            }
            if (object.getString("0").equals("衢州")) {
               SomeThing someThing = demo.get(6);
               demo2(someThing,object);
            }
            if (object.getString("0").equals("绍兴")) {
               SomeThing someThing = demo.get(7);
               demo2(someThing,object);
            }
            if (object.getString("0").equals("台州")) {
               SomeThing someThing = demo.get(8);
               demo2(someThing,object);
            }
            if (object.getString("0").equals("温州")) {
               SomeThing someThing = demo.get(9);
               demo2(someThing,object);
            }
            if (object.getString("0").equals("舟山")) {
               SomeThing someThing = demo.get(10);
               demo2(someThing,object);
            }
            if (object.getString("3").equals("单宽包年")) {
               SomeThing someThing = demo.get(12);
               demo2(someThing,object);
            }
            if (object.getString("3").equals("单宽包月")) {
               SomeThing someThing = demo.get(13);
               demo2(someThing,object);
            }
            if (object.getString("3").equals("二宽")) {
               SomeThing someThing = demo.get(14);
               demo2(someThing,object);
            }
            if (object.getString("3").equals("非高套融合")) {
               SomeThing someThing = demo.get(15);
               demo2(someThing,object);
            }
            if (object.getString("3").equals("高套")) {
               SomeThing someThing = demo.get(16);
               demo2(someThing,object);
            }
//            SomeThing someThing = demo.get(16);
//            demo2(someThing,object);
         }
      }
   }

}