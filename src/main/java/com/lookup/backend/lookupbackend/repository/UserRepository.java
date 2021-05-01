package com.lookup.backend.lookupbackend.repository;

import com.lookup.backend.lookupbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

}
