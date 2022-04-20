package com.easyexcel;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.springframework.util.Assert;

import java.io.*;
import java.util.List;

/**
 * Excel工具类
 *
 * @author lurunze
 * @date 2019/04/22
 */
public class ExcelUtil {

    /**
     * 获取Excel文件类型
     *
     * @param fileType 文件后缀
     * @return ExcelTypeEnum
     */
    public static ExcelTypeEnum getExcelTypeEnum(String fileType) {
        Assert.isTrue(ExcelTypeEnum.XLS.getValue().equals(fileType) || ExcelTypeEnum.XLSX.getValue().equals(fileType)
                , "文件格式不正确，请选择.xlxs或.xls格式文件！");
        return ExcelTypeEnum.valueOf(fileType);
    }

    /**
     * 读取Excel文件数据
     *
     * @param inputStream 文件输入流
     * @return Excel文件数据
     */
    public static List<List<String>> readExcel(InputStream inputStream, ExcelTypeEnum excelTypeEnum) {
        List<List<String>> datas;
        try {
            ExcelListener listener = new ExcelListener();
            ExcelReader excelReader = new ExcelReader(inputStream, excelTypeEnum, null, listener);
            excelReader.read();
            datas = listener.getDatas();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return datas;
    }

    /**
     * 写Excel文件
     *
     * @param excelTypeEnum excel版本
     * @param fileName      包含文件路径的文件名
     * @param sheetName     sheet名
     * @param datas         文件数据（包含表头）
     */
    public static void writeExcel(ExcelTypeEnum excelTypeEnum, String fileName, String sheetName, 
            List<List<String>> datas) throws FileNotFoundException {
        OutputStream out = new FileOutputStream(fileName);
        try {
            ExcelWriter writer = new ExcelWriter(out, excelTypeEnum, false);
            //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
            Sheet sheet1 = new Sheet(1, 0);
            sheet1.setSheetName(sheetName);
            writer.write0(datas, sheet1);
            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}