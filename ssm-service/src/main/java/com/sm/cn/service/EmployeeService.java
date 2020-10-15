package com.sm.cn.service;

import com.sm.cn.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    int addEmployee(Employee employee);

    Employee findById(long id);

    Employee findByEmail(String email);

    void updataEmployee(Employee byEmail);
}
