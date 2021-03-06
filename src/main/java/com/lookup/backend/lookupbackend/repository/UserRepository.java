package com.lookup.backend.lookupbackend.repository;

import com.lookup.backend.lookupbackend.model.usermodel.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    Boolean existsByName(String name);
    void deleteByName(String name);
}
