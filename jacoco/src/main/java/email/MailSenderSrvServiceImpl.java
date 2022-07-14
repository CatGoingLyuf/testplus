package email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.internet.MimeMessage;
/**
 * @author rceng
 * @version 1.0.0
 * @ClassName MailSenderSrvServiceImpl.java
 * @createTime 2021年04月27日 09:40:00
 */
@Service("MailSenderSrvService")
public class MailSenderSrvServiceImpl implements MailSenderSrvService {

   @Autowired
    private JavaMailSenderImpl javaMailSender;

    public void setMailSender(JavaMailSenderImpl javaMailSender) {
     this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(String recipient, String subject, String content) {
       MimeMessage mimeMessage = javaMailSender.createMimeMessage();
       try {
        MimeMessageHelper messageHelper = new MimeMessageHelper((MimeMessage) mimeMessage,true,"UTF-8");
        messageHelper.setFrom("xxxx@qq.com");//发件人
        messageHelper.setTo(recipient);
        messageHelper.setSubject(subject);
        messageHelper.setText(content,true);//true代表支持html格式
        javaMailSender.send((MimeMessage) mimeMessage);
       } catch (MessagingException e) {
        e.printStackTrace();
       }
    }

    @Override
    public void sendHtmlEmail(String recipient, String subject, String content) throws MessagingException, Exception {
     MimeMessage mimeMessage = javaMailSender.createMimeMessage();
      try {
       MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
       messageHelper.setFrom("xxx@qq.com");//发件人
       messageHelper.setTo(recipient);
       messageHelper.setSubject(subject);
       messageHelper.setText(content,true);
       mimeMessage.setRecipients(Message.RecipientType.CC,"xxxx@qq.com");//抄送人
       javaMailSender.send(mimeMessage);
      } catch (MessagingException e) {
       e.printStackTrace();
      }
    }
}

