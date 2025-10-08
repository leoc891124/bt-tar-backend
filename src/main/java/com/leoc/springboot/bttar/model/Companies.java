package com.leoc.springboot.bttar.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "companies")
public class Companies {

    @Id
    private String id;
    private String nit;
    private String name;
    private String address;


    public Companies() {
    }

    public Companies(String id, String nit, String name, String address) {
        this.id = id;
        this.nit = nit;
        this.name = name;
        this.address = address;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
