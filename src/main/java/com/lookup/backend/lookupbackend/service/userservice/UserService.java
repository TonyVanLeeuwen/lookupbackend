package com.lookup.backend.lookupbackend.service.userservice;

import com.lookup.backend.lookupbackend.model.authority.Authority;
import com.lookup.backend.lookupbackend.model.observationmodel.Observation;
import com.lookup.backend.lookupbackend.model.usermodel.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    public List<User> getAllUsers();
    public User getUserByName(String name);
    public void save(User user);
    public void deleteById(String id);
    public void updateUser(String userId, User user);
    public List<Observation> getUserObservations(String id);
    public Set<Authority> getAuthorities(String id);
    public void addAuthority(String id, String authorityToAdd);
    public void removeAuthority(String id, String authorityToRemove);

}
