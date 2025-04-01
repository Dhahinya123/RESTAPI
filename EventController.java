// package com.example.demo.controller;

// import java.time.LocalDate;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.demo.entity.Event;
// import com.example.demo.service.EventService;

// @RestController
// public class EventController {
//     @Autowired
//    EventService empService;

//     @PostMapping("/eveadd")
//     public Event  insertEmployee(@RequestBody Event  emp)
//     {
//         return empService.addeve(emp);
//     }

//     @GetMapping("/eveget")
//     public List<Event> getAllEmployee()
//     {
//         return empService.geteve();
//     }

//     @PutMapping("/eveupdate/{id}")
//     public Event  updateEmployee(@PathVariable Long id, @RequestBody Event  emp)
//     {
//         return empService.updateeve(id,emp);
//     }
     
//     @DeleteMapping("/evedelete/{id}")
//     public String deleteEmployee(@PathVariable Long id)
//     {
//         return empService.deleteeve(id);
//     }
    
//     //pagination
//     @GetMapping("/pageeve")
//     public Page<Event>getByPage(@RequestParam (defaultValue="0") int page,@RequestParam (defaultValue = "5") int size)
//     {
//         return empService.getUserByPage(page, size);
//     }

//     //sorting
//     @GetMapping("/sorteve")
//     public List<Event>sortByUser()
//     {
//         return empService.sortByUser();
// }

//     //JPQL
//     @GetMapping("/event/{date}")
//     public List<Event>getUsername(@PathVariable LocalDate date)
//     {
//         return empService.getByjpql(date);
// }

// //JPA
// @GetMapping("/date")
//     public List<Event> getJobsBydate(@RequestParam LocalDate date) {
//         return empService.getJobsBydate(date);
//     }


// }
package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Event;
import com.example.demo.service.EventService;

@RestController
public class EventController {

    @Autowired
    EventService empService;

    // **Regex patterns for validation**
    private static final String NAME_REGEX = "^[A-Za-z ]{3,50}$";  // Allows only letters and spaces (3-50 chars)
    private static final String DATE_REGEX = "^\\d{4}-\\d{2}-\\d{2}$"; // YYYY-MM-DD format

    @PostMapping("/eveadd")
    public Event insertEmployee(@RequestBody Event emp) {
        validateEvent(emp);
        return empService.addeve(emp);
    }

    @PutMapping("/eveupdate/{id}")
    public Event updateEmployee(@PathVariable Long id, @RequestBody Event emp) {
        validateId(id);
        validateEvent(emp);
        return empService.updateeve(id, emp);
    }

    @DeleteMapping("/evedelete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        validateId(id);
        return empService.deleteeve(id);
    }

    @GetMapping("/eveget")
    public List<Event> getAllEmployee() {
        return empService.geteve();
    }

    // Pagination
    @GetMapping("/pageeve")
    public List<Event> getByPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return empService.getUserByPage(page, size).getContent();
    }

    // Sorting
    @GetMapping("/sorteve")
    public List<Event> sortByUser() {
        return empService.sortByUser();
    }

    // JPQL Query with Date Validation
    @GetMapping("/event/{date}")
    public List<Event> getUsername(@PathVariable String date) {
        validateDate(date);
        return empService.getByjpql(LocalDate.parse(date));
    }

    // JPA Query with Date Validation
    @GetMapping("/date")
    public List<Event> getJobsBydate(@RequestParam String date) {
        validateDate(date);
        return empService.getJobsBydate(LocalDate.parse(date));
    }

    // **Validation Methods**
    
    // **1. Validate Event Fields**
    private void validateEvent(Event emp) {
        if (emp.getId() != null && emp.getId() <= 0) {
            throw new IllegalArgumentException("Event ID must be a positive number.");
        }
        if (emp.getEventname() == null || emp.getEventname().trim().isEmpty() || !Pattern.matches(NAME_REGEX, emp.getEventname())) {
            throw new IllegalArgumentException("Invalid event name! It should only contain letters and be 3-50 characters long.");
        }
       
        if (emp.getDate() == null || emp.getDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid date! The event date cannot be in the past.");
        }
    }

    // **2. Validate ID (Ensure it's positive)**
    private void validateId(Long id) {
        if (id == null || id <= 0) {
            
            throw new IllegalArgumentException("Invalid ID! ID must be a positive number.");
        }
    }

    // **3. Validate Date Format**
    private void validateDate(String date) {
        if (!Pattern.matches(DATE_REGEX, date)) { 
            throw new IllegalArgumentException("Invalid date format! Use YYYY-MM-DD.");
        }
        LocalDate parsedDate = LocalDate.parse(date);
        if (parsedDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid date! The event date cannot be in the past.");
        }
    }
}
