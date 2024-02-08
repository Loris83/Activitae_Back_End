package com.activitae.activitae.entities;

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
    
    @ElementCollection(fetch = FetchType.EAGER)
    List<Role> roles;

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
	
	public List<Role> getRoles(){
		return roles;
	}
	
	public void setRoles( List<Role> roles){
		this.roles = roles;
	}
	
}
