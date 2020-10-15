package com.sm.cn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

@RestController
public class EmailController {
    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("email")
    public String email(){
        SimpleMailMessage massage = new SimpleMailMessage();
        massage.setFrom("段海龙<18439443515@163.com>");
        massage.setTo("1691791069@qq.com");
        massage.setSubject("尚马邮件");
        massage.setText("尚马通知，国庆放假");
        javaMailSender.send(massage);
        return "OK";
    }

    @GetMapping("email2")
    public String email2() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        mimeMessageHelper.setFrom("清华大学<18439443515@163.com>");
        mimeMessageHelper.setTo("1691791069@qq.com");
        mimeMessageHelper.setSubject("录取通知书");
        mimeMessageHelper.setText("恭喜你被清华录取了");
        mimeMessageHelper.addAttachment("offer.jpg",new File("E:\\picture\\564.jpg"));
        javaMailSender.send(mimeMessage);
        return "OK";
    }

    @PostMapping("email3")
    public String email3(String to, String subject, String text,@RequestPart javax.servlet.http.Part part) throws MessagingException, IOException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        mimeMessageHelper.setFrom("北京大学<18439443515@163.com>");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text);
        InputStream inputStream = part.getInputStream();
        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        mimeMessageHelper.addAttachment(URLEncoder.encode(part.getSubmittedFileName(),"utf-8") ,new ByteArrayResource(buffer));
        javaMailSender.send(mimeMessage);
        return "OK";
    }

    @GetMapping("email4")
    public String email4() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        mimeMessageHelper.setFrom("北京大学<18439443515@163.com>");
        mimeMessageHelper.setTo("1691791069@qq.com");
        mimeMessageHelper.setSubject("发送页面标签");
        // use the true flag to indicate the text included is HTML
        mimeMessageHelper.setText("<html><body><img src='cid:huige'></body></html>",true);
//        mimeMessageHelper.setText("<html><body><img src='cid:identifier1234'></body></html>", true);
        // let's include the infamous windows Sample file (this time copied to c:/)
        FileSystemResource res = new FileSystemResource(new File("E:\\picture\\1100.jpg"));
        mimeMessageHelper.addInline("huige", res);
        javaMailSender.send(mimeMessage);
        return "OK";
    }
}
