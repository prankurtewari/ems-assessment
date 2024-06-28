package com.ems.assessment.employee.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String email;

    private String gender;

    private String department;
}
