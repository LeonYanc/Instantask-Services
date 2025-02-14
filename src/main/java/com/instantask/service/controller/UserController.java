package com.instantask.service.controller;

import com.instantask.service.model.User;
import com.instantask.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // GET /users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // GET /users/search?name=xxx
    @GetMapping("/search")
    public List<User> findUsersByName(@RequestParam String name) {
        return userRepository.findByName(name);
    }

    // POST /users
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

}


