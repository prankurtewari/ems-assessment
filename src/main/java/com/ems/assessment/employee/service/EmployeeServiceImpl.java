package com.ems.assessment.employee.service;

import com.ems.assessment.employee.entity.Employee;
import com.ems.assessment.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployee{
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmp(Employee emp) {
        try {
            Employee newEmp = employeeRepository.save(emp);
            return newEmp;
        }
        catch (Exception e){
            System.out.println(e);
            return new Employee();
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
    public void removeSessionMessage() {
        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
                .getSession();

        session.removeAttribute("msg");

    }

}
