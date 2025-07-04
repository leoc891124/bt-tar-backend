package com.leoc.springboot.rentalevelyn.repository;

import com.leoc.springboot.rentalevelyn.model.Rental;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RentalRepo extends MongoRepository<Rental, String> {


    List<Rental> findByEmail(String email);

    Boolean existsByName(String name);
}
