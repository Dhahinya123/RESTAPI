package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/users")  // Base path for all user-related endpoints
public class UserController {

    @Autowired
    private UserService userService;

    // Create a new user
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User savedUser = userService.addUser(user);
        return ResponseEntity.ok(savedUser);
    }

    // Get all users
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Update a user
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete a user
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        String message = userService.deleteUser(id);
        return ResponseEntity.ok(message);
    }

    // Pagination
    @GetMapping("/page")
    public ResponseEntity<Page<User>> getUsersByPage(
            @RequestParam(defaultValue = "0") int page, 
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(userService.getUsersByPage(page, size));
    }

    // Sorting (e.g., sorting by name)
    @GetMapping("/sort")
    public ResponseEntity<List<User>> sortUsers() {
        return ResponseEntity.ok(userService.sortUsers());
    }

    // JPQL Query Example: Find user by name
    @GetMapping("/find/{name}")
    public ResponseEntity<List<User>> getUsersByName(@PathVariable String name) {
        return ResponseEntity.ok(userService.getUsersByName(name));
    }

    // JPA Query Example: Find users by email
    @GetMapping("/email")
    public ResponseEntity<List<User>> getUsersByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.getUsersByEmail(email));
    }
}
