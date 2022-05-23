package provider.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import org.junit.Test;
import provider.bean.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelWriteTest {

   @Test
   public void test() {
      // 生成Excel路径
      String fileName = "C:\\Users\\lyuf\\Desktop\\模板.xlsx";
      // head()用来放表头数据，dataList()用来放每一行的数据。
      EasyExcel.write(fileName).head(head()).sheet("模板").doWrite(dataList());
      // 设置自动列宽
      EasyExcel.write(fileName).head(head()).registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
              .sheet("模板").doWrite(dataList());
      // 根据对象写入
      EasyExcel.write(fileName, User.class).sheet("模板").doWrite(data());
   }

   @Test
   public void test2(){
      // 模板文件添加数据
      String templateName = "C:\\Users\\lyuf\\Desktop\\模板.xlsx";
      String fileName = "C:\\Users\\lyuf\\Desktop\\测试.xlsx";
      // 使用withTemplate(templateName)方法传入模板路径就可以了
      // 有个地方需要注意的是：这里的write方法只传文件路径，不传对象，如果传了对象又会生成新的表头，效果图如下：
      EasyExcel.write(fileName).withTemplate(templateName).sheet("模板").doWrite(data());
   }

   private List<List<String>> head() {
      List<List<String>> list = new ArrayList<>();
      List<String> head0 = new ArrayList<>();
      head0.add("姓名");
      List<String> head1 = new ArrayList<>();
      head1.add("年龄");
      List<String> head2 = new ArrayList<>();
      head2.add("生日");
      list.add(head0);
      list.add(head1);
      list.add(head2);
      return list;
   }

   private List<List<Object>> dataList() {
      List<List<Object>> list = new ArrayList<>();
      for (int i = 0; i < 10; i++) {
         List<Object> data = new ArrayList<>();
         data.add("张三");
         data.add(25);
         data.add(new Date());
         list.add(data);
      }
      return list;
   }
   private List<User> data() {
      List<User> userList = new ArrayList<>();
      User user;
      for (int i = 1; i <= 10; i++) {
         user = new User();
         user.setName("张三" + i);
         user.setSex("男");
         user.setAge(i);
         user.setCardid("440582xxxx");
         userList.add(user);
      }
      return userList;
   }


}