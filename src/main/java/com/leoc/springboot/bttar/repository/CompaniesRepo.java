package com.leoc.springboot.bttar.repository;

import com.leoc.springboot.bttar.model.Companies;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompaniesRepo extends MongoRepository<Companies, String> {

    Boolean existsByNit(String nit);

}
