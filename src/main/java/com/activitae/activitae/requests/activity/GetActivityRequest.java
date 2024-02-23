package com.activitae.activitae.requests.activity;

import java.math.BigDecimal;
import java.util.Date;

import com.activitae.activitae.entities.ActivityPlaceType;
import com.activitae.activitae.entities.ActivityType;
import com.activitae.activitae.entities.Thematique;

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
	
	private BigDecimal minPrice;
	
	private BigDecimal maxPrice;
	
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

}
