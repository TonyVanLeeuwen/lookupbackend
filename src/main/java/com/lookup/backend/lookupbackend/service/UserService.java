package com.lookup.backend.lookupbackend.service;

import com.lookup.backend.lookupbackend.model.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();
    public User getUser(long id);
    public User getUserByName(String name);
    public void save(User user);
    public void deleteById(long id);
    public void changeEmailForExistingUser(Long id, String emaild);

}
