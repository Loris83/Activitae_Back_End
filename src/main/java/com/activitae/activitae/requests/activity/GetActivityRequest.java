package com.activitae.activitae.requests.activity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.activitae.activitae.entities.ActivityPlaceType;
import com.activitae.activitae.entities.ActivityType;
import com.activitae.activitae.entities.EntrantType;
import com.activitae.activitae.entities.Thematique;

import jakarta.persistence.Column;

public class GetActivityRequest {
	
	public enum ActivityFilterMode{
		REGISTERED,
		FAVORITES,
		HISTORY,
		OWNED,
		ALL
	}
	
	private Long id;
	
	private ActivityFilterMode activityFilterMode;
	
	private Date minDate;
	
	private Date maxDate;
	
	private String thematic;
	
	private String search;
	
	private BigDecimal minPrice;
	
	private BigDecimal maxPrice;
	
	private Integer maxParticipants;
	
	private EntrantType entrantType;
	
	private Integer minAge;
	
	private Integer maxAge;
	
	private ActivityType type;
	
	private ActivityPlaceType place_type;

	public ActivityFilterMode getActivityFilterMode() {
		return activityFilterMode;
	}

	public void setActivityFilterMode(ActivityFilterMode activityFilterMode) {
		this.activityFilterMode = activityFilterMode;
	}

	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

	public Date getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}

	public String getThematic() {
		return thematic;
	}

	public void setThematic(String thematic) {
		this.thematic = thematic;
	}

	public ActivityType getType() {
		return type;
	}

	public void setType(ActivityType type) {
		this.type = type;
	}

	public ActivityPlaceType getPlace_type() {
		return place_type;
	}

	public void setPlace_type(ActivityPlaceType place_type) {
		this.place_type = place_type;
	}

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMaxParticipants() {
		return maxParticipants;
	}

	public void setMaxParticipants(Integer maxParticipants) {
		this.maxParticipants = maxParticipants;
	}

	public EntrantType getEntrantType() {
		return entrantType;
	}

	public void setEntrantType(EntrantType entrantType) {
		this.entrantType = entrantType;
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

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

}
