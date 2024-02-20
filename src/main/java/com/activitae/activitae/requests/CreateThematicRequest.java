package com.activitae.activitae.requests;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CreateThematicRequest {

	private String name;
	private String  imageUrl;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

    public CreateThematicRequest(String name, String imageUrl) {
    	setName(name);
    	setImageUrl(imageUrl);
    }
}