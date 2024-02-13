package com.activitae.activitae.requests.activity;

import java.math.BigDecimal;
import java.util.Date;

import com.activitae.activitae.entities.Activite;
import com.activitae.activitae.entities.ActivityPlaceType;
import com.activitae.activitae.entities.ActivityType;
import com.activitae.activitae.requests.user.get.GetUserResponse;



public class GetActivityResponse {
    private Long id;
    private String titre;
    private Date date;
    private String address;
    private BigDecimal price;
    private String descriptif;
    private String info_comp;
    private String site;
    ActivityPlaceType place_type;
    ActivityType type;
	private GetUserResponse user;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public GetUserResponse getUser() {
		return user;
	}
	public void setUser(GetUserResponse user) {
		this.user = user;
	}
	
	public GetActivityResponse() {
		
	}
	
	public GetActivityResponse(Activite activite) {
		setId(activite.getId());
		setTitre(activite.getTitre());
		setDate(activite.getDate());
		setAddress(activite.getAddress());
		setPrice(activite.getPrice());
		setDescriptif(activite.getDescriptif());
		setInfo_comp(activite.getInfo_comp());
		setSite(activite.getSite());
		setUser(new GetUserResponse(activite.getUser()));
	}
}