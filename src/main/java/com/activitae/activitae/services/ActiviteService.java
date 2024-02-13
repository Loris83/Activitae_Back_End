package com.activitae.activitae.services;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.activitae.activitae.entities.Activite;
import com.activitae.activitae.entities.User;
import com.activitae.activitae.entities.CustomUserDetails;
import com.activitae.activitae.repositories.ActiviteRepository;
import com.activitae.activitae.repositories.UserRepository;
import com.activitae.activitae.requests.activity.CreateActiviteRequest;
import com.activitae.activitae.requests.activity.GetActivityResponse;
import com.activitae.activitae.utils.JwtUtils;

@Service
public class ActiviteService {

	@Autowired
	private ActiviteRepository activiteRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	public Activite createActivite(CreateActiviteRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userPrincipal = (CustomUserDetails)auth.getPrincipal();
		User user = userRepository.findById(userPrincipal.getId()).get();
		Activite activite = new Activite();
		activite.setAddress(request.getAddress());
		activite.setDate(request.getDate());
		activite.setDescriptif(request.getDescriptif());
		activite.setInfo_comp(request.getInfo_comp());
		activite.setPrice(request.getPrice());
		activite.setSite(request.getSite());
		activite.setTitre(request.getTitre());
		activite.setType(request.getType());
		activite.setPlaceType(request.getPlaceType());
		activite.setUser(user);
		
		return activiteRepository.save(activite);
	}
	
	public List<GetActivityResponse> getActivities(){
		List<GetActivityResponse> activities = new ArrayList<GetActivityResponse>();
		for(Activite a : activiteRepository.findAll()) {
			activities.add(new GetActivityResponse(a));
		}
		return activities;
	}
	
	public Activite getActivity(Long id) {
		Optional<Activite> activity = activiteRepository.findById(id);
		return activity.orElseThrow();
	}
}
