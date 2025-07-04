package com.leoc.springboot.rentalevelyn.controller;

import com.leoc.springboot.rentalevelyn.dto.ItemDTO;
import com.leoc.springboot.rentalevelyn.model.Item;
import com.leoc.springboot.rentalevelyn.payload.response.MessageResponse;
import com.leoc.springboot.rentalevelyn.repository.ItemRepo;
import com.leoc.springboot.rentalevelyn.services.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://evelyn-rental.netlify.app"
})
@RestController
@RequestMapping("/rentalevelyn/v1.0")
public class ItemController {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ItemService itemService;

    @PostMapping("/item")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addItem(@Valid @RequestBody ItemDTO item){

        //Optional<Item> itemexit = itemService.existByName(item.getName().toUpperCase().trim())
        if(item.getName() != null) {
            if (itemService.existByName(item.getName().toUpperCase().trim())) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Name Item is already taken!"));
            }
        }
         itemService.addItem(item);

         return ResponseEntity.ok(new MessageResponse("Item successfully added"));
    }

    @PutMapping("/item/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateItem(@Valid @RequestBody Item item, @PathVariable String id){

        Optional<Item> itemUpdate = itemService.getItemById(id);

        if(itemUpdate.isEmpty()){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: item with id '"+item.getId()+"' doesn't exist"));
        }


        itemService.updateItem(item);
        return ResponseEntity.ok(new MessageResponse("Item successfully updated"));
    }

    @GetMapping("/items")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Item> getAllItems(){
        return itemService.getItems();
    }


   /* @GetMapping("/item/{name}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Item> getItemByName(@PathVariable EType name){
        return itemService.getItemByName(name);
    }*/

    @GetMapping("/item/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getItemById(@PathVariable String id){
        Optional<Item> item = itemService.getItemById(id);

        if(item.isEmpty()){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: item with id '"+id+"' doesn't exist"));
        }

        return ResponseEntity.ok(item);
    }


    @DeleteMapping("/item/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteItem(@PathVariable String id){

        /*Optional<Item> itemUpdate = itemService.getItemById(id);

        if(itemUpdate.isEmpty()){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: item with id '"+item.getId()+"' doesn't exist"));
        }
        if(itemService.existByName(item.getName().toUpperCase().trim())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Name Item is already taken!"));
        }*/


        itemService.deleteItem(id);
        return ResponseEntity.ok(new MessageResponse("Item successfully deleted"));
    }


    @GetMapping("/item/{id}/{quantity}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Boolean itemInStock(@PathVariable String id, @PathVariable int quantity){
        return itemService.isItemAvailable(id, quantity);
    }

   /* @GetMapping("/items/names")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<EType> getItemNames(){

        List<Item> items = itemService.getItems();

        return items.stream().map(Item::getName).distinct().toList();
    }*/





}
