package com.activitae.activitae.entities;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
	  ROLE_ADMIN, ROLE_PARTICIPANT, ROLE_ORGANISATEUR;

	  public String getAuthority() {
	    return name();
	  }

	}