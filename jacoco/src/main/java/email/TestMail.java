package email;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author rceng
 * @version 1.0.0
 * @ClassName TestMail.java
 * @createTime 2021年04月27日 09:51:00
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class TestMail {

    //private static MailSenderSrvService mailsend;
    @Autowired
    private MailSenderSrvService mailsend;


    @Test
    public void testmaila(){
        String to = "1204851038@qq.com";  //收件人地址
        String subject = "this　is　ｎｅｗ　ｅｍａｉｌ";   //邮件标题
        String content = "好好学习，天天向上";    //邮件内容
        mailsend.sendEmail(to,subject,content);    //发送邮件
    }


    public MailSenderSrvService getMailsend(){
        return mailsend;
    }

    public void setMailsend(MailSenderSrvService mailsend){
        this.mailsend = mailsend;
    }

}


