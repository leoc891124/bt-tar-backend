package com.leoc.springboot.bttar.repository;



import com.leoc.springboot.bttar.model.ERole;
import com.leoc.springboot.bttar.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepo extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
