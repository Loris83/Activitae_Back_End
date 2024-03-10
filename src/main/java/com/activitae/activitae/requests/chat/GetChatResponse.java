package com.activitae.activitae.requests.chat;

import java.util.ArrayList;
import java.util.List;

import com.activitae.activitae.entities.Chat;
import com.activitae.activitae.entities.Message;


public class GetChatResponse {
    private Long id;
    private List<GetMessageResponse> messages;
	private Long activity;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<GetMessageResponse> getMessages() {
		return messages;
	}
	public void setMessages(List<GetMessageResponse> messages) {
		this.messages = messages;
	}
	public Long getActivity() {
		return activity;
	}
	public void setActivity(Long activity) {
		this.activity = activity;
	} 
	
	public GetChatResponse(Chat chat) {
		this.id = chat.getId();
		this.activity = chat.getActivity().getId();
		this.messages = new ArrayList<GetMessageResponse>();
		for(Message message : chat.getMessages()) {
			this.messages.add(new GetMessageResponse(message));
		}
	}
	
}
