package com.ems.assessment.employee.controller;

import com.ems.assessment.employee.entity.Employee;
import com.ems.assessment.employee.service.IEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private IEmployee empService;

    @GetMapping("/")
    public String index() {
        return "redirect:/api/home";
    }

    @GetMapping("/api/home")
    public String home(Model m) {
        getAuthenitcationStatus(m);
        return "index";
    }


    @GetMapping("/api/employeeDetails")
    public String addEmployeeForm(Model m) {
        getAuthenitcationStatus(m);
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
    private void getAuthenitcationStatus(Model m) {
        boolean islogIn = false;
        if(SecurityContextHolder.getContext().getAuthentication() !=null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            islogIn = true;
        }
        List<Employee> employeeList = empService.getAllEmp();
        m.addAttribute("empList", employeeList);
        m.addAttribute("isLoggedIn", islogIn);
    }

}
