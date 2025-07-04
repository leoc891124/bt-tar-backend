package com.leoc.springboot.rentalevelyn.repository;



import com.leoc.springboot.rentalevelyn.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String> {
    @Query("{email:'?0'}")
    User findByEmail(String email);


    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
