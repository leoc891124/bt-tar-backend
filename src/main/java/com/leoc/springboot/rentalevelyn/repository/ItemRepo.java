package com.leoc.springboot.rentalevelyn.repository;

import com.leoc.springboot.rentalevelyn.model.EType;
import com.leoc.springboot.rentalevelyn.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepo extends MongoRepository<Item, String> {

    Optional<Item> findByName(String name);

    Boolean existsByName(String name);

}
