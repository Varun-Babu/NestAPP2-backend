package com.example.nest2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="leavetotal")
public class LeaveModel {

    @Id
    @GeneratedValue
    public int id;
    public int emp_id;
    public String type;
    public String description;
    public String applyDate;
    public String leaveDate;
    public int status;

    public LeaveModel() {
    }

    public LeaveModel(int id, int emp_id, String type, String description, String applyDate, String leaveDate, int status) {
        this.id = id;
        this.emp_id = emp_id;
        this.type = type;
        this.description = description;
        this.applyDate = applyDate;
        this.leaveDate = leaveDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
