package com.ems.assessment.employee.service;

import com.ems.assessment.employee.entity.Employee;
import com.ems.assessment.employee.repository.EmployeeRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployee{

    private static final Log log = LogFactory.getLog(EmployeeServiceImpl.class);
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void saveEmp(Employee emp) {
        try {
            Employee newEmp = employeeRepository.save(emp);
        }
        catch (Exception e){
            log.error("Employee could not be added " + e.getMessage());
        }
    }

    @Override
    public List<Employee> getAllEmp() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmpById(int id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public boolean deleteEmp(int id) {
        Employee emp = employeeRepository.findById(id).get();
        if (emp != null) {
            employeeRepository.delete(emp);
            return true;
        }
        return false;
    }
}
