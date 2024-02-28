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
import com.activitae.activitae.entities.User;
import com.activitae.activitae.entities.Chat;
import com.activitae.activitae.repositories.ChatRepository;
import com.activitae.activitae.repositories.UserRepository;
import com.activitae.activitae.requests.chat.SendMessageRequest;

@Service
public class ChatService {
	@Autowired
	private ChatRepository chatRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Chat sendMessage(SendMessageRequest request, Long chat_id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userPrincipal = (CustomUserDetails) auth.getPrincipal();
		User user = userRepository.findById(userPrincipal.getId()).get();
		Message message = new Message();
		message.setContent(request.getContent());
		message.setUser(user);
		message.setDate(Date.from(Instant.now()));
		Chat chat = chatRepository.findById(chat_id).get();
		List<Message> messages = chat.getMessages();
		message.setChat(chat);
		messages.add(message);
		chat.setMessages(messages);
		return chatRepository.save(chat);
	}
	
	public Chat getChat(Long chat_id) {
		return chatRepository.findById(chat_id).get();
	}
	
}
