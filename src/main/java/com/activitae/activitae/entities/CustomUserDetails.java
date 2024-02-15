package com.activitae.activitae.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    @JsonIgnore
    private List<Activite> favorites;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    
    public CustomUserDetails(User user) {
        this(build(user));
    }

    public CustomUserDetails(Long id, String username, String password, List<Activite> favorites, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.favorites = favorites;
        this.authorities = authorities;
    }

    public CustomUserDetails(CustomUserDetails user) {
    	this.id = user.id;
        this.username = user.username;
        this.password = user.password;
        this.favorites = user.favorites;
        this.authorities = user.authorities;
	}

	public static CustomUserDetails build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getAuthority())
        ).collect(Collectors.toList());

        return new CustomUserDetails(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getFavorites(),
                authorities
        );
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
    
    public List<Activite> getFavorites(){
		return favorites;
	}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
