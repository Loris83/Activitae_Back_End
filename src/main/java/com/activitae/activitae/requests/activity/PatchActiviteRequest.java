package com.activitae.activitae.requests.activity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.activitae.activitae.entities.ActivityPlaceType;
import com.activitae.activitae.entities.ActivityType;
import com.activitae.activitae.entities.User;


public class PatchActiviteRequest {

	private List<ActiviteFields> fields;
	

	private String titre;
    private Date date;
    private String address; //chez Jerem
    private BigDecimal price;
    private String descriptif;
    private String info_comp;
    private String site;
    private String image_url;
    ActivityPlaceType place_type;
    ActivityType type;
	private User user;
	
	public List<ActiviteFields> getFields() {
		return fields;
	}
	public void setFields(List<ActiviteFields> fields) {
		this.fields = fields;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getDescriptif() {
		return descriptif;
	}
	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}
	public String getInfo_comp() {
		return info_comp;
	}
	public void setInfo_comp(String info_comp) {
		this.info_comp = info_comp;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public ActivityPlaceType getPlace_type() {
		return place_type;
	}
	public void setPlace_type(ActivityPlaceType place_type) {
		this.place_type = place_type;
	}
	public ActivityType getType() {
		return type;
	}
	public void setType(ActivityType type) {
		this.type = type;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
    private Long id ;
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
