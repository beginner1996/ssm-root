package com.sm.cn.service.impl;

import com.sm.cn.entity.Employee;
import com.sm.cn.entity.EmployeeExample;
import com.sm.cn.mapper.EmployeeMapper;
import com.sm.cn.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper  employeeMapper;

    @Override
    public List<Employee> findAll() {
        return employeeMapper.selectByExample(null);
    }

    @Override
    public int addEmployee(Employee employee) {
        return employeeMapper.insert(employee);
    }

    @Override
    public Employee findById(long id) {
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }

    @Override
    public Employee findByEmail(String email) {
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andEmployeeEmailEqualTo(email);
        List<Employee> employees = employeeMapper.selectByExample(employeeExample);
        if (employees.isEmpty()){
            return null;
        }
        return employees.get(0);
    }

    @Override
    public void updataEmployee(Employee byEmail) {
        employeeMapper.updateByPrimaryKey(byEmail);
    }
}
