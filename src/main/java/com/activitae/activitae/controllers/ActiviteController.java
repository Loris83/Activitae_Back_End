package com.activitae.activitae.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.activitae.activitae.entities.Activite;
import com.activitae.activitae.entities.CustomUserDetails;
import com.activitae.activitae.entities.User;
import com.activitae.activitae.requests.activity.CreateActiviteRequest;
import com.activitae.activitae.requests.activity.GetActivityRequest;
import com.activitae.activitae.requests.activity.GetActivityResponse;
import com.activitae.activitae.requests.activity.PatchActiviteRequest;
import com.activitae.activitae.requests.user.PatchUserRequest;
import com.activitae.activitae.services.ActiviteService;
import com.activitae.activitae.services.UserService;
import com.activitae.activitae.utils.JwtUtils;
import com.activitae.activitae.utils.UrlChecking;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/activities")
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
   public List<GetActivityResponse> getActivities(@ModelAttribute GetActivityRequest filter){
	   System.out.println(filter);
	   if(filter==null)
		   filter = new GetActivityRequest();
	   return activiteService.getActivities(filter);
   }
   
   @GetMapping("/get/{id}")
   public GetActivityResponse getActivity(@PathVariable Long id){
	   return activiteService.getActivity(id);
   }

   
   
   @DeleteMapping("/delete/{id}")
   public void deleteActivity (@PathVariable Long id){
       activiteService.deleteActivite(id);
   }
   
   @PatchMapping("/update/{id}")
   public Activite setSelf(HttpServletRequest hsrequest, @RequestBody PatchActiviteRequest patchActiviteRequest, @PathVariable Long id) {
	   if(patchActiviteRequest.getImage_url()!=null&&patchActiviteRequest.getImage_url()!=""&&!UrlChecking.checkUrl(hsrequest, patchActiviteRequest.getImage_url())) {
	   		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'url de l'image n'est pas valide");
	   	}
		
		patchActiviteRequest.setId(id);
   	return activiteService.setActivite(patchActiviteRequest);
   }
}
