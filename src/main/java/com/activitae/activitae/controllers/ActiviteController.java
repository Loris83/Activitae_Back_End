package com.activitae.activitae.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.activitae.activitae.entities.Activite;
import com.activitae.activitae.requests.activity.CreateActiviteRequest;
import com.activitae.activitae.requests.activity.GetActivityResponse;
import com.activitae.activitae.services.ActiviteService;
import com.activitae.activitae.services.UserService;
import com.activitae.activitae.utils.JwtUtils;

@RestController
@RequestMapping("/api/activite")
public class ActiviteController {
	
	@Autowired
	 private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

   @Autowired
   private ActiviteService activiteService;
   
   @PostMapping("/create")
   public Activite createActivity(@RequestBody CreateActiviteRequest request){
	   return activiteService.createActivite(request);
   }
   
   @GetMapping("/get")
   public List<GetActivityResponse> getActivities(){
	   return activiteService.getActivities();
   }
   

}
