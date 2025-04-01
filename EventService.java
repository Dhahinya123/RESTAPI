package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Event;
import com.example.demo.repository.EventRepository;

@Service
public class EventService {
      
    @Autowired
    EventRepository er;

    public Event addeve(Event emp)
    {
        return er.save(emp);
    }

    public List<Event> geteve()
    {
        return er.findAll();
    }

    public Event updateeve(long id,Event emp)
    {
        emp.setId(id);
        return er.save(emp);
    }

    public String deleteeve(Long id)
    {
       er.deleteById(id);
       return "Success";
    }

    //pagination
    public Page<Event> getUserByPage(int page,int size)
    {
        PageRequest pageable=PageRequest.of(page, size);
        return er.findAll(pageable);
    }

    //sorting
    public List<Event>sortByUser()
    {
       return er.findAll(Sort.by(Sort.Direction.ASC, "date"));
    }

    //JPQL
    public List<Event>getByjpql(LocalDate date)
    {
        return er.findByEventname(date);
}

//JPA
public List<Event> getJobsBydate(LocalDate date) {
        return er.findBydate(date);
    } 


    
}
