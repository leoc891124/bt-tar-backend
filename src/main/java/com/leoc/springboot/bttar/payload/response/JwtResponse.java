package com.leoc.springboot.bttar.payload.response;

import com.leoc.springboot.bttar.model.Companies;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String id;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private List<String> roles;
	private Boolean isAdmin;
	private Companies company;

	public JwtResponse(String accessToken, String id, String username, String email, List<String> roles,
					   String firstName, String lastName, Boolean isAdmin, Companies company) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isAdmin = isAdmin;
		this.email = email;
		this.roles = roles;
		this.company = company;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public String getFirstName() {return firstName;	}

	public void setFirstName(String firstName) {this.firstName = firstName; }

	public String getLastName() { 	return lastName; 	}

	public void setLastName(String lastName) { 	this.lastName = lastName; 	}

	public Boolean getAdmin() { return isAdmin; }

	public void setAdmin(Boolean admin) { isAdmin = admin; 	}

	public Companies getCompany() {
		return company;
	}

	public void setCompany(Companies company) {
		this.company = company;
	}
}
