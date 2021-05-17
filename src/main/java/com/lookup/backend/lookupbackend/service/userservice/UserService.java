package com.lookup.backend.lookupbackend.service.userservice;

import com.lookup.backend.lookupbackend.exception.RecordNotFoundException;
import com.lookup.backend.lookupbackend.model.authority.Authority;
import com.lookup.backend.lookupbackend.model.observationmodel.Observation;
import com.lookup.backend.lookupbackend.model.usermodel.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    public List<User> getAllUsers();
    public User getUser(long id);
    public User getUserByName(String name);
    public void save(User user);
    public void deleteById(long id);
    public void updateUser(Long userId, User user);
    public List<Observation> getUserObservations(Long id);
    public Set<Authority> getAuthorities(Long id);
    public void addAuthority(Long id, String authorityToAdd);
    public void removeAuthority(Long id, String authorityToRemove);

}
