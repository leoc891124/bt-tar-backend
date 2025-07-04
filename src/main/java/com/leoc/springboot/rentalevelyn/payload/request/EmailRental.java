package com.leoc.springboot.rentalevelyn.payload.request;

import com.leoc.springboot.rentalevelyn.model.Rental;

public class EmailRental {

    private String fullName;
    private Rental rental;


    public EmailRental() {
    }

    public EmailRental(String fullName, Rental rental) {
        this.fullName = fullName;
        this.rental = rental;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }
}
