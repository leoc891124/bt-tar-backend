package com.leoc.springboot.rentalevelyn.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "roles")
public class Role  {


    @Id
    private String id;
    private ERole name;
    @DBRef
    private Set<User> users;

    public Set<User> getUsers() {
        return users;
    }

    public Role() {

    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name=name;
}

}
