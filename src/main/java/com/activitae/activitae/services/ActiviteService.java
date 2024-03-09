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
import com.activitae.activitae.requests.user.get.GetUserResponse;
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
	private UserService userService;
	
	@Autowired
	private ActivityRegistrationService activityRegistrationService;
	
	@Autowired
	private ActivityReportService activityReportService;
	
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
		activite.setActivity_thematics(request.getActivity_thematics());
		activite.setMaxAge(request.getMaxAge());
		activite.setMinAge(request.getMinAge());
		activite.setEntrantType(request.getEntrantType());
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
		
		List<GetUserResponse> users = userService.getAll();
		for(int i=0;i<users.size();i++) {
			if(users.get(i).getSeen_activities()!=null) {
				for(int j=0;j<users.get(i).getSeen_activities().size();j++) {
					if (users.get(i).getSeen_activities().get(j).getId() == idActivite)
						System.out.println(users.get(i).getSeen_activities().get(j).getId());
						userService.deleteSeenActivityByUserId(activite,users.get(i).getId());
				}
			}
			if(users.get(i).getFavorites()!=null) {
				for(int j=0;j<users.get(i).getFavorites().size();j++) {
					if (users.get(i).getFavorites().get(j).getId() == idActivite)
						userService.deleteFavoriteByUserId(activite,users.get(i).getId());
				}
			}
		}
		activityReportService.deleteReports(activite);
		activityRegistrationService.deleteRegistrations(activite);
		activiteRepository.delete(activite);
	}

	public List<GetActivityResponse> getActivities() {
		List<GetActivityResponse> activities = new ArrayList<GetActivityResponse>();
		for (Activite a : activiteRepository.findAll()) 
			activities.add(new GetActivityResponse(a, activityRegistrationRepository.findByActivity(a).size()));
		return activities;
	}

	public Activite setActivite(PatchActiviteRequest patchActiviteRequest) {

		Activite activite = activiteRepository.findById(patchActiviteRequest.getId()).get();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userPrincipal = (CustomUserDetails) auth.getPrincipal();

		if (userPrincipal.getId() != activite.getUser().getId()) {
			throw new AccessDeniedException("Vous n'êtes pas autorisé à modifier cette activité");
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
		if (patchActiviteRequest.getFields().contains(ActiviteFields.maxAge)) {
			activite.setMaxAge(patchActiviteRequest.getMaxAge());
		}
		if (patchActiviteRequest.getFields().contains(ActiviteFields.minAge)) {
			activite.setMinAge(patchActiviteRequest.getMinAge());
		}
		if (patchActiviteRequest.getFields().contains(ActiviteFields.maxParticipants)) {
			activite.setMaxParticipants(patchActiviteRequest.getMaxParticipants());
		}
		if (patchActiviteRequest.getFields().contains(ActiviteFields.entrantType)) {
			activite.setEntrantType(patchActiviteRequest.getEntrantType());
		}

		return activiteRepository.save(activite);

	}

	public GetActivityResponse getActivity(Long id) {
		Optional<Activite> activity = activiteRepository.findById(id);
		if(activity.isEmpty())return null;
		Activite a = activity.orElseThrow();
		return new GetActivityResponse(a, activityRegistrationRepository.findByActivity(a).size());

	}
	
	public Activite get(Long id) {
		Optional<Activite> activity = activiteRepository.findById(id);
		return activity.orElseThrow();
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
			activities.add(new GetActivityResponse(a,activityRegistrationRepository.findByActivity(a).size()));
		}
		return activities;
	}
}
