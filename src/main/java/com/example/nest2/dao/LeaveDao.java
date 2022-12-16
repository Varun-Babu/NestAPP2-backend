package com.example.nest2.dao;

import com.example.nest2.model.LeaveModel;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface LeaveDao extends CrudRepository<LeaveModel,Integer> {

    @Query(value = "SELECT l.`id`, l.`apply_date`,l. `description`, l.`emp_id`, l.`leave_date`, l.`status`,e.emp_name,e.emp_designation `type` FROM `leavetotal` l JOIN employee e ON l.emp_id=e.id WHERE `status`=0",nativeQuery = true)
    List<Map<String,String>> viewAllLeaves();

    @Query(value = "SELECT l.`id`, l.`apply_date`, l.`description`, l.`emp_id`, l.`leave_date`, l.`status`, l.`type`,e.emp_name,e.emp_designation FROM `leavetotal` l JOIN employee e ON l.emp_id=e.id WHERE l.id=:emp_id",nativeQuery = true)
    List<Map<String,String>>viewLeaveById(@Param("emp_id") Integer emp_id);

    @Modifying
    @Query(value = "UPDATE `leavetotal` SET `status`=:status WHERE `id`=:id",nativeQuery = true)
    void updateById(@Param("status") Integer status,@Param("id") Integer id);


}
