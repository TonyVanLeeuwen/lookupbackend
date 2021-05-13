package com.lookup.backend.lookupbackend.controller;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@NoArgsConstructor
@RestController
public class NearEarthObjectController {

 @GetMapping(value= "/neo/get/{name}")
    public String getNEOByName(@PathVariable String name){
     return "this page is for all your NEO's gotten by name";
 }

 @GetMapping(value= "/neo/get/{id}")
    public String getNeoById(@PathVariable long id){
     return "this page is for all your NEO's gotten by ID";
 }



}
