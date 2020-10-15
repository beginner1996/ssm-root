package com.sm.cn.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sm.cn.common.http.entity.ResponseBean;
import com.sm.cn.entity.Employee;
import com.sm.cn.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@RestController
@RequestMapping("employee")
//@CrossOrigin解决跨域问题，实现前后端分离（局部解决方式）
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JavaMailSender javaMailSender;




    @GetMapping("findAll")
    public ResponseBean findAll(){
        return ResponseBean.success(employeeService.findAll());
    }
    @GetMapping("page")
    public ResponseBean findPage(int currentPage,int pageSize){
        PageHelper.startPage(currentPage,pageSize);
        List<Employee> all = employeeService.findAll();
        PageInfo<Employee> employeePageInfo = new PageInfo<>(all);
        long total = employeePageInfo.getTotal();
        return ResponseBean.success(all,total);
    }
    @PostMapping
    public ResponseBean addEmployee(@RequestBody Employee employee){
        employee.setEmployeePassword("123456");
        System.out.println(employee.getEmployeeEmail());
        int row = employeeService.addEmployee(employee);
        if (row>0){
            return ResponseBean.success();
        }else {
            return ResponseBean.error();
        }
    }
    @GetMapping("{id}")
    public ResponseBean findById(@PathVariable long id){
        Employee employee= employeeService.findById(id);
        return ResponseBean.success(employee);
    }

    @PutMapping
    public ResponseBean updataEmployee(@RequestBody Employee employee){
        employeeService.updataEmployee(employee);
        return ResponseBean.success();

    }


    /**
     * 发送激活邮件
     * @param email
     * @return
     * @throws MessagingException
     */
    @PutMapping("active")
    public ResponseBean active(String email) throws MessagingException {
        Employee byEmail = employeeService.findByEmail(email);
        if (byEmail != null && !byEmail.getActive()){
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setFrom("尚马教育<18439443515@163.com>");
            helper.setTo("1691791069@qq.com");
            helper.setSubject("邮箱激活");
            helper.setText("点击链接激活邮箱<a href=http://localhost:8080/employee/active/"+byEmail.getEmployeeId()+">点我激活</a>",true);
            javaMailSender.send(mimeMessage);
        }
        return ResponseBean.success();
    }


    /**
     * 激活邮箱
     * @param id
     * @return
     */
    @GetMapping("active/{id}")
    public ResponseBean active(@PathVariable long  id){
        Employee byId = employeeService.findById(id);
        byId.setActive(true);
        employeeService.updataEmployee(byId);
        return ResponseBean.success();
    }

}
