package com.activitae.activitae.requests;

import java.math.BigDecimal;
import java.util.Date;

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
}
