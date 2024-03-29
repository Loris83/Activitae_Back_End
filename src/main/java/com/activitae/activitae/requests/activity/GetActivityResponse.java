package com.activitae.activitae.requests.activity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.activitae.activitae.entities.Activite;
import com.activitae.activitae.entities.ActivityPlaceType;
import com.activitae.activitae.entities.ActivityType;
import com.activitae.activitae.entities.Thematique;
import com.activitae.activitae.entities.Message;
import com.activitae.activitae.requests.chat.GetMessageResponse;
import com.activitae.activitae.requests.user.get.GetUserResponse;



public class GetActivityResponse {
    private Long id;
    private String titre;
    private Date date;
    private String address;
    private BigDecimal price;
    private String descriptif;
    private String info_comp;
    private String site;
    private Long chat_id;
    private String image_url;
    ActivityPlaceType place_type;
    ActivityType type;
    List<Thematique> activity_thematics;
	private GetUserResponse user;
	private Integer maxParticipants;
	private Integer nbParticipants;
	
	private GetMessageResponse lastMessage;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getDescriptif() {
		return descriptif;
	}
	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}
	public String getInfo_comp() {
		return info_comp;
	}
	public void setInfo_comp(String info_comp) {
		this.info_comp = info_comp;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public GetUserResponse getUser() {
		return user;
	}
	public void setUser(GetUserResponse user) {
		this.user = user;
	}
	
	public List<Thematique> getActivity_thematics() {
		return activity_thematics;
	}

	public void setActivity_thematics(List<Thematique> activity_thematics) {
		this.activity_thematics = activity_thematics;
	}
	
	public GetActivityResponse() {
		
	}
	
	public GetActivityResponse(Activite activite, Integer nbParticipants) {
		if(activite != null) {
			setId(activite.getId());
			setTitre(activite.getTitre());
			setDate(activite.getDate());
			setAddress(activite.getAddress());
			setPrice(activite.getPrice());
			setDescriptif(activite.getDescriptif());
			setInfo_comp(activite.getInfo_comp());
			setSite(activite.getSite());
			setUser(new GetUserResponse(activite.getUser()));
			setChat_id(activite.getChat().getId());
			setImage_url(activite.getImage_url());
			setMaxParticipants(activite.getMaxParticipants());
			setNbParticipants(nbParticipants);
			setActivity_thematics(activite.getActivity_thematics());
			activite.getChat().getMessages().stream().max((Message a, Message b) -> {return a.getDate().compareTo(b.getDate());}).ifPresentOrElse((Message message) -> {setLastMessage(new GetMessageResponse(message));}, ()->{setLastMessage(null);});
			
		}
	}
	public Long getChat_id() {
		return chat_id;
	}
	public void setChat_id(Long chat_id) {
		this.chat_id = chat_id;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public Integer getMaxParticipants() {
		return maxParticipants;
	}
	public void setMaxParticipants(Integer maxParticipants) {
		this.maxParticipants = maxParticipants;
	}
	public Integer getNbParticipants() {
		return nbParticipants;
	}
	public void setNbParticipants(Integer nbParticipants) {
		this.nbParticipants = nbParticipants;
	}
	public GetMessageResponse getLastMessage() {
		return lastMessage;
	}
	public void setLastMessage(GetMessageResponse lastMessage) {
		this.lastMessage = lastMessage;
	}
}
