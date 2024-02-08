package com.activitae.activitae.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.activitae.activitae.entities.Activite;
import com.activitae.activitae.entities.User;
import com.activitae.activitae.entities.CustomUserDetails;
import com.activitae.activitae.repositories.ActiviteRepository;
import com.activitae.activitae.repositories.UserRepository;
import com.activitae.activitae.requests.CreateActiviteRequest;
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
		activite.setUser(user);
		return activiteRepository.save(activite);
	}
}
