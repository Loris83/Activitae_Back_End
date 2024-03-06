package com.activitae.activitae.requests.user.get;

import java.util.Date;
import java.util.List;

import com.activitae.activitae.entities.Activite;
import com.activitae.activitae.entities.Role;
import com.activitae.activitae.entities.User;


public class GetUserResponse {
    private Long id;
    
    private String username;

    private String siret;
    
    private String image_url;
    
    private List<Activite> favorites;
    
    private List<Activite> seen_activities;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSiret() {
		return siret;
	}

	public void setSiret(String siret) {
		this.siret = siret;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	
	public List<Activite> getFavorites(){
		return favorites;
	}
	
	public void setFavorites(List<Activite> favorites){
		this.favorites = favorites;
	}
	
	public GetUserResponse(User user) {
		setId(user.getId());
		setUsername(user.getUsername());
		setSiret(user.getSiret());
		setImage_url(user.getImage_url());
		setFavorites(user.getFavorites());
		setSeen_activities(user.getSeen_activities());
	}
	
	public GetUserResponse() {
		
	}

	public List<Activite> getSeen_activities() {
		return seen_activities;
	}

	public void setSeen_activities(List<Activite> seen_activities) {
		this.seen_activities = seen_activities;
	}
}
