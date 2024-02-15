package com.activitae.activitae.requests.user;

import java.util.Date;
import java.util.List;

import com.activitae.activitae.entities.Role;

public class PatchUserRequest {
	private List<UserFields> fields;
	private Long id;
	private String email;
    private String password;
    private Date birthdate;
    private String siret;
    private String username;
    private String image_url;
    private Role role;
    
	public List<UserFields> getFields() {
		return fields;
	}
	public void setFields(List<UserFields> fields) {
		this.fields = fields;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
}
