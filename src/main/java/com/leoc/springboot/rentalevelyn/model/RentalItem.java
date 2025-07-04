package com.leoc.springboot.rentalevelyn.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rental_items")
public class RentalItem {
    @Id
    private String id;
    private int quantitySelected;
    private String itemId;


    public RentalItem(String id, int quantitySelected, String itemId) {
        this.id = id;
        this.quantitySelected = quantitySelected;
        this.itemId = itemId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantitySelected() {
        return quantitySelected;
    }

    public void setQuantitySelected(int quantitySelected) {
        this.quantitySelected = quantitySelected;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
