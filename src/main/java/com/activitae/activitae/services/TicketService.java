package com.activitae.activitae.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.activitae.activitae.entities.CustomUserDetails;
import com.activitae.activitae.entities.Message;
import com.activitae.activitae.entities.Ticket;
import com.activitae.activitae.entities.User;
import com.activitae.activitae.entities.Chat;
import com.activitae.activitae.repositories.ChatRepository;
import com.activitae.activitae.repositories.TicketRepository;
import com.activitae.activitae.repositories.UserRepository;
import com.activitae.activitae.requests.CreateTicketRequest;
import com.activitae.activitae.requests.chat.SendMessageRequest;

@Service
public class TicketService {
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Ticket createTicket(CreateTicketRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userPrincipal = (CustomUserDetails) auth.getPrincipal();
		User user = userRepository.findById(userPrincipal.getId()).get();
		Ticket ticket = new Ticket();
		ticket.setRequest(request.getRequest());
		ticket.setTitle(request.getTitle());
		ticket.setUser(user);
		ticket.setDate(Date.from(Instant.now()));
		return ticketRepository.save(ticket);
	}
	
	public Ticket getTicket(Long ticket_id) {
		return ticketRepository.findById(ticket_id).get();
	}
	
}
