package com.leoc.springboot.rentalevelyn.repository;

import com.leoc.springboot.rentalevelyn.model.Item;
import com.leoc.springboot.rentalevelyn.model.RentalItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RentalItemRepo extends MongoRepository<RentalItem, String> {



}
