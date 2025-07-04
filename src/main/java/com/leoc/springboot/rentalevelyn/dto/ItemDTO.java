package com.leoc.springboot.rentalevelyn.dto;

import com.leoc.springboot.rentalevelyn.model.EType;

public class ItemDTO {

    private String description;
    private int quantity;
    private String name;
    private Double price;

    public ItemDTO() {
    }

    public ItemDTO(String description, String name, Double price, int quantity) {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
