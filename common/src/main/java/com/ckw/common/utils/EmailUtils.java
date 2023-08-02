package com.ckw.common.utils;


import com.ckw.common.pojo.ToEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author Echo
 */
@Component
public class EmailUtils {


    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private RedisUtils redisUtils;

    @Value("${spring.mail.username}")
    private String from;

    @Async
    public void sendVerCode(ToEmail email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email.getTos());
        message.setSubject(email.getSubject());
        String verCode = VerCodeGenerateUtil.gnerateVercode();
        message.setText("Coder,您好:\n"
                        + "\n本次请求的邮件验证码为:" + verCode + ",本验证码 5 分钟内效，请及时输入。（请勿泄露此验证码）\n"
                        + "\n如非本人操作，请忽略该邮件。\n(这是一封通过自动发送的邮件，请不要直接回复）");
        mailSender.send(message);
        redisUtils.insertObject(email.getTos()[0],verCode,60*5);
        System.out.println(redisUtils.getObject(email.getTos()[0]));
    }

}
