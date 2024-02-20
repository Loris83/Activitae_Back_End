package com.activitae.activitae.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
	
	@Column(nullable = true)
	@Enumerated(EnumType.ORDINAL)
    ActivityPlaceType place_type;
	
	@Column(nullable = true)
	@Enumerated(EnumType.ORDINAL)
    ActivityType type;
	
	@Column(nullable = true)
    private String image_url;
	
	@JoinColumn(name = "user_id")
	@ManyToOne
	@JsonBackReference
	private User user;
	
	@ManyToMany 
    @JoinTable(name="activity_thematics",joinColumns=@JoinColumn(name="activity_id"), inverseJoinColumns = @JoinColumn(name = "thematic_id"))
    //@JsonManagedReference
    List<Thematique> activity_thematics;
	
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
	
	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public ActivityPlaceType getPlaceType(){
		return place_type;
	}
	
	public void setPlaceType(ActivityPlaceType place_type) {
		this.place_type = place_type;
	}
	
	public ActivityType getType(){
		return type;
	}
	
	public void setType(ActivityType type) {
		this.type = type;
	}
	
	public List<Thematique> getActivityThematics(){
		return activity_thematics;
	}
	
	public void setActivityThematics(List<Thematique> activity_thematics){
		this.activity_thematics = activity_thematics;
	}
	
	
}
