package com.example.nest2.controller;

import com.example.nest2.dao.EmployeeDao;
import com.example.nest2.dao.LeaveCheckDao;
import com.example.nest2.model.EmployeeModel;
import com.example.nest2.model.LeaveCheckModel;
import com.example.nest2.model.SecurityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeDao edao;
    @Autowired
    private LeaveCheckDao ldao;


    @CrossOrigin(origins = "*")
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> AddEmployee(@RequestBody EmployeeModel e){
        List<EmployeeModel> emp = (List<EmployeeModel>) edao.UserLoginCred(e.getUsername(), e.getPassword(),e.getId());
        HashMap<String, String> hashMap = new HashMap<>();
//        edao.save(e);
//        hashMap.put("status","success");
        if(emp.size()==0){
            LocalDateTime now = LocalDateTime.now();
            edao.save(e);
            List<EmployeeModel> result = (List<EmployeeModel>) edao.UserLogDetailsById(String.valueOf(e.getId()));
            LeaveCheckModel l1 = new LeaveCheckModel();
            l1.setEmpId(String.valueOf(result.get(0).getId()));
            DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy");
            l1.setYear(d.format(now));
            l1.setCasualLeave(20);
            l1.setSickLeave(7);
            l1.setSpecialLeave(3);
            ldao.save(l1);
            hashMap.put("status","success");
        }else{
            hashMap.put("status","failed");
        }
        return hashMap;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/search", consumes = "application/json", produces = "application/json")
    public List SearchEmployee(@RequestBody EmployeeModel e){
        String name = e.getEmpName();
        System.out.println(name);
        return (List<EmployeeModel>)edao.SearchEmployee(e.getEmpName());
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/view")
    public List<EmployeeModel> ViewEmployee(){
        return (List<EmployeeModel>) edao.findAll();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path="/delete",consumes = "application/json", produces = "application/json")
    public HashMap<String, String> DeleteEmployee(@RequestBody EmployeeModel e) {
        String id = String.valueOf(e.getId());
        edao.deleteEmployee(e.getId());
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/employeelogin" ,produces = "application/json", consumes = "application/json")
    public HashMap<String,String > EmployeeLogin(@RequestBody EmployeeModel u) {
        List<EmployeeModel> result = (List<EmployeeModel>) edao.EmployeeLogin(u.getEmpEmail(), u.getPassword());
        HashMap<String, String> map = new HashMap<>();
        if (result.size() == 0) {
            map.put("status", "failed");
            map.put("message", "user does not exist");
        } else {
//            int id = result.get(0).getId();
//            map.put("userId", String.valueOf(id));
            map.put("userInfo",String.valueOf(result.get(0).getId()));
            System.out.println(result.get(0).getId());
            map.put("status", "success");
            map.put("message", "user login success");
        }
        return map;
    }

    @PostMapping(path = "/employeeProfile", consumes = "application/json", produces = "application/json")
    public List<EmployeeModel> getEmployeeProfile(@RequestBody EmployeeModel e){
        return (List<EmployeeModel>) edao.GetEmployeeProfile(e.getId());
    }

//    @CrossOrigin(origins = "*")
//    @PostMapping(path = "/editEmployee", consumes = "application/json", produces = "application/json")
//    public HashMap<String, String> EditEmployee(@RequestBody EmployeeModel e){
//        System.out.println(e.getId());
//        edao.EditEmployee(e.getId(), e.getEmpDesignation(), e.getEmpEmail(), e.getEmpName(), e.getPassword(), e.getEmpPhone(), e.getUsername());
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("status","success");
//        return hashMap;
//    }








}
