package com.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author lyuf
 * @Date 2021/12/21 11:03
 * @Version 1.0
 */
public class zz {
    public String replaceNum(String content) {
        content = "DELETE /rest/pm/task/105";
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            String replace = content.replace(matcher.group(0), "{id}");
            return replace;
        }
        return content;
    }
}
