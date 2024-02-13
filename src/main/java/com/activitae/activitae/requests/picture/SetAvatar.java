package com.activitae.activitae.requests.picture;

public class SetAvatar {
	private Long picture_id;
	private Boolean isAvatar;
	public Long getPicture_id() {
		return picture_id;
	}
	public void setPicture_id(Long picture_id) {
		this.picture_id = picture_id;
	}
	public Boolean getIsAvatar() {
		return isAvatar;
	}
	public void setIsAvatar(Boolean isAvatar) {
		this.isAvatar = isAvatar;
	}
}
