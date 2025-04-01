package com.example.demo.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
// import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
@Service
public class UserService {
    
    @Autowired
    UserRepository empRepo;

    public User addUser(User emp)
    {
        return empRepo.save(emp);
    }

    public List<User> getAllUsers()
    {
        return empRepo.findAll();
    }

    public User updateUser(long id,User emp)
    {
        emp.setId(id);
        return empRepo.save(emp);
    }

    public String deleteUser(Long id)
    {
       empRepo.deleteById(id);
       return "Success";
    }

    //pagination
    public Page<User> getUsersByPage(int page,int size)
    {
        Pageable pageable=PageRequest.of(page, size);
        return empRepo.findAll(pageable);
    }

    //sorting
    public List<User>sortUsers()
    {
       return empRepo.findAll(Sort.by(Sort.Direction.ASC, "username"));
    }

    //JPQL
    public List<User>getUsersByName(String name)
    {
        return empRepo.findByUsername(name);
}

    //JPA
    public List<User> getUsersByEmail(String email) {
        return empRepo.findByemail(email);
    }


}
