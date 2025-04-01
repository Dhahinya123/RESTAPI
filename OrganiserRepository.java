package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Organiser;

public interface OrganiserRepository extends JpaRepository<Organiser,Long> {
    @Query("SELECT u from Organiser u where u.name LIKE %:name%")
    List<Organiser>findByname(@Param("name")String name);
    
    
    //JPA
    List<Organiser> findByid(Long id);
    
}

