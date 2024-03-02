package com.activitae.activitae.requests;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CreateThematicRequest {

	private String item;
	private String  imageUrl;
    
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

    public CreateThematicRequest(String item, String imageUrl) {
    	setItem(item);
    	setImageUrl(imageUrl);
    }
}