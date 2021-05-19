package com.lookup.backend.lookupbackend.service.userservice;

import com.lookup.backend.lookupbackend.exception.RecordNotFoundException;
import com.lookup.backend.lookupbackend.model.authority.Authority;
import com.lookup.backend.lookupbackend.model.observationmodel.Observation;
import com.lookup.backend.lookupbackend.model.usermodel.User;
import com.lookup.backend.lookupbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(long id) {
        if (userRepository.existsById(id)) {
            return userRepository.findById(id).get();
        } else {
            throw new RecordNotFoundException("No user with ID " + id);
        }
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
        addAuthority(user.getId(), "USER");
    }

    @Override
    public void deleteById(long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No user with id " + id);
        }
    }

    @Override
    public void updateUser(Long userId, User user) {
        if (userRepository.existsById(userId)) {
            User userToPatch = userRepository.getOne(userId);
            userToPatch.setName(user.getName());
            userToPatch.setEmailAdress(user.getEmailAdress());
            userToPatch.setPassWord(user.getPassWord());
            userRepository.save(userToPatch);
        } else {
            throw new RecordNotFoundException("Couldn't find a user with id" + userId);
        }
    }

    public List<Observation> getUserObservations(Long id){
        if (userRepository.existsById(id)){
            return userRepository.getOne(id).getObservations();
        } else {
            throw new RecordNotFoundException("Couldn't find a user with id" + id);
        }
    }

    @Override
    public Set<Authority> getAuthorities(Long id) {
            if (userRepository.existsById(id)) {
                User user = userRepository.getOne(id);
                return user.getAuthorities();
            } return null;
        }


    @Override
    public void addAuthority(Long id, String authorityToAdd) {
        if (userRepository.existsById(id)) {
            User user = userRepository.getOne(id);
            user.addAuthority(new Authority(user.getName(), authorityToAdd));
            userRepository.save(user);
        }
    }

    @Override
    public void removeAuthority(Long id, String authorityToRemove) {
        if(userRepository.existsById(id)){
            User user = userRepository.getOne(id);
            Authority removeAuthority = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authorityToRemove)).findAny().get();
            user.removeAuthority(removeAuthority);
            userRepository.save(user);
        }
    }


}
