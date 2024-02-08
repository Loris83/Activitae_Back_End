package com.activitae.activitae.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.activitae.activitae.entities.Role;
import com.activitae.activitae.entities.User;
import com.activitae.activitae.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(String email, String password) {
        // Vérifiez si l'utilisateur existe déjà
        if(userRepository.findByEmail(email).isPresent())
        	throw new RuntimeException("Email déjà utilisé.");

        // Hash du mot de passe avant de le stocker en base de données
        String hashedPassword = passwordEncoder.encode(password);

        // Création d'un nouvel utilisateur
        User user = new User();
        user.setEmail(email);
        user.setPassword(hashedPassword);
        user.setPseudo("anonyme");
        List<Role> roles = new ArrayList<Role>();
        roles.add(Role.ROLE_PARTICIPANT);
        user.setRoles(roles);

        // Enregistrement de l'utilisateur dans la base de données
        return userRepository.save(user);
    }
}
