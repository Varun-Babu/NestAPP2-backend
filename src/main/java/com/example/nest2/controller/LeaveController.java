package com.example.nest2.controller;

import com.example.nest2.dao.LeaveCheckDao;
import com.example.nest2.dao.LeaveDao;
import com.example.nest2.model.EmployeeModel;
import com.example.nest2.model.LeaveCheckModel;
import com.example.nest2.model.LeaveModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LeaveController {

    @Autowired
    private LeaveDao dao;

    @Autowired
    private LeaveCheckDao ldao;
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addleave",consumes = "application/json",produces = "application/json")
    public HashMap<String,String> addLeave(@RequestBody LeaveModel l){
        DateTimeFormatter dt=DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm:ss");
        LocalDateTime now=LocalDateTime.now();
        String currentdate=String.valueOf((dt.format(now)));
        l.setApplyDate(currentdate);
        System.out.println(l.getApplyDate());
        System.out.println(l.getId());
        dao.save(l);
        HashMap<String,String> map =new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/viewAllLeaves")
    public List<Map<String ,String>> viewallleaves(){
        return (List<Map<String, String>>) dao.viewAllLeaves();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/viewLeavesById",consumes = "application/json",produces = "application/json")
    public  List<Map<String,String>> viewLeavesById(@RequestBody LeaveModel lm){
        return (List<Map<String, String>>) dao.viewLeaveById(lm.getId());
    }

    @Transactional
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/updatestatus",consumes = "application/json",produces = "application/json")
    public HashMap<String, String> updateStatus(@RequestBody LeaveModel l){
        dao.updateById(l.getStatus(),l.getId());
        HashMap<String,String> map =new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/updateLeaves", produces = "application/json", consumes = "application/json")
    public HashMap<String, String> UpdateLeaves(@RequestBody LeaveModel lv){
        ldao.GetLeaveUpdates(lv.getId(), String.valueOf(lv.getStatus()));
        String leaveType = lv.getType();
        LocalDate dateBefore = LocalDate.parse(lv.getApplyDate());
        LocalDate dateAfter = LocalDate.parse(lv.getLeaveDate());
        int daysOfLeave = (int) ChronoUnit.DAYS.between(dateBefore, dateAfter)+1;
        List<LeaveCheckModel> l1 = (List<LeaveCheckModel>) ldao.GetLeaveDetails(lv.getEmp_id());
        System.out.println(l1.get(0).getCasualLeave()-daysOfLeave);
        if(leaveType.equals("casualLeave")){
            ldao.UpdateLeave(lv.getEmp_id(),(l1.get(0).getCasualLeave()-daysOfLeave),l1.get(0).getSickLeave(),l1.get(0).getSpecialLeave());
        } else if (leaveType.equals("sickLeave")) {
            ldao.UpdateLeave(lv.getEmp_id(),l1.get(0).getCasualLeave(),(l1.get(0).getSickLeave()-daysOfLeave),l1.get(0).getSpecialLeave());
        }else if (leaveType.equals("specialLeave")){
            l1.get(0).setSpecialLeave(l1.get(0).getSickLeave()-daysOfLeave);
            ldao.UpdateLeave(lv.getEmp_id(),l1.get(0).getCasualLeave(),l1.get(0).getSickLeave(),(l1.get(0).getSickLeave()-daysOfLeave));
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("status","success");
        return hashMap;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/getLeaveDetails", produces = "application/json", consumes = "application/json")
    public List<LeaveCheckModel> GetLeaveDetails(@RequestBody EmployeeModel e){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter ytf = DateTimeFormatter.ofPattern("yyyy");
        List<LeaveCheckModel> leave =  ldao.GetLeaveDetails(Integer.valueOf(e.getId()));
        if(leave.size()==0){
            LeaveCheckModel l1 = new LeaveCheckModel();
            l1.setEmpId(String.valueOf(e.getId()));
            l1.setYear(ytf.format(now));
            l1.setCasualLeave(20);
            l1.setSickLeave(7);
            l1.setSpecialLeave(3);
            ldao.save(l1);
            List<LeaveCheckModel> temp_leave =  ldao.GetLeaveDetails(Integer.valueOf(e.getId()));
            return temp_leave;
        }else{
            return leave;
        }
    }
}
