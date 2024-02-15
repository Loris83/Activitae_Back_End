package com.activitae.activitae.requests.user;

import java.util.Date;
import java.util.List;

import com.activitae.activitae.entities.Role;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;

public enum UserFields {
	email, 
	password, 
	username, 
	birthdate, 
	image_url, 
	siret, 
	roles
}
