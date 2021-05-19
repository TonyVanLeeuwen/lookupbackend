package com.lookup.backend.lookupbackend.controller;

import com.lookup.backend.lookupbackend.model.nearearthobjectmodel.NearEarthObjectType;
import com.lookup.backend.lookupbackend.service.nearearthobjectservice.NearEarthObjectService;
import com.lookup.backend.lookupbackend.service.observationservice.ObservationService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@NoArgsConstructor
@RestController
public class NearEarthObjectController {

    @Autowired
    private NearEarthObjectService nearEarthObjectService;

    @GetMapping(value = "/neo/get")
    public ResponseEntity<Object> getAllNeos(){
        return new ResponseEntity<>(nearEarthObjectService.getAllNearEarthObjects(), HttpStatus.OK);
    }

 @GetMapping(value= "/neo/get/{name}")
    public ResponseEntity<Object> getNEOByName(@PathVariable String name, @RequestBody NearEarthObjectType Type){
     return new ResponseEntity<>(nearEarthObjectService.getNearEarthObjectByType(Type), HttpStatus.OK);
 }

 @GetMapping(value= "/neo/get/{id}")
    public ResponseEntity<Object> getNeoById(@PathVariable long id){
     return new ResponseEntity<>(nearEarthObjectService.getNearEarthObjectById(id), HttpStatus.OK);
 }



}
