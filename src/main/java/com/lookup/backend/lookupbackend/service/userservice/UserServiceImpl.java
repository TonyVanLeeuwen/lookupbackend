package com.lookup.backend.lookupbackend.service.userservice;

import com.lookup.backend.lookupbackend.exception.RecordNotFoundException;
import com.lookup.backend.lookupbackend.model.authority.Authority;
import com.lookup.backend.lookupbackend.model.observationmodel.Observation;
import com.lookup.backend.lookupbackend.model.usermodel.User;
import com.lookup.backend.lookupbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public void save(User user) {
        User newUser = user;

        newUser.setPassWord(passwordEncoder.encode(user.getPassWord()));

        userRepository.save(newUser);
    }

    @Override
    public void deleteById(String id) {
        if (userRepository.existsByName(id)) {
            userRepository.deleteByName(id);
        } else {
            throw new RecordNotFoundException("No user with id " + id);
        }
    }

    @Override
    public void updateUser(String userId, User user) {
        if (userRepository.existsByName(userId)) {
            User userToPatch = userRepository.findByName(userId);
            userToPatch.setName(user.getName());
            userToPatch.setEmailAdress(user.getEmailAdress());
            userToPatch.setPassWord(user.getPassWord());
            userRepository.save(userToPatch);
        } else {
            throw new RecordNotFoundException("Couldn't find a user with id" + userId);
        }
    }

    @Override
    public List<Observation> getUserObservations(String id){
        if (userRepository.existsByName(id)){
            return userRepository.findByName(id).getObservations();
        } else {
            throw new RecordNotFoundException("Couldn't find a user with id" + id);
        }
    }

    @Override
    public Set<Authority> getAuthorities(String id) {
            if (userRepository.existsByName(id)) {
                User user = userRepository.findByName(id);
                return user.getAuthorities();
            } return null;
        }


    @Override
    public void addAuthority(String id, String authorityToAdd) {
        if (userRepository.existsByName(id)) {
            User user = userRepository.findByName(id);
            user.addAuthority(new Authority(user.getName(), authorityToAdd));
            userRepository.save(user);
        }
    }

    @Override
    public void removeAuthority(String id, String authorityToRemove) {
        if(userRepository.existsByName(id)){
            User user = userRepository.findByName(id);
            Authority removeAuthority = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authorityToRemove)).findAny().get();
            user.removeAuthority(removeAuthority);
            userRepository.save(user);
        }
    }


}
