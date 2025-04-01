package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{
      @Query("SELECT u from User u where u.name LIKE %:name%")
    List<User>findByUsername(@Param("name")String name);

      //JPA
      List<User> findByemail(String email);




    
    List<User> findByName(String name);
    
    List<User> findByEmail(String email);
    
    boolean existsByName(String name);
}

