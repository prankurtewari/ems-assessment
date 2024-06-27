package com.ems.assessment.employee.controller;

import com.ems.assessment.employee.entity.Employee;
import com.ems.assessment.employee.model.EmployeeDTO;
import com.ems.assessment.employee.service.IEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private IEmployee empService;


    @GetMapping("/")
    public String index(Model m) {
        List<Employee> list = empService.getAllEmp();
        m.addAttribute("empList", list);
        return "index";
    }

    @GetMapping("/loadEmpSave")
    public String loadEmpSave() {
        return "emp_save";
    }

    @GetMapping("/EditEmp/{id}")
    public String EditEmp(@PathVariable int id, Model m) {
        // System.out.println(id);
        Employee emp = empService.getEmpById(id);
        m.addAttribute("emp", emp);
        return "edit_emp";
    }

    @PostMapping("/saveEmp")
    public String saveEmp(@ModelAttribute Employee emp) {
        // System.out.println(emp);


        Employee newEmp = empService.saveEmp(emp);
        return "redirect:/loadEmpSave";



    }

    @PostMapping("/updateEmpDtls")
    public String updateEmp(@ModelAttribute Employee emp, HttpSession session) {
        // System.out.println(emp);

        Employee updateEmp = empService.saveEmp(emp);

        if (updateEmp != null) {
            // System.out.println("save success");
            session.setAttribute("msg", "Update sucessfully");
        } else {
            // System.out.println("something wrong on server");
            session.setAttribute("msg", "something wrong on server");
        }

        return "redirect:/";
    }

    @GetMapping("/deleteEmp/{id}")
    public String loadEmpSave(@PathVariable int id, HttpSession session) {
        boolean f = empService.deleteEmp(id);
        if (f) {
            session.setAttribute("msg", "Delete sucessfully");
        } else {
            session.setAttribute("msg", "something wrong on server");
        }
        return "redirect:/";
    }

}
