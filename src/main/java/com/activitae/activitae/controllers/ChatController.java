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
import com.activitae.activitae.requests.chat.GetChatResponse;
import com.activitae.activitae.requests.chat.SendMessageRequest;
import com.activitae.activitae.services.ChatService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/chat")
public class ChatController {
	@Autowired
	private ChatService chatService;
	
	@GetMapping("/get/{id}")
	GetChatResponse getChat(@PathVariable Long id) {
		return chatService.getChat(id);
	}
	
	@PostMapping("/send/{id}")
	Chat sendMessage(@PathVariable Long id, @RequestBody SendMessageRequest sendMessageRequest) {
		return chatService.sendMessage(sendMessageRequest, id);
	}

}
