package com.activitae.activitae.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.activitae.activitae.services.CustomUserDetailsService;
import com.activitae.activitae.utils.AuthEntryPointJwt;
import com.activitae.activitae.utils.AuthTokenFilter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import org.springframework.security.config.http.SessionCreationPolicy;





@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@EnableAutoConfiguration
public class SecurityConfig {
	
	@Autowired
    private CustomUserDetailsService customUserDetailsService;
	
	 @Autowired
	 private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*http
                .authorizeHttpRequests((authz) -> authz
                                .requestMatchers("/api/users/register").permitAll()
                                .requestMatchers("/api/users/login").permitAll()
                                .anyRequest().authenticated()
                ).logout(t -> t.logoutUrl("/api/users/logout"))
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();*/
        
        
        http.csrf(csrf -> csrf.disable())
        .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> 
        	auth
        	.requestMatchers("/api/users/register").permitAll()
        	.requestMatchers("/api/users/login").permitAll()
        	.requestMatchers("/api/pictures/view/**").permitAll()
        		.anyRequest().authenticated()
        		).logout(t -> t.logoutUrl("/api/users/logout"));
        
        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
	
	/*@Bean
    public AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return auth.build();
    }*/
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	  @Bean
	  public DaoAuthenticationProvider authenticationProvider() {
	      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	       
	      authProvider.setUserDetailsService(customUserDetailsService);
	      authProvider.setPasswordEncoder(passwordEncoder());
	   
	      return authProvider;
	  }

}
