package com.example.nest2.dao;

import com.example.nest2.model.LeaveCheckModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface LeaveCheckDao extends CrudRepository<LeaveCheckModel, Integer> {

    @Query(value = "SELECT `id`, `casual_leave`, `emp_id`, `sick_leave`, `special_leave`, `year` FROM `leavecounter` WHERE `emp_id`= :id", nativeQuery = true)
    List<LeaveCheckModel> GetLeaveDetails(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE `leavecounter` SET `casual_leave`= :casualLeave,`sick_leave`= :sickLeave,`special_leave`= :specialLeave WHERE `emp_id`= :id", nativeQuery = true)
    void UpdateLeave(@Param("id") Integer id, @Param("casualLeave") Integer casualLeave, @Param("sickLeave") Integer sickLeave, @Param("specialLeave") Integer specialLeave);

    @Query(value = "SELECT `id`, `apply_date`, `emp_id`, `from_date`, `leave_status`, `leave_type`, `remarks`, `to_date` FROM `leavetotal` WHERE `emp_id`= :empId  AND :date BETWEEN `from_date` AND `to_date` AND `leave_status`=1", nativeQuery = true)
    List<Map<String,String>> GetLeaveUpdates(@Param("empId") Integer empId, @Param("date") String date);
}


