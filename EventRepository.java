package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Event;

public interface EventRepository extends JpaRepository<Event,Long>{
    @Query("SELECT u from Event u where u.date = :date")
    List<Event>findByEventname(@Param("date")LocalDate date);

    //JPA
    List<Event> findBydate(LocalDate date);

}
    
