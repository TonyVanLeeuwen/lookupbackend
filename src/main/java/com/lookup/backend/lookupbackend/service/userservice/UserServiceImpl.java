package com.lookup.backend.lookupbackend.service.userservice;

import com.lookup.backend.lookupbackend.exception.RecordNotFoundException;
import com.lookup.backend.lookupbackend.model.usermodel.User;
import com.lookup.backend.lookupbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public User getUser(long id){
        if(userRepository.existsById(id)){
            return userRepository.findById(id).get();
        } else {
            throw new RecordNotFoundException("No user with ID " + id);
        }
    }

    @Override
    public User getUserByName(String name){
        return userRepository.findByName(name);
    }

    @Override
    public void save(User user){
        userRepository.save(user);
    }

    @Override
    public void deleteById(long id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No user with id " + id);
        }
    }

//    public Optional<User> updateUser(Long userId, User user) {
//        return userRepository.findById(userId)
//                .map(existingUser -> {
//                    userEntityMapper.updateUserEntity(existingUser, user);
//                    return userEntityMapper.map(userRepository.save(existingUser));
//                });
//    }
}
