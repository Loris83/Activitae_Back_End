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
import com.activitae.activitae.entities.Chat;
import com.activitae.activitae.entities.ActivityRegistration;
import com.activitae.activitae.entities.User;
import com.activitae.activitae.filters.ActivityFilter;
import com.activitae.activitae.filters.ActivityRegistrationFilter;
import com.activitae.activitae.entities.CustomUserDetails;
import com.activitae.activitae.entities.Message;
import com.activitae.activitae.repositories.ActiviteRepository;
import com.activitae.activitae.repositories.ChatRepository;
import com.activitae.activitae.repositories.ActivityRegistrationRepository;
import com.activitae.activitae.repositories.UserRepository;
import com.activitae.activitae.requests.activity.ActiviteFields;
import com.activitae.activitae.requests.activity.CreateActiviteRequest;
import com.activitae.activitae.requests.activity.GetActivityRequest;
import com.activitae.activitae.requests.activity.GetActivityResponse;
import com.activitae.activitae.requests.activity.PatchActiviteRequest;
import com.activitae.activitae.utils.JwtUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ActiviteService {

	@Autowired
	private ActiviteRepository activiteRepository;
	
	@Autowired
	private ActivityRegistrationRepository activityRegistrationRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ChatRepository chatRepository;
	
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
		activite.setPlace_type(request.getPlace_type());
		activite.setType(request.getType());
		activite.setMaxParticipants(request.getMaxParticipants());
		activite.setActivityThematics(request.getActivityThematics());
		activite.setUser(user);
		Chat chat = new Chat();
		chat.setMessages(new ArrayList<Message>());
		chat.setActivity(activite);
		
		chat = chatRepository.save(chat);
		activite.setChat(chat);
		
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
		
		if (patchActiviteRequest.getFields().contains(ActiviteFields.image_url)) {
			activite.setImage_url(patchActiviteRequest.getImage_url());
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
			activite.setPlace_type(patchActiviteRequest.getPlace_type());
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
	
	public int getActiviteByDate(GetActivityResponse activity) {
		Instant instant = activity.getDate().toInstant();
		LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
			if(LocalDateTime.now().isAfter(dateTime))
				return 0; //Retourne 0 si l'activité est dans le passé
		return 1; // Retourne 1 si l'activité est dans le futur
	}
	
	public List<GetActivityResponse> getCreatedActivities(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userPrincipal = (CustomUserDetails) auth.getPrincipal();
		List<GetActivityResponse> activities = getActivities();
		List<GetActivityResponse> createdActivities = new ArrayList<GetActivityResponse>();
		for(int i=0;i<activities.size();i++) {
			if(activities.get(i).getUser().getId().equals(userPrincipal.getId()))
				createdActivities.add(activities.get(i));
		}
		return createdActivities;	
	}
	
	public List<GetActivityResponse> getActivities(GetActivityRequest request) {
		List<Activite> filtered_activities;
		if(request.getActivityFilterMode()!=null) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			CustomUserDetails userPrincipal = (CustomUserDetails) auth.getPrincipal();
			User user = userRepository.findById(userPrincipal.getId()).get();
			switch(request.getActivityFilterMode()) {
				case REGISTERED:
					filtered_activities = new ArrayList<Activite>();
					for(ActivityRegistration a : activityRegistrationRepository.findAll(ActivityRegistrationFilter.filterRegistered(request, user.getId())))
						filtered_activities.add(a.getActivity());
					break;
				case FAVORITES:
					filtered_activities = activiteRepository.findAll(ActivityFilter.filterFavorite(request, user));
					break;
				
				case HISTORY:
					filtered_activities = activiteRepository.findAll(ActivityFilter.filterHistory(request, user));
					break;
				case OWNED:
					filtered_activities = activiteRepository.findAll(ActivityFilter.filterOwned(request, user.getId()));
					break;
				default:
					filtered_activities = activiteRepository.findAll(ActivityFilter.filter(request));
			}
		}else {
			filtered_activities = activiteRepository.findAll(ActivityFilter.filter(request));
		}
		List<GetActivityResponse> activities = new ArrayList<GetActivityResponse>();
		for (Activite a : filtered_activities) {
			activities.add(new GetActivityResponse(a));
		}
		return activities;
	}
}
