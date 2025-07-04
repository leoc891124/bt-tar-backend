package com.leoc.springboot.rentalevelyn.payload.request;

import java.util.Set;

import com.leoc.springboot.rentalevelyn.model.Role;
import jakarta.validation.constraints.*;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    @NotBlank
    @Size(min = 3, max = 20)
    private String firstName;
    @NotBlank
    @Size(min = 3, max = 20)
    private String lastName;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    private Boolean isAdmin;
    private Set<String> roles;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;


    private Boolean enabled;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Boolean getAdmin() { return isAdmin;   }

    public void setAdmin(Boolean isAdmin) { this.isAdmin = isAdmin;  }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
