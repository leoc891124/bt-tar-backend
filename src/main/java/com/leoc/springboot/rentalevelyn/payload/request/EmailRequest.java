package com.leoc.springboot.rentalevelyn.payload.request;

import com.leoc.springboot.rentalevelyn.model.Item;

import java.util.Date;
import java.util.List;

public class EmailRequest {

    private String to;
    private String subject;
    private String name;
    private String initalDate;
    private String finalDate;
    private List<Item> items;


    public EmailRequest() {
    }

    public EmailRequest(String to, String subject, String name, String initalDate, String finalDate, List<Item> items) {
        this.to = to;
        this.subject = subject;
        this.name = name;
        this.initalDate = initalDate;
        this.finalDate = finalDate;
        this.items = items;
    }

    public String getInitalDate() {
        return initalDate;
    }

    public void setInitalDate(String initalDate) {
        this.initalDate = initalDate;
    }

    public String getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(String finalDate) {
        this.finalDate = finalDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
