package com.activitae.activitae.requests;

import java.util.Date;

import jakarta.persistence.Column;

public class RegistrationRequest {

    private String email;
    private String password;
    private Date birthdate;
    private String siret;
    
    
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
}
