package com.activitae.activitae.entities;

import org.springframework.security.core.GrantedAuthority;

public enum ActivityType implements GrantedAuthority {
	  TYPE_REEL, TYPE_VIRTUEL;

	  public String getAuthority() {
	    return name();
	  }

	}