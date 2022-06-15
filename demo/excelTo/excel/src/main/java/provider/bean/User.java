package provider.bean;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class User {

    @ExcelProperty(value = "姓名", index = 0)
    private String name;

    @ExcelProperty(value = "性别", index = 1)
    private String sex;

    @ExcelProperty(value = "年龄", index = 2)
    private Integer age;

    @ExcelProperty(value = "身份证", index = 4)
    private String cardid;

    @ExcelProperty({"普通高等学校全日制教育", "学历"})
    private String kultur;

    @ExcelProperty({"普通高等学校全日制教育", "学位"})
    private String degree;

    @ExcelProperty({"普通高等学校全日制教育", "专业"})
    private String major;

    @ExcelProperty({"普通高等学校全日制教育", "获得学历时间"})
    private String graduatetime;

    @ExcelProperty({"普通高等学校全日制教育", "毕业院校"})
    private String school;

    /**
     * 如果对象里面有些字段我们并不想导出到Excel中，只要使用@ExcelIgnore注解就可以了
     */
    @ExcelIgnore
    private String aaaa;
}