package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Organiser;
import com.example.demo.service.OrganiserService;


@RestController
public class OrganiserController {
     @Autowired
    OrganiserService os;

    @PostMapping("/orgadd")
    public Organiser insertEmployee(@RequestBody Organiser emp)
    {
        return os.addo(emp);
    }

    @GetMapping("/orgget")
    public List<Organiser> getAllEmployee()
    {
        return os.geto();
    }

    @PutMapping("/orgupdate/{id}")
    public Organiser updateEmployee(@PathVariable Long id, @RequestBody Organiser emp)
    {
        return os.updateo(id,emp);
    }
     
    @DeleteMapping("/orgdelete/{id}")
    public String deleteEmployee(@PathVariable Long id)
    {
        return os.deleteo(id);
    }

    //pagination
    @GetMapping("/pageorg")
    public Page<Organiser>getByPage(@RequestParam (defaultValue="0") int page,@RequestParam (defaultValue = "5") int size)
    {
        return os.getUserByPage(page, size);
    }

    //sorting
    @GetMapping("/sorting")
    public List<Organiser>sortByUser()
    {
        return os.sortByUser();
    }

    //JPQL
    @GetMapping("/q/{name}")
    public List<Organiser>getUsername(@PathVariable String name)
    {
        return os.getByQuery(name);
}

//JPA
@GetMapping("/id")
    public List<Organiser> getJobsByid(@RequestParam Long id) {
        return os.getJobsByid(id);
    }

    
}
