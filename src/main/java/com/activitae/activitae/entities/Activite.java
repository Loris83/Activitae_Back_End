package com.activitae.activitae.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Activites")
public class Activite {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
    private String titre;
	
	@Column(nullable = false)
    private Date date;
	
	@Column(nullable = false)
    private String address; //chez Jerem
	
	@Column(nullable = true)
    private BigDecimal price;
	
	@Column(nullable = false)
    private String descriptif;
	
	@Column(nullable = true)
    private String info_comp;
	
	@Column(nullable = true)
    private String site;
	
	@ElementCollection(fetch = FetchType.EAGER)
    List<ActivityPlaceType> place_types;
	
	@ElementCollection(fetch = FetchType.EAGER)
    List<ActivityType> types;
	
	@JoinColumn(name = "user_id")
	@ManyToOne
	private User user;
	
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<ActivityPlaceType> getPlaceType(){
		return place_types;
	}
	
	public void setPlaceType(List<ActivityPlaceType> place_types) {
		this.place_types = place_types;
	}
	
	public List<ActivityType> getType(){
		return types;
	}
	
	public void setType(List<ActivityType> types) {
		this.types = types;
	}
	
	
	
}
