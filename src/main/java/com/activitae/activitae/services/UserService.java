package com.activitae.activitae.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.activitae.activitae.entities.Activite;
import com.activitae.activitae.entities.CustomUserDetails;
import com.activitae.activitae.entities.Role;
import com.activitae.activitae.entities.User;
import com.activitae.activitae.repositories.UserRepository;
import com.activitae.activitae.requests.RegistrationRequest;
import com.activitae.activitae.requests.user.PatchUserRequest;
import com.activitae.activitae.requests.user.UserFields;
import com.activitae.activitae.requests.user.get.GetUserResponse;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User registerUser(RegistrationRequest request) {
		if (userRepository.findByEmail(request.getEmail()).isPresent())
			throw new RuntimeException("Email déjà utilisé.");

		String hashedPassword = passwordEncoder.encode(request.getPassword());
		User user = new User();
		user.setEmail(request.getEmail());
		user.setUsername(request.getUsername());
		user.setPassword(hashedPassword);
		user.setDate(request.getBirthdate());
		user.setSiret(request.getSiret());
		List<Role> roles = new ArrayList<Role>();
		if (request.getRole() == Role.ROLE_PARTICIPANT || request.getRole() == Role.ROLE_ORGANISATEUR)
			roles.add(request.getRole());
		else
			roles.add(Role.ROLE_PARTICIPANT);
		user.setRoles(roles);
		List<Activite> favorites = new ArrayList<Activite>();
		user.setFavorites(favorites);
		List<Activite> seen_activities = new ArrayList<Activite>();
		user.setSeen_activies(seen_activities);
		return userRepository.save(user);
	}

	public User getSelf() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userPrincipal = (CustomUserDetails) auth.getPrincipal();
		User user = userRepository.findById(userPrincipal.getId()).get();
		return user;
	}
	
	public GetUserResponse get(Long id) {
		User user = userRepository.findById(id).get();
		return new GetUserResponse(user);
	}
	
	public List<GetUserResponse> getAll() {
		List<GetUserResponse> users = new ArrayList<GetUserResponse>();
		for (User u : userRepository.findAll())
			users.add(new GetUserResponse(u));
		return users;
	}

	public User putFavorite(Activite activity) {
		User user = getSelf();
		List<Activite> favorites = user.getFavorites();
		if (favorites == null)
			favorites = new ArrayList<Activite>();
		favorites.add(activity);
		user.setFavorites(favorites);
		return userRepository.save(user);
	}

	public User deleteFavorite(Activite activity) {
		User user = getSelf();
		List<Activite> favorites = user.getFavorites();
		if (favorites == null)
			return userRepository.save(user);
		favorites.remove(activity);
		user.setFavorites(favorites);
		return userRepository.save(user);
	}
	
	public User deleteFavoriteByUserId(Activite activity, Long userId) {
		User user = userRepository.findById(userId).get();
		List<Activite> favorites = user.getFavorites();
		if (favorites == null)
			return userRepository.save(user);
		favorites.remove(activity);
		user.setFavorites(favorites);
		return userRepository.save(user);
	}

	public User addSeenActivity(Activite activity) {
		User user = getSelf();
		List<Activite> seen_activities = user.getSeen_activities();
		if (seen_activities.size() == 10) {
			seen_activities.remove(0);
			seen_activities.add(activity);
			return userRepository.save(user);
		}
		if (seen_activities.size() != 0) {
			for (int i = 0; i < seen_activities.size(); i++) {
				if (seen_activities.get(i).getId() == activity.getId())
					return userRepository.save(user);
			}
		}
		seen_activities.add(activity);
		user.setSeen_activies(seen_activities);
		return userRepository.save(user);
	}
	
	public User deleteSeenActivity(Activite activity) {
		User user = getSelf();
		List<Activite> history = user.getSeen_activities();
		if (history == null)
			return userRepository.save(user);
		history.remove(activity);
		user.setSeen_activies(history);
		return userRepository.save(user);
	}
	
	public User deleteSeenActivityByUserId(Activite activity, Long userId) {
		User user = userRepository.findById(userId).get();
		List<Activite> history = user.getSeen_activities();
		if (history == null)
			return userRepository.save(user);
		history.remove(activity);
		user.setSeen_activies(history);
		return userRepository.save(user);
	}

	public User setUser(PatchUserRequest patchUserRequest) {
		User user = userRepository.findById(patchUserRequest.getId()).get();
		if (patchUserRequest.getFields().contains(UserFields.email)) {
			user.setEmail(patchUserRequest.getEmail());
		}
		if (patchUserRequest.getFields().contains(UserFields.password)) {
			String hashedPassword = passwordEncoder.encode(patchUserRequest.getPassword());
			user.setPassword(hashedPassword);
		}
		if (patchUserRequest.getFields().contains(UserFields.username)) {
			user.setUsername(patchUserRequest.getUsername());
		}
		if (patchUserRequest.getFields().contains(UserFields.image_url)) {
			user.setImage_url(patchUserRequest.getImage_url());
		}
		if (patchUserRequest.getFields().contains(UserFields.birthdate)) {
			user.setDate(patchUserRequest.getBirthdate());
		}
		if (patchUserRequest.getFields().contains(UserFields.siret)) {
			user.setSiret(patchUserRequest.getSiret());
		}
		return userRepository.save(user);
	}

	public List<Activite> getFavorite() {
		User user = getSelf();
		return user.getFavorites();
	}
	
	public List<Activite> getSeenActivity(){
		User user = getSelf();
		return user.getSeen_activities();
	}

	public void deleteUser(Long idUser) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userPrincipal = (CustomUserDetails) auth.getPrincipal();

		User user = userRepository.findById(idUser).orElseThrow(() -> new EntityNotFoundException("User non trouvé"));

		userRepository.delete(user);
	}
}
