package com.ems.assessment.employee.service;

import com.ems.assessment.employee.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IEmployee {
    public void saveEmp(Employee emp);

    public List<Employee> getAllEmp();

    public Employee getEmpById(int id);

    public boolean deleteEmp(int id);

}
