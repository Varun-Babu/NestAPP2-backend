package com.example.nest2.dao;

import com.example.nest2.model.EmployeeModel;
import com.example.nest2.model.SecurityModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeDao extends CrudRepository<EmployeeModel, Integer> {

    @Query(value = "SELECT `id`, `emp_designation`, `emp_email`, `emp_name`, `emp_phone`, `password`, `username` FROM `employee` WHERE `emp_name`LIKE %:name%",nativeQuery = true)
    List<EmployeeModel> SearchEmployee(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM `employee` WHERE `id`=:id",nativeQuery = true)
    void deleteEmployee(@Param("id") Integer id);

    @Query(value = "SELECT `id`, `emp_designation`, `emp_email`, `emp_name`, `emp_phone`, `password`, `username` FROM `employee` WHERE `emp_email`=:email AND `password`=:password",nativeQuery = true)
    List<EmployeeModel> EmployeeLogin(@Param("email") String email, @Param("password") String password);

    @Query(value = "SELECT `id`, `empDesignation`, `empEmail`, `empName`, `password`, `empPhone`, `username` FROM `employee` WHERE `id`= :id", nativeQuery = true)
    List<EmployeeModel> GetEmployeeProfile(@Param("id") int id);

    @Query(value = "SELECT `id`, `empDesignation`, `empEmail`, `empName`, `password`, `empPhone`, `username` FROM `employee` WHERE `username`= :username AND `password`= :password AND `emp_code`= :empCode", nativeQuery = true)
    List<EmployeeModel> UserLoginCred(@Param("username") String username, @Param("password") String password,@Param("empCode") String empCode);

}
