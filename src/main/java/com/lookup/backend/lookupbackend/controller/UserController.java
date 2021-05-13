package com.lookup.backend.lookupbackend.controller;


import com.lookup.backend.lookupbackend.exception.RecordNotFoundException;
import com.lookup.backend.lookupbackend.model.usermodel.User;
import com.lookup.backend.lookupbackend.repository.UserRepository;
import com.lookup.backend.lookupbackend.service.userservice.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/helloworld")
    public String helloworld() {
        return "Hello world";
    }

    @GetMapping(value = "/user/id/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") long id){
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @GetMapping(value = "/user/name/{name}")
    public ResponseEntity<Object> getUserByName(@PathVariable String name){
        return new ResponseEntity<>(userService.getUserByName(name), HttpStatus.OK);
    }

    @PostMapping(value = "/user")
    public ResponseEntity<Object> createUser(@RequestBody User user){
        userService.save(user);
        return new ResponseEntity<>("User added", HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/user/id/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable Long id) throws RecordNotFoundException {
        userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("this user doesn't exist"));

        userService.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }



}
