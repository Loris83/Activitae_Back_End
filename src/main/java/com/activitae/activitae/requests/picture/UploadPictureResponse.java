package com.activitae.activitae.requests.picture;

import com.activitae.activitae.entities.Picture;

public class UploadPictureResponse {
	private Long id;
	
	private String path;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public UploadPictureResponse(Picture picture) {
		this.id = picture.getId();
		this.path = "/api/pictures/view/"+this.id;
	}
}
