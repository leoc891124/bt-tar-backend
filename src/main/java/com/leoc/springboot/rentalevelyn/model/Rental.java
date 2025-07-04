package com.leoc.springboot.rentalevelyn.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;


@Document(collection = "rental")
public class Rental {

    @Id
    private String id;
    private String name;
    private Date  initalDate;
    private Date finalDate;
    private String email;
    /*@DBRef
    private List<Item> items;*/
    @DBRef
    private List<RentalItem> items;
    private Double totalRental;

    public Rental(String id, String name, Date initalDate, Date finalDate, String email, List<RentalItem> items, Double totalRental) {
        this.id = id;
        this.name = name;
        this.initalDate = initalDate;
        this.finalDate = finalDate;
        this.email = email;
        this.items = items;
        this.totalRental = totalRental;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getInitalDate() {
        return initalDate;
    }

    public void setInitalDate(Date initalDate) {
        this.initalDate = initalDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RentalItem> getItems() {
        return items;
    }

    public void setItems(List<RentalItem> items) {
        this.items = items;
    }

    public Double getTotalRental() {
        return totalRental;
    }

    public void setTotalRental(Double totalRental) {
        this.totalRental = totalRental;
    }
}
