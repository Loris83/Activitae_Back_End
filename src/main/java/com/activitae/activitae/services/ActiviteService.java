package com.activitae.activitae.services;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.activitae.activitae.entities.Activite;
import com.activitae.activitae.entities.User;
import com.activitae.activitae.entities.CustomUserDetails;
import com.activitae.activitae.repositories.ActiviteRepository;
import com.activitae.activitae.repositories.UserRepository;
import com.activitae.activitae.requests.activity.ActiviteFields;
import com.activitae.activitae.requests.activity.CreateActiviteRequest;
import com.activitae.activitae.requests.activity.GetActivityResponse;
import com.activitae.activitae.requests.activity.PatchActiviteRequest;
import com.activitae.activitae.utils.JwtUtils;

import jakarta.persistence.EntityNotFoundException;

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
		CustomUserDetails userPrincipal = (CustomUserDetails) auth.getPrincipal();
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

	public void deleteActivite(Long idActivite) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userPrincipal = (CustomUserDetails) auth.getPrincipal();

		Activite activite = activiteRepository.findById(idActivite)
				.orElseThrow(() -> new EntityNotFoundException("Activité non trouvée"));

		if (!activite.getUser().getId().equals(userPrincipal.getId())) {
			throw new AccessDeniedException("Vous n'êtes pas autorisé à supprimer cette activité");
		}

		activiteRepository.delete(activite);
	}

	public List<GetActivityResponse> getActivities() {
		List<GetActivityResponse> activities = new ArrayList<GetActivityResponse>();
		for (Activite a : activiteRepository.findAll()) {
			activities.add(new GetActivityResponse(a));
		}
		return activities;
	}

	public Activite setActivite(PatchActiviteRequest patchActiviteRequest) {

		Activite activite = activiteRepository.findById(patchActiviteRequest.getId()).get();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userPrincipal = (CustomUserDetails) auth.getPrincipal();

		if (userPrincipal.getId() != activite.getUser().getId()) {
			throw new AccessDeniedException("Vous n'êtes pas autorisé à supprimer cette activité");
		}

		if (patchActiviteRequest.getFields().contains(ActiviteFields.titre)) {
			activite.setTitre(patchActiviteRequest.getTitre());
		}

		if (patchActiviteRequest.getFields().contains(ActiviteFields.date)) {
			activite.setDate(patchActiviteRequest.getDate());
		}

		if (patchActiviteRequest.getFields().contains(ActiviteFields.address)) {
			activite.setAddress(patchActiviteRequest.getAddress());
		}

		if (patchActiviteRequest.getFields().contains(ActiviteFields.price)) {
			activite.setPrice(patchActiviteRequest.getPrice());
		}

		if (patchActiviteRequest.getFields().contains(ActiviteFields.descriptif)) {
			activite.setDescriptif(patchActiviteRequest.getDescriptif());
		}

		if (patchActiviteRequest.getFields().contains(ActiviteFields.info_comp)) {
			activite.setInfo_comp(patchActiviteRequest.getInfo_comp());
		}

		if (patchActiviteRequest.getFields().contains(ActiviteFields.site)) {
			activite.setSite(patchActiviteRequest.getSite());
		}

		if (patchActiviteRequest.getFields().contains(ActiviteFields.place_type)) {
			activite.setPlaceType(patchActiviteRequest.getPlace_type());
		}

		if (patchActiviteRequest.getFields().contains(ActiviteFields.type)) {
			activite.setType(patchActiviteRequest.getType());
		}

		if (patchActiviteRequest.getFields().contains(ActiviteFields.user)) {
			activite.setUser(patchActiviteRequest.getUser());
		}

		return activiteRepository.save(activite);

	}

	public Activite getActivity(Long id) {
		Optional<Activite> activity = activiteRepository.findById(id);
		return activity.orElseThrow();

	}
}
