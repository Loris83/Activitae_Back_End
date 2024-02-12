package com.activitae.activitae.entities;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String pseudo;
    
    @Column(nullable = true)
    private Date birthdate;
    
    @Column(nullable = true)
    private String image_url;
    
    @Column(nullable = true)
    private String siret;
    
    @ElementCollection(fetch = FetchType.EAGER)
    List<Role> roles;
    
    public void setDate(Date date) {
    	this.birthdate = date;
    }
    
    public void setSiret(String siret) {
    	this.siret = siret;
    }

    public void setId(Long id) {
		this.id = id;
	}
    
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPseudo() {
		return pseudo;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Date getBirthdate() {
		return birthdate;
	}
	
	public String getSiret() {
		return siret;
	}
	
	public List<Role> getRoles(){
		return roles;
	}
	
	public void setRoles( List<Role> roles){
		this.roles = roles;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	
}
