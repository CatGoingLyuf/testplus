package provider.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author lyuf
 * @Date 2022/5/23 14:56
 * @Version 1.0
 */
@Data
public class SomeThing {

    @ExcelProperty(value = "行标签", index = 0)
    private String a;

    @ExcelProperty(value = "新装宽带总数", index = 1)
    private Integer b;

    @ExcelProperty(value = "其中有一次性礼包", index = 2)
    private Integer c;

    @ExcelProperty(value = "其中有包月礼包", index = 3)
    private Integer d;

    @ExcelProperty(value = "WIFI合计", index = 4)
    private Integer e;

    @ExcelProperty(value = "渗透率", index = 5)
    private String f;
}
