package com.leoc.springboot.bttar.repository;


import com.leoc.springboot.bttar.model.ERole;
import com.leoc.springboot.bttar.model.Role;
import com.leoc.springboot.bttar.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepo extends MongoRepository<User, String> {
    @Query("{email:'?0'}")
    User findByEmail(String email);


    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    List<User> findByRoles(Role role);
}
