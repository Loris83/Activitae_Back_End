package com.activitae.activitae.controllers;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
   public List<GetActivityResponse> getActivities(@RequestBody(required=false) GetActivityRequest filter){
	   if(filter==null)
		   filter = new GetActivityRequest();
	   return activiteService.getActivities(filter);
   }

   
   
   @DeleteMapping("/delete/{id}")
   public void deleteActivity (@PathVariable Long id){
       activiteService.deleteActivite(id);
   }
   
   @PatchMapping("/update/{id}")
   public Activite setSelf(@RequestBody PatchActiviteRequest patchActiviteRequest, @PathVariable Long id) {

		
		patchActiviteRequest.setId(id);
   	return activiteService.setActivite(patchActiviteRequest);
   }
   
   
   
   /*@GetMapping("/get-times/{id}")
   public GetActivityResponse getActivityTime(@PathVariable Long id) {
	   return activiteService.getActiviteByDate(activiteService.getActivity(id));
   }*/
   
   @GetMapping("/get-past-created-activities")
   public List<GetActivityResponse> getPastActivities(){
	   List<GetActivityResponse> activities = activiteService.getCreatedActivities();
	   List<GetActivityResponse> pastActivities = new ArrayList<GetActivityResponse>();
	   if(activities.size() != 0) {
		   for(int i=0;i<activities.size();i++) {
			  if(activiteService.getActiviteByDate(activities.get(i)) == 0)
				  pastActivities.add(activities.get(i));
		   }
	   }
	   return pastActivities;
   }
   
   @GetMapping("/get-future-activities")
   public List<GetActivityResponse> getFutureActivities(){
	   List<GetActivityResponse> activities = activiteService.getActivities();
	   List<GetActivityResponse> futureActivities = new ArrayList<GetActivityResponse>();
	   if(activities.size() != 0) {
		   for(int i=0;i<activities.size();i++) {
			  if(activiteService.getActiviteByDate(activities.get(i)) == 1)
				  futureActivities.add(activities.get(i));
		   }
	   }
	   return futureActivities;
   }
   
}
