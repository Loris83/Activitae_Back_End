package com.activitae.activitae.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.activitae.activitae.entities.Activite;
import com.activitae.activitae.entities.ActivityRegistration;
import com.activitae.activitae.entities.CustomUserDetails;
import com.activitae.activitae.entities.User;
import com.activitae.activitae.requests.activity.CreateActiviteRequest;
import com.activitae.activitae.requests.activity.GetActivityResponse;
import com.activitae.activitae.requests.activity.PatchActiviteRequest;
import com.activitae.activitae.requests.user.PatchUserRequest;
import com.activitae.activitae.services.ActiviteService;
import com.activitae.activitae.services.ActivityRegistrationService;
import com.activitae.activitae.services.UserService;
import com.activitae.activitae.utils.JwtUtils;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/registration")
public class ActivityRegistrationController {
	
	@Autowired
	 private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

   @Autowired
   private ActivityRegistrationService activityRegistrationService;
   @Autowired
   private ActiviteService activiteService;
   
   @PostMapping("/register/{id}")
   public ActivityRegistration registerActivity(@PathVariable Long id) {
	   Activite activity = activiteService.getActivity(id);
	   return activityRegistrationService.registerActivity(activity);	
   }
   
   

}
