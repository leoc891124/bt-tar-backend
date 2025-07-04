package com.leoc.springboot.rentalevelyn.controller;


import com.leoc.springboot.rentalevelyn.model.Item;
import com.leoc.springboot.rentalevelyn.model.Rental;
import com.leoc.springboot.rentalevelyn.model.RentalItem;
import com.leoc.springboot.rentalevelyn.payload.response.MessageResponse;
import com.leoc.springboot.rentalevelyn.repository.RentalItemRepo;
import com.leoc.springboot.rentalevelyn.services.ItemService;
import com.leoc.springboot.rentalevelyn.services.RentalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://evelyn-rental.netlify.app"
})
@RestController
@RequestMapping("/rentalevelyn/v1.0")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private RentalItemRepo rentalItemRepo;

    @PostMapping("/rental")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> addRental(@Valid @RequestBody Rental rental){

        if( !rental.getItems().isEmpty()){

            List<RentalItem> rentalItems = rental.getItems();

            rentalItemRepo.saveAll(rental.getItems());
            Boolean existInStack = true;
            for(RentalItem rentalItem : rentalItems){
                if(!itemService.isItemAvailable(rentalItem.getItemId(), rentalItem.getQuantitySelected())){
                    return ResponseEntity.badRequest().body(new MessageResponse("Not enough in stock item "+ rentalItem.getId()));
                }
            }

        }
        else {
            return ResponseEntity.badRequest().body(new MessageResponse("At least one item is required"));
        }

        rental.getItems().forEach( ite ->  itemService.getItemById(ite.getItemId()).map(
                itemId -> {

                    itemId.setQuantity(itemId.getQuantity() - ite.getQuantitySelected());
                    itemService.updateItem(itemId);
                    return "success stock update";
                }
        ));

        if(rental.getName() != null) {
            if (rentalService.existByName(rental.getName().toUpperCase().trim())) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Rental Name " + rental.getName() +" is already taken!"));
            }
        }
        rentalService.saveRental(rental);
        return ResponseEntity.ok(new MessageResponse("Rental successfully booked"));
    }

    @GetMapping("/rentals")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Rental> getAllRental(){
        return rentalService.getAllRentals();
    }

    @GetMapping("/rental/email/{email}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Rental> getByEmail(@PathVariable String email){
        return rentalService.getByEmail(email);
    }

    @DeleteMapping("/rental/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteRental(@PathVariable String id){

        Rental rentalById = rentalService.getById(id);

        if(rentalById != null && !rentalById.getItems().isEmpty()){

            rentalById.getItems().forEach( ite ->  itemService.getItemById(ite.getItemId()).map(
                    itemId -> {

                        itemId.setQuantity(itemId.getQuantity() + ite.getQuantitySelected());
                        itemService.updateItem(itemId);
                        return "success stock update";
                    }
            ));
            rentalItemRepo.deleteAll(rentalById.getItems());
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: rental service with id '"+id+"' doesn't exist"));
        }



        rentalService.deleteRentalById(id);
        return ResponseEntity.ok(new MessageResponse("Rental service successfully deleted"));
    }



}
