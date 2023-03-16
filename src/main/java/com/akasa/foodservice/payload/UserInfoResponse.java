package com.akasa.foodservice.payload;

import java.util.List;

public class UserInfoResponse {

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public List<String> getRoles() {
		return roles;
	}

	final Long id;
	final String username;
	final String email;
	final List<String> roles;

	public UserInfoResponse(Long id, String username, String email, List<String> roles) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}
}
