package com.activitae.activitae.entities;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;


//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "ActivityRegistration", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "activity_id"}))
public class ActivityRegistration {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@JoinColumn(name = "user_id")
	@ManyToOne
	@JsonBackReference
	private User user;

	@JoinColumn(name = "activity_id")
	@ManyToOne
	@JsonBackReference
	private Activite activity;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Activite getActivity() {
		return activity;
	}

	public void setActivity(Activite activity) {
		this.activity = activity;
	}
	
}
