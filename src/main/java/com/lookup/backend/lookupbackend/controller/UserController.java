package com.lookup.backend.lookupbackend.controller;

import com.lookup.backend.lookupbackend.exception.RecordNotFoundException;
import com.lookup.backend.lookupbackend.model.authority.Authority;
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

    @GetMapping(value = "/users/user/name/{name}")
    public ResponseEntity<Object> getUserByName(@PathVariable String name){
        return new ResponseEntity<>(userService.getUserByName(name), HttpStatus.OK);
    }

    @GetMapping(value = "/users/user/observations/{id}")
    public ResponseEntity<Object> getUserObservations(@PathVariable String id){
        return new ResponseEntity<>(userService.getUserObservations(id), HttpStatus.OK);
    }

    @PostMapping(value = "/user")
    public ResponseEntity<Object> createUser(@RequestBody User user){
        userService.save(user);
        return new ResponseEntity<>("User added", HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/users/user/id/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable String id) throws RecordNotFoundException {
        userService.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PatchMapping(path = "/users/user/id/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        try {
            userService.updateUser(id, user);
            return ResponseEntity.ok(user);
        } catch (RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(value = "/users/user/{id}/authorities")
    public ResponseEntity<Object> getUserAuthorities(@PathVariable String id){
        return new ResponseEntity<>(userService.getAuthorities(id), HttpStatus.OK);
    }

    @PostMapping(value = "/users/user/{id}/authority")
    public ResponseEntity<Object> addUserAuthorities(@PathVariable String id, @RequestBody Authority authority){
        userService.addAuthority(id, authority.getAuthority());
        return new ResponseEntity<>("Authority added" ,HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/user/{id}/authority")
    public ResponseEntity<Object> deleteUserAuthorities(@PathVariable String id, @RequestBody Authority authority){
        userService.removeAuthority(id, authority.getAuthority());
        return new ResponseEntity<>("Authority removed", HttpStatus.OK);
    }
}
