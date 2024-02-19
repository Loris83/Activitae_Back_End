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
import com.activitae.activitae.entities.ActivityRegistration;
import com.activitae.activitae.entities.CustomUserDetails;
import com.activitae.activitae.repositories.ActivityRegistrationRepository;
import com.activitae.activitae.repositories.ActiviteRepository;
import com.activitae.activitae.repositories.UserRepository;
import com.activitae.activitae.requests.activity.ActiviteFields;
import com.activitae.activitae.requests.activity.CreateActiviteRequest;
import com.activitae.activitae.requests.activity.GetActivityResponse;
import com.activitae.activitae.requests.activity.PatchActiviteRequest;
import com.activitae.activitae.utils.JwtUtils;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ActivityRegistrationService {

	@Autowired
	private ActivityRegistrationRepository activiteRegistrationRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private UserService userService;

	@Autowired
	private JwtUtils jwtUtils;

	public ActivityRegistration registerActivity(Activite activity) {
		ActivityRegistration registration = new ActivityRegistration();
		registration.setUser(userService.getSelf());
		registration.setActivity(activity);
		return activiteRegistrationRepository.save(registration);
		
	}
}
