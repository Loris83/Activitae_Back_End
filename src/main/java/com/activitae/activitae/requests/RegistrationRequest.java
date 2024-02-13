package com.activitae.activitae.requests;

import java.util.Date;

import com.activitae.activitae.entities.Role;

import jakarta.persistence.Column;

public class RegistrationRequest {

    private String email;
    private String password;
    private Date birthdate;
    private String siret;
    private String username;
    private Role role;
    
    
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

    public RegistrationRequest(String email, String password) {
    	setEmail(email);
    	setPassword(password);
    }
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getSiret() {
		return siret;
	}
	public void setSiret(String siret) {
		this.siret = siret;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}
