package com.sm.cn.controller;

import com.sm.cn.common.http.entity.MyStatus;
import com.sm.cn.common.http.entity.ResponseBean;
import com.sm.cn.entity.Employee;
import com.sm.cn.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @GetMapping("getCode")
    public ResponseBean getCode(String email){
        Employee byEmail = employeeService.findByEmail(email);
        if (byEmail == null){
            return ResponseBean.error(MyStatus.EMAIL_NOT_REGISTER);
        }
       int num= (int) (Math.random()*(999999-100000 + 1)+100000);
        stringRedisTemplate.opsForValue().set(email,num+"");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("<18439443515@163.com>");
        message.setTo(email);
        message.setSubject("邮箱验证码");
        message.setText("你的邮箱验证码为：  " + num);
        javaMailSender.send(message);
        return ResponseBean.success();
    }

    @GetMapping("login")
    public ResponseBean login(String email,String code){
        String s = stringRedisTemplate.opsForValue().get(email);
        if (!StringUtils.isEmpty(code) && code.equals(s)){
            //验证码正确
            stringRedisTemplate.delete(email);
            Employee byEmail = employeeService.findByEmail(email);
            if (!byEmail.getActive()){
                return ResponseBean.error(MyStatus.NO_REGISTER);
            }
            return ResponseBean.success();
        }else {
            return ResponseBean.error(MyStatus.ERROR_CODE);
        }
    }
}
