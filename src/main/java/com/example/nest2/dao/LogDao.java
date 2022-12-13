package com.example.nest2.dao;

import com.example.nest2.model.LogModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface LogDao extends CrudRepository<LogModel,Integer> {

    @Modifying
    @Query(value = "UPDATE `logdetails` SET`check_out`=:check_out,`out_date`=:out_date WHERE `id`=:id",nativeQuery = true)
    void logOutStatus(@Param("check_out") Integer check_out,@Param("out_date") String out_date,@Param("id") Integer id);


    @Query(value = "SELECT l.`id`, l.`check_in`, l.`emp_id`, l.`in_date`, l.`out_date`,e.emp_name FROM `logdetails` l JOIN employee e ON l.`emp_id`=e.id WHERE l.`emp_id`=:emp_id",nativeQuery = true)
    List<Map<String,String>> viewlogByEmpid(@Param("emp_id") Integer emp_id);

    @Query(value = "SELECT l.`id`, l.`check_in`, l.`emp_id`, l.`in_date`, l.`out_date`,e.emp_name,e.emp_designation FROM `logdetails` l JOIN employee e ON l.emp_id=e.id",nativeQuery = true)
    List<Map<String,String>> viewAllLog();

    @Query(value = "SELECT l.`id`, l.`check_in`, l.`emp_id`, l.`in_date`, l.`out_date`,e.emp_name,e.emp_designation FROM `logdetails` l JOIN employee e ON l.emp_id=e.id WHERE `check_out`=0",nativeQuery = true)
    List<Map<String,String>> viewlogByStatus();

}
