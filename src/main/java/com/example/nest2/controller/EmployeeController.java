package com.example.nest2.controller;

import com.example.nest2.dao.EmployeeDao;
import com.example.nest2.model.EmployeeModel;
import com.example.nest2.model.SecurityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeDao edao;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> AddEmployee(@RequestBody EmployeeModel e){
        edao.save(e);
        e.getId();
        HashMap<String,String > map = new HashMap<>();
        map.put("status","success");
        return map;
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
            int id = result.get(0).getId();
            map.put("userId", String.valueOf(id));
            map.put("status", "success");
            map.put("message", "user login success");
        }
        return map;
    }

    @PostMapping(path = "/employeeProfile", consumes = "application/json", produces = "application/json")
    public List<EmployeeModel> getEmployeeProfile(@RequestBody EmployeeModel e){
        return (List<EmployeeModel>) edao.GetEmployeeProfile(e.getId());
    }






}
