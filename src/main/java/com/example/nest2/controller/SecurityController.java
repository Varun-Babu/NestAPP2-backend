package com.example.nest2.controller;
import com.example.nest2.dao.SecurityDao;
import com.example.nest2.model.SecurityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class SecurityController {

    @Autowired
    private SecurityDao sdao;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addSecurity", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> AddSecurity(@RequestBody SecurityModel s){
        sdao.save(s);
        HashMap<String,String > map = new HashMap<>();
        map.put("status","success");
        return map;
    }
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/searchSecurity", consumes = "application/json", produces = "application/json")
    public List SearchSecurity(@RequestBody SecurityModel s){
        String name = s.getName();
        System.out.println(name);
        return (List<SecurityModel>)sdao.SearchSecurity(s.getName());
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/viewSecurity")
    public List<SecurityModel> ViewSecurity(){
        return (List<SecurityModel>) sdao.findAll();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path="/deleteSecurity",consumes = "application/json", produces = "application/json")
    public HashMap<String, String> DeleteEmployee(@RequestBody SecurityModel s) {
        String id = String.valueOf(s.getId());
        sdao.deleteSecurity(s.getId());
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "success");
        return map;
    }
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/securitylogin" ,produces = "application/json", consumes = "application/json")
    public HashMap<String,String > UserLogin(@RequestBody SecurityModel u) {
        List<SecurityModel> result = (List<SecurityModel>) sdao.SecurityLogin(u.getUsername(), u.getPassword());
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

}
