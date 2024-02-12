package com.activitae.activitae.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.activitae.activitae.entities.Role;
import com.activitae.activitae.entities.User;
import com.activitae.activitae.repositories.UserRepository;
import com.activitae.activitae.requests.RegistrationRequest;

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
        user.setPassword(hashedPassword);
        user.setDate(request.getBirthdate());
        user.setSiret(request.getSiret());
        user.setPseudo("anonyme");
        List<Role> roles = new ArrayList<Role>();
        roles.add(Role.ROLE_PARTICIPANT);
        user.setRoles(roles);

        return userRepository.save(user);
    }
}