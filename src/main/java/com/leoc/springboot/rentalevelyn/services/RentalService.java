package com.leoc.springboot.rentalevelyn.services;

import com.leoc.springboot.rentalevelyn.model.Rental;
import com.leoc.springboot.rentalevelyn.repository.RentalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {

    @Autowired
    private RentalRepo rentalRepo;

    public List<Rental> getAllRentals(){
        return rentalRepo.findAll();
    }

    public void saveRental(Rental rental){
        rentalRepo.save(rental);
    }

    public List<Rental> getByEmail(String email){
        return rentalRepo.findByEmail(email);
    }

    public Rental getById(String id){
        return rentalRepo.findById(id).orElse(null);
    }

    public Boolean existByName(String name){
        return rentalRepo.existsByName(name);
    }

    public void deleteRentalById(String id){
        rentalRepo.deleteById(id);
    }
}
