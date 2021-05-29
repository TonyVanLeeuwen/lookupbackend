package com.lookup.backend.lookupbackend.service.userservice;

import com.lookup.backend.lookupbackend.exception.RecordNotFoundException;
import com.lookup.backend.lookupbackend.model.authority.Authority;
import com.lookup.backend.lookupbackend.model.observationmodel.Observation;
import com.lookup.backend.lookupbackend.model.usermodel.User;
import com.lookup.backend.lookupbackend.repository.UserRepository;
import com.lookup.backend.lookupbackend.service.observationservice.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObservationService observationService;

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
    public ResponseEntity<Object> save(User user) throws Exception {
        if (user.getPassWord() == null || user.getPassWord().equals("")){
            return new ResponseEntity<>("Password cannot be null", HttpStatus.BAD_REQUEST);
        } else if (user.getName() != null && !user.getName().equals("")){
            user.setPassWord(passwordEncoder.encode(user.getPassWord()));
            userRepository.save(user);
            return new ResponseEntity<>("Succesfully added", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Username cannot be null", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> deleteById(String id) {
        if (userRepository.existsByName(id)) {
            userRepository.deleteByName(id);
            return new ResponseEntity<>("Succesfully deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Username cannot be null", HttpStatus.BAD_REQUEST);
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
    public Set<Observation> getObservations(String userId){
        if (userRepository.existsByName(userId)){
            User user = getUserByName(userId);

            return user.getObservations();
        } else {
            throw new RecordNotFoundException("Couldn't find a user with id" + userId);
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
