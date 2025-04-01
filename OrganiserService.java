package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Organiser;
import com.example.demo.repository.OrganiserRepository;

@Service

public class OrganiserService {
    @Autowired 
    OrganiserRepository or;
    public Organiser addo(Organiser o)
    {
        return or.save(o);
    }
    
    public List<Organiser> geto()
    {
        return or.findAll();
    }

    public Organiser updateo(Long id,Organiser emp)
    {
        emp.setId(id);
        return or.save(emp);
    }

    public String deleteo(Long id)
    {
       or.deleteById(id);
       return "Success";
    }

    //pagination
    public Page<Organiser> getUserByPage(int page,int size)
    {
        PageRequest pageable=PageRequest.of(page, size);
        return or.findAll(pageable);
    }

    //sort
      public List<Organiser>sortByUser()
    {
       return or.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    //JPQL
    public List<Organiser>getByQuery(String name)
    {
        return or.findByname(name);
}

//JPA
public List<Organiser> getJobsByid(Long id) {
        return or.findByid(id);
    }
    
}
