package com.leoc.springboot.rentalevelyn.mapper;

import com.leoc.springboot.rentalevelyn.dto.ItemDTO;
import com.leoc.springboot.rentalevelyn.model.Item;

public class ItemMapper {

    public static ItemDTO mapperToDTO(Item entity){
        return new ItemDTO(entity.getDescription(), entity.getName(), entity.getPrice(), entity.getQuantity());

    }
}
