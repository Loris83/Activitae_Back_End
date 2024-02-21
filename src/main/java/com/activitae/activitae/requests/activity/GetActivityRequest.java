package com.activitae.activitae.requests.activity;

import java.math.BigDecimal;
import java.sql.Date;

import com.activitae.activitae.entities.ActivityPlaceType;
import com.activitae.activitae.entities.ActivityType;
import com.activitae.activitae.entities.Thematique;

public class GetActivityRequest {
	
	public enum ActivityFilterMode{
		FAVORITES,
		HISTORY,
		ALL
	}
	
	private ActivityFilterMode activityFilterMode;
	
	private Date minDate;
	
	private Date maxDate;
	
	private String thematic;
	
	private BigDecimal price;
	
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

}
