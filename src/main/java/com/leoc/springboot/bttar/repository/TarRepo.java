package com.leoc.springboot.bttar.repository;

import com.leoc.springboot.bttar.model.Tar;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface TarRepo extends MongoRepository<Tar, String> {
   List<Tar> findByCompanyId(String id);

   Optional<Tar> getTarById(String id);
}
