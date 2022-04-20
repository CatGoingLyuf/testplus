package com.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author xppll
 * @date 2021/12/3 10:05
 */
@Data
public class DemoData {
    //设置excel表头名称，index表示对应的第几列
    @ExcelProperty(value = "学生编号",index = 0)
    private Integer sno;

    @ExcelProperty(value = "学生姓名",index = 1)
    private String sname;
}
