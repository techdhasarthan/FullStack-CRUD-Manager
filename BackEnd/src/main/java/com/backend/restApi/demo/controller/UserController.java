package com.backend.restApi.demo.controller;

import com.backend.restApi.demo.exception.userNotFoundException;
import com.backend.restApi.demo.model.User;
import com.backend.restApi.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return  userRepository.save(newUser);
    }

    @GetMapping("/user")
    List<User> getAllUser(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserId(@PathVariable Long id){
        return  userRepository.findById(id).orElseThrow(()->new userNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser ,@PathVariable Long id){
        return userRepository.findById(id).map(user -> {
            user.setUsername(newUser.getUsername());
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            return userRepository.save(user);
        }).orElseThrow(()->new userNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new userNotFoundException(id);
        }

        userRepository.deleteById(id);
        return "The deleted ID is "+id;
    }
}
