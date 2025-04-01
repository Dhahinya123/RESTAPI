package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity

public class Organiser {
    @Id
      Long id;
      String name;
      String events;
    public String getEvents() {
        return events;
    }
    public void setEvents(String events) {
        this.events = events;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
