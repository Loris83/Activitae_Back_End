package com.activitae.activitae.requests.activity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.activitae.activitae.entities.ActivityPlaceType;
import com.activitae.activitae.entities.ActivityType;
import com.activitae.activitae.entities.EntrantType;
import com.activitae.activitae.entities.Thematique;
import com.activitae.activitae.entities.User;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class CreateActiviteRequest {
	
    private String titre;
	
    private Date date;
	
    private String address; //chez Jerem
	
    private BigDecimal price;
	
    private String descriptif;
	
    private String info_comp;
	
    private String site;
    
    private ActivityPlaceType place_type;
    
    private ActivityType type;
    
    private EntrantType entrantType;

	private Integer maxParticipants;
	
	private Integer minAge;
	
	private Integer maxAge;
    
    List<Thematique> activity_thematics;

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
	
	public ActivityPlaceType getPlace_type(){
		return place_type;
	}
	
	public void setPlace_type(ActivityPlaceType place_type) {
		this.place_type = place_type;
	}
	
	public ActivityType getType(){
		return type;
	}
	
	public void setType(ActivityType type) {
		this.type = type;
	}
	
	public List<Thematique> getActivity_thematics(){
		return activity_thematics;
	}
	
	public void setActivity_thematics(List<Thematique> activity_thematics){
		this.activity_thematics = activity_thematics;
	}

	public Integer getMaxParticipants() {
		return maxParticipants;
	}

	public void setMaxParticipants(Integer maxParticipants) {
		this.maxParticipants = maxParticipants;
	}
	
	public Integer getMinAge() {
		return minAge;
	}

	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

	public Integer getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}
	
	public EntrantType getEntrantType(){
		return entrantType;
	}
	
	public void setEntrantType(EntrantType entrantType){
		this.entrantType = entrantType;
	}
}
