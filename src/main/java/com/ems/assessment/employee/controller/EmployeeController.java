package com.ems.assessment.employee.controller;

import com.ems.assessment.employee.entity.Employee;
import com.ems.assessment.employee.service.IEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private IEmployee empService;


    @GetMapping("/api/home")
    public String home(Model m) {
        List<Employee> employeeList = empService.getAllEmp();
        m.addAttribute("empList", employeeList);
        return "index";
    }

    @GetMapping("/api/employeeDetails")
    public String addEmployeeForm() {
        return "save_emp";
    }

    @GetMapping("/api/editEmployee/{id}")
    public String EditEmployee(@PathVariable int id, Model m) {
        Employee employee = empService.getEmpById(id);
        m.addAttribute("emp", employee);
        return "edit_emp";
    }

    @PostMapping("/api/addEmployee")
    public String addEmployee(@ModelAttribute Employee employee) {
        empService.saveEmp(employee);
        return "redirect:/api/home";
    }

    @PostMapping("/api/updateEmployee")
    public String updateEmployee(@ModelAttribute Employee employee) {
        empService.saveEmp(employee);
        return "redirect:/api/home";
    }

    @GetMapping("/api/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable int id) {
        empService.deleteEmp(id);
        return "redirect:/api/home";
    }

}
