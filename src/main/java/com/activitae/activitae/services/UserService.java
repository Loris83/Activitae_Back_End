package com.activitae.activitae.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.activitae.activitae.entities.CustomUserDetails;
import com.activitae.activitae.entities.Role;
import com.activitae.activitae.entities.User;
import com.activitae.activitae.repositories.UserRepository;
import com.activitae.activitae.requests.RegistrationRequest;
import com.activitae.activitae.requests.user.PatchUserRequest;
import com.activitae.activitae.requests.user.UserFields;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(RegistrationRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent())
        	throw new RuntimeException("Email déjà utilisé.");

        String hashedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(hashedPassword);
        user.setDate(request.getBirthdate());
        user.setSiret(request.getSiret());
        List<Role> roles = new ArrayList<Role>();
        if(request.getRole() == Role.ROLE_PARTICIPANT || request.getRole() == Role.ROLE_ORGANISATEUR)
        	roles.add(request.getRole());
        else
        	roles.add(Role.ROLE_PARTICIPANT);
        user.setRoles(roles);

        return userRepository.save(user);
    }
    
    public User getSelf() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userPrincipal = (CustomUserDetails)auth.getPrincipal();
		User user = userRepository.findById(userPrincipal.getId()).get();
		return user;
    }
    
    public User setUser(PatchUserRequest patchUserRequest) {
    	User user = userRepository.findById(patchUserRequest.getId()).get();
    	if(patchUserRequest.getFields().contains(UserFields.email)){
    		user.setEmail(patchUserRequest.getEmail());
    	}
    	if(patchUserRequest.getFields().contains(UserFields.password)){
    		String hashedPassword = passwordEncoder.encode(patchUserRequest.getPassword());
    		user.setPassword(hashedPassword);
    	}
    	if(patchUserRequest.getFields().contains(UserFields.username)){
    		user.setUsername(patchUserRequest.getUsername());
    	}
    	if(patchUserRequest.getFields().contains(UserFields.image_url)){
    		user.setImage_url(patchUserRequest.getImage_url());
    	}
    	if(patchUserRequest.getFields().contains(UserFields.birthdate)){
    		user.setDate(patchUserRequest.getBirthdate());
    	}
    	if(patchUserRequest.getFields().contains(UserFields.siret)){
    		user.setSiret(patchUserRequest.getSiret());
    	}
    	return userRepository.save(user);
    }
}
