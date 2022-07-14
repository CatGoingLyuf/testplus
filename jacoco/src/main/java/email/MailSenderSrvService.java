package email;

import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;

/**
 * @author rceng
 * @version 1.0.0
 * @ClassName MailSenderSrvServices.java
 * @createTime 2021年04月27日 09:38:00
 * 邮箱接口
 */
public interface MailSenderSrvService {
    /*
     * 普通格式发送
     * @recipient 收件人地址
     * @subject 主题
     * @content 正文
     */
     @Transactional
    void sendEmail(String recipient,String subject,String content);

    /*带抄送
     * */
    void sendHtmlEmail(String recipient,String subject,String content) throws MessagingException, Exception;
}
