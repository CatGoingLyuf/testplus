package com.easyexcel;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author lyuf
 * @Date 2022/4/20 9:51
 * @Version 1.0
 */
public class ExcelTest {

    /**
     * 下载Excel模板

     * @param request request
     * @param response response
     * @throws IOException IOException
     */
    @RequestMapping(value = "/template/download", method = RequestMethod.POST)
    public void downloadTemplate (HttpServletRequest request, HttpServletResponse response) throws IOException {
        String filename = request.getParameter("filename");
        String path = request.getSession().getServletContext().getRealPath("/")+"static/excel/";
        FileUtil.download(filename,path,request,response);
    }

    /**
     * excel读数据量少于1千行数据，内部采用回调方法.
     * (经测试，大于1千行也是可以正常读取的)
     *
     * @param inputStream 输入流
     * @param sheetNo     工作表编号
     * @param headLineMun 标题占用行数
     */

    //  pom 版本：1.1.2-beta4
//    public static List<Object> simpleReadListString(InputStream inputStream, int sheetNo, int headLineMun) {
//        List<Object> data = null;
//        try {
//            data = EasyExcelFactory.read(inputStream, new Sheet(sheetNo, headLineMun));
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                inputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return data;
//    }

    /**
     * excel读数据量少于1千行数据自动转成javamodel，内部采用回调方法.
     * (经测试，大于1千行也是可以正常读取的)
     *
     * @param inputStream 输入流
     * @param sheetNo     工作表编号
     * @param headLineMun 标题占用行数
     */
   //  pom 版本：1.1.2-beta4
//    public static List<Object> simpleReadJavaModel(InputStream inputStream, int sheetNo, int headLineMun, Class<? extends BaseRowModel> clazz) {
//        List<Object> data = null;
//        try {
//            data = EasyExcelFactory.read(inputStream, new Sheet(sheetNo, headLineMun, clazz));
//            inputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                inputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return data;
//    }
}

