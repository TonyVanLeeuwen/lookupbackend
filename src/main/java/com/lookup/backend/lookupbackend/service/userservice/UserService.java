package com.lookup.backend.lookupbackend.service.userservice;

import com.lookup.backend.lookupbackend.model.authority.Authority;
import com.lookup.backend.lookupbackend.model.observationmodel.Observation;
import com.lookup.backend.lookupbackend.model.usermodel.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface UserService {

    public List<User> getAllUsers();
    public User getUserByName(String name);
    public ResponseEntity save(User user) throws Exception;
    public ResponseEntity<String> deleteById(String id);
    public void updateUser(String userId, User user);
    public Set<Observation> getObservations(String userId);
    public Set<Authority> getAuthorities(String id);
    public void addAuthority(String id, String authorityToAdd);
    public void removeAuthority(String id, String authorityToRemove);

}
