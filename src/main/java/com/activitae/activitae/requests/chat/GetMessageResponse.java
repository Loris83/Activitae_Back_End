package com.activitae.activitae.requests.chat;

import java.util.Date;
import com.activitae.activitae.entities.Message;
import com.activitae.activitae.requests.user.get.GetUserResponse;


public class GetMessageResponse {
	
	private Long id;
	private String content;
    private Long chat;
	private GetUserResponse user;
	private Date date;

	
	
	public GetMessageResponse(Message message) {
		this.id = message.getId();
		this.content = message.getContent();
		this.chat = message.getChat().getId();
		this.date = message.getDate();
		this.user = new GetUserResponse(message.getUser());
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public Long getChat() {
		return chat;
	}



	public void setChat(Long chat) {
		this.chat = chat;
	}



	public GetUserResponse getUser() {
		return user;
	}



	public void setUser(GetUserResponse user) {
		this.user = user;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}
}
