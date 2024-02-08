package com.activitae.activitae.entities;

import org.springframework.security.core.GrantedAuthority;

public enum ActivityPlaceType implements GrantedAuthority {
	  PLACE_INTERIEUR, PLACE_EXTERIEUR;

	  public String getAuthority() {
	    return name();
	  }

	}