package com.activitae.activitae.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.activitae.activitae.entities.User;
import com.activitae.activitae.requests.JwtAuthenticationResponse;
import com.activitae.activitae.requests.LoginRequest;
import com.activitae.activitae.requests.RegistrationRequest;
import com.activitae.activitae.services.UserService;
import com.activitae.activitae.utils.JwtUtils;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	 private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;
    


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
        return userService.registerUser(request.getEmail(), request.getPassword());
    }
}
