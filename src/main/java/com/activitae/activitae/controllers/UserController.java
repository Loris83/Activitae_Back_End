package com.activitae.activitae.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.activitae.activitae.entities.Activite;
import com.activitae.activitae.entities.CustomUserDetails;
import com.activitae.activitae.entities.User;
import com.activitae.activitae.requests.JwtAuthenticationResponse;
import com.activitae.activitae.requests.LoginRequest;
import com.activitae.activitae.requests.RegistrationRequest;
import com.activitae.activitae.requests.activity.GetActivityResponse;
import com.activitae.activitae.services.ActiviteService;
import com.activitae.activitae.requests.user.PatchUserRequest;
import com.activitae.activitae.requests.user.get.GetUserResponse;
import com.activitae.activitae.services.UserService;
import com.activitae.activitae.utils.JwtUtils;
import com.activitae.activitae.utils.UrlChecking;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	 private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;
    
    @Autowired
    private ActiviteService activiteService;
    


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    } 

    @PostMapping("/register")
    public User registerUser(@RequestBody RegistrationRequest request) {
        return userService.registerUser(request);
    }
    
    @GetMapping("/get-self")
    public User getSelf() {
        return userService.getSelf();
    }
    

    @GetMapping("/get/{id}")
    public GetUserResponse get(@PathVariable Long id) {
        return userService.get(id);
    }
    
    @GetMapping("/get-favorite")
    public List<Activite> getActivity(){
    	return userService.getFavorite();
    }
    
    @GetMapping("/get-history")
    public List<Activite> getHistory(){
    	return userService.getSeenActivity();
    }
    

    @PostMapping("/add-favorite/{id}")
    public User addFavorite(@PathVariable Long id) {
    	return userService.putFavorite(activiteService.get(id));
    }
    
    @PostMapping("/add-history/{id}")
    public User addHistory(@PathVariable Long id) {
    	return userService.addSeenActivity(activiteService.get(id));
    }
    
    @PostMapping("/delete-favorite/{id}")
    public User deleteFavorite(@PathVariable Long id) {
    	return userService.deleteFavorite(activiteService.get(id));
    }
    

    @PatchMapping("/set-self")
    public User setSelf(HttpServletRequest hsrequest, @RequestBody PatchUserRequest patchUserRequest) {
    	if(patchUserRequest.getImage_url()!=null&&patchUserRequest.getImage_url()!=""&&!UrlChecking.checkUrl(hsrequest, patchUserRequest.getImage_url())) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'url de l'image n'est pas valide");
    	}
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userPrincipal = (CustomUserDetails)auth.getPrincipal();
		patchUserRequest.setId(userPrincipal.getId());
    	return userService.setUser(patchUserRequest);
    }
    
    @DeleteMapping("/delete-self/{id}")
    public void deleteSelf(@PathVariable Long id){
    	
    	userService.deleteUser(id);
    	
    }
}
