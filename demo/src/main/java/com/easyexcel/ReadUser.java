package com.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

@Data
public class ReadUser extends BaseRowModel {
    @ExcelProperty(value = "姓名", index = 0)
    private String name;

    @ExcelProperty(value = "昵称", index = 1)
    private String nickName;

    @ExcelProperty(value = "密码", index = 2)
    private String password;

    @ExcelProperty(value = "生日", index = 3, format = "yyyy/MM/dd")
    private Date birthday;
}