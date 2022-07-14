package http;

import lombok.Data;

/**
 * @Author lyuf
 * @Date 2022/7/13 16:22
 * @Version 1.0
 */
@Data
public class Topic {

    // 题目
    private String question;

    // 解析
    private String jiexi;

    // 选项
    private String answer;

    //
    private String img;

    // 类型
    private String type;

    // 答案
    private String sel;

    // 题号
    private String rowid;
}
