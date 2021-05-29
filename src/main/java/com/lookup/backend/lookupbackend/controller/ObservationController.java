package com.lookup.backend.lookupbackend.controller;

import com.lookup.backend.lookupbackend.exception.RecordNotFoundException;
import com.lookup.backend.lookupbackend.model.observationmodel.Observation;
import com.lookup.backend.lookupbackend.model.usermodel.User;
import com.lookup.backend.lookupbackend.repository.ObservationRepository;
import com.lookup.backend.lookupbackend.service.observationservice.ObservationService;
import com.lookup.backend.lookupbackend.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
public class ObservationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ObservationService observationService;

    @GetMapping(value = "/observation/id/{id}")
    public ResponseEntity<Object> getObservationById(@PathVariable long id){
        return new ResponseEntity<>(observationService.getObservationbyId(id), HttpStatus.OK);
    }

    @GetMapping(value = "/observation")
    public ResponseEntity<Object> getAllObservations(){
        return new ResponseEntity<>(observationService.getAllObservations(), HttpStatus.OK);
    }

    @GetMapping(value = "/observation/sortedbyvotesascending")
    public ResponseEntity<Object> getAllObservationsSortedByVotesAscending(){
        return new ResponseEntity<>(observationService.getObservationsSortedByVotes(false), HttpStatus.OK);
    }

    @GetMapping(value = "/observation/sortedbyvotesdescending")
    public ResponseEntity<Object> getAllObservationsSortedByVotesDescending(){
        return new ResponseEntity<>(observationService.getObservationsSortedByVotes(true), HttpStatus.OK);
    }

    @GetMapping(value = "/observation/nearearthobject/id/{id}")
    public ResponseEntity<Object> getObservationsByNearEarthObject(@PathVariable String id){
        return new ResponseEntity<>(observationService.getObservationByNearEarthObject(id), HttpStatus.OK);
    }

    @PostMapping(value = "/users/observation")
    public ResponseEntity<Object> createObservation(@RequestBody Observation observation, Principal principal){
        User user = userService.getUserByName(principal.getName());

        user.addObservation(observation);
        userService.updateUser(user.getName(), user);
        return new ResponseEntity<>("Observation added", HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/users/observation/id/{id}")
    public Map<String, Boolean> deleteObservation(@PathVariable Long id) {
        observationService.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PatchMapping(path = "/users/observation/id/{id}")
    public ResponseEntity<Observation> updateObservation(@PathVariable Long id, @RequestBody Observation observation){
        try {
            observationService.updateObservation(id, observation);
            return ResponseEntity.ok(observation);
        } catch (RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
