package com.example.nest2.dao;

import com.example.nest2.model.EmployeeModel;
import com.example.nest2.model.SecurityModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SecurityDao extends CrudRepository<SecurityModel,Integer> {

    @Query(value = "SELECT `id`, `address`, `name`, `password`, `username` FROM `security` WHERE `name`LIKE %:name%",nativeQuery = true)
    List<SecurityModel> SearchSecurity(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM `security` WHERE `id`=:id",nativeQuery = true)
    void deleteSecurity(@Param("id") Integer id);

    @Query(value = "SELECT `id`, `address`, `name`, `password`, `username` FROM `security` WHERE `username` =:username AND `password`= :password",nativeQuery = true)
    List<SecurityModel> SecurityLogin(@Param("username") String username,@Param("password") String password);

    @Query(value = "SELECT `id`,`address`, `name`,`password`, `username` FROM `security` WHERE `id`= :id", nativeQuery = true)
    List<SecurityModel> GetSecurityProfile(@Param("id") int id);
}
