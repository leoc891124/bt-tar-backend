package com.leoc.springboot.rentalevelyn.services;

import com.leoc.springboot.rentalevelyn.dto.ItemDTO;
import com.leoc.springboot.rentalevelyn.model.EType;
import com.leoc.springboot.rentalevelyn.model.Item;
import com.leoc.springboot.rentalevelyn.repository.ItemRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    public void addItem(ItemDTO itemDTO) {
        Item item = modelMapper.map(itemDTO, Item.class);
        itemRepo.save(item);

    }

    public List<Item> getItems() {
        return itemRepo.findAll();
    }

    public Optional<Item> getItemById(String id) {
        return itemRepo.findById(id);

    }

    public Boolean existByName(String name) {
        return itemRepo.existsByName(name);
    }

    public Optional<Item> findByName(String name){
        return itemRepo.findByName(name);
    }

    public void updateItem(Item item) {
        itemRepo.save(item);

    }

    public Boolean isItemAvailable(String id, int quantity){
        Optional<Item> itemById = itemRepo.findById(id);
        return itemById.filter(item -> item.getQuantity() >= quantity).isPresent();

    }

    public void deleteItem(String id){
        itemRepo.deleteById(id);
    }

    public List<Item> getAllItemsById(List<String> ids){
        return itemRepo.findAllById(ids);
    }



   /* public List<Item> getItemByName(String name) {
       // List<Item> items = itemRepo.findByName(name);
        // Manejar el error
        // return "No se encontro el usuario.";
        return itemRepo.findByName(name);

    }*/



}
