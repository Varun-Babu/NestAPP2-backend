package com.example.nest2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.web.bind.annotation.RestController;

@Entity
@Table(name = "employee")
public class EmployeeModel {

    @Id
    @GeneratedValue
    private int id;
    private String empName;
    private String empEmail;
    private String empDesignation;
    private String empPhone;
    private String username;
    private String password;

    public EmployeeModel() {
    }

    public EmployeeModel(int id, String empName, String empEmail, String empDesignation, String empPhone, String username, String password) {
        this.id = id;
        this.empName = empName;
        this.empEmail = empEmail;
        this.empDesignation = empDesignation;
        this.empPhone = empPhone;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getEmpDesignation() {
        return empDesignation;
    }

    public void setEmpDesignation(String empDesignation) {
        this.empDesignation = empDesignation;
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
