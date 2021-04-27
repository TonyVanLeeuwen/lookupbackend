package com.lookup.backend.lookupbackend.controller;


import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@NoArgsConstructor
@RestController
public class UserController {

    @GetMapping(value = "/helloworld")
    public String helloworld() {
        return "Hello world";
    }

}
