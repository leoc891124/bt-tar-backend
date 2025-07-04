package com.leoc.springboot.rentalevelyn.repository;



import com.leoc.springboot.rentalevelyn.model.ERole;
import com.leoc.springboot.rentalevelyn.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepo extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
