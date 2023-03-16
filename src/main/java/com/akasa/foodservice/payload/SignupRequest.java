package com.akasa.foodservice.payload;

import java.util.List;
import java.util.Set;

public class SignupRequest {

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	String username;
	String email;
	String password;

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> roles) {
		this.role = roles;
	}

	Set<String> role;

}
