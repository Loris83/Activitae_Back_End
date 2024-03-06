package com.activitae.activitae.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.activitae.activitae.entities.Chat;
import com.activitae.activitae.entities.Message;
import com.activitae.activitae.entities.Ticket;
import com.activitae.activitae.requests.CreateTicketRequest;
import com.activitae.activitae.requests.chat.SendMessageRequest;
import com.activitae.activitae.services.ChatService;
import com.activitae.activitae.services.TicketService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ticket")
public class TicketController {
	@Autowired
	private TicketService ticketService;
	
	@GetMapping("/get/{id}")
	public Ticket getTicket(@PathVariable Long id) {
		return ticketService.getTicket(id);
	}
	
	@PostMapping("/create")
	public Ticket createTicket(@RequestBody CreateTicketRequest request) {
		return ticketService.createTicket(request);
	}
	

}
