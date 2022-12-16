package com.example.nest2.controller;

import com.example.nest2.dao.LogDao;
import com.example.nest2.model.LogModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LogController {

    @Autowired
    private LogDao ldao;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addlog",consumes = "application/json",produces = "application/json")
    public HashMap<String, String> addLog(@RequestBody LogModel l){
        DateTimeFormatter dt=DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm:ss");
        LocalDateTime now=LocalDateTime.now();
        String currentdate=String.valueOf(dt.format(now));
        l.setInDate(currentdate);
        l.setCheckOut(0);
        ldao.save(l);
        HashMap<String,String> map =new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(origins = "*")
    @Transactional
    @PostMapping(path = "/logout",consumes = "application/json",produces = "application/json")
    public HashMap<String, String> logOutStatus(@RequestBody LogModel l){
        DateTimeFormatter dt=DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm:ss");
        LocalDateTime now=LocalDateTime.now();
        String currentdate=String.valueOf(dt.format(now));
        l.setOutDate(currentdate);
        ldao.logOutStatus(l.getCheckOut(),l.getOutDate(),l.getId());
        HashMap<String,String> map =new HashMap<>();
        map.put("status","success");
        return map;
}

    @CrossOrigin(origins = "*")
    @GetMapping("/viewCheckin")
    public List<Map<String,String>> viewAllcheckin(){

        return (List<Map<String, String>>) ldao.viewlogByStatus();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/viewAllLogs")
    public List<Map<String,String>> viewAllLog(){
        return (List<Map<String, String>>) ldao.viewAllLog();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/viewLogById",consumes = "application/json",produces = "application/json")
    public List<Map<String,String>> viewLogByEmp(@RequestBody LogModel l){
        return (List<Map<String, String>>) ldao.viewlogByEmpid(l.getEmpId());
    }
}
