package email;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;


/**
 * @author rceng
 * @version 1.0.0
 * @ClassName TestMail.java
 * @createTime 2021年04月27日 09:51:00
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMail2 {

    private static String from = "993805524@qq.com";

    @Test
    public void sendStringEmail() throws IllegalAccessException, InstantiationException {
        // 测试文本邮件发送（无附件）
        String to = "1606304810@qq.com";
        String title = "文本邮件发送测试";
        String content = "文本邮件发送测试";
        EmailUtil emailUtil = EmailUtil.class.newInstance();
        emailUtil.sendMessage(to, title, content,from);
    }


    @Test
    public void sendFileEmail() throws IllegalAccessException, InstantiationException {
        // 测试单个附件邮件发送
        String to = "weiyushan@aliyun.com";
        String title = "单个附件邮件发送测试";
        String content = "单个附件邮件发送测试";
        File file = new File("D:\\2021年国考行测真题(地市级).docx");
        EmailUtil emailUtil = EmailUtil.class.newInstance();
        emailUtil.sendMessageCarryFile(to, title, content, file, from);
    }

//    @Test
//    void sendFilesEmail() {
//        // 测试多个附件邮件发送
//        String to = "1354720990@qq.com";
//        String title = "多个附件邮件发送测试";
//        String content = "多个附件邮件发送测试";
//        File[] files = new File[2];
//        files[0] = new File("C:\\Users\\root\\Desktop\\配置邮箱\\1.png");
//        files[1] = new File("C:\\Users\\root\\Desktop\\配置邮箱\\2.png");
//        emailUtil.sendMessageCarryFile(to, title, content, files);
//    }
}


