package com.activitae.activitae.services;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;

import com.activitae.activitae.entities.Avatar;
import com.activitae.activitae.entities.Picture;
import com.activitae.activitae.repositories.AvatarRepository;
import com.activitae.activitae.repositories.PictureRepository;
import com.activitae.activitae.requests.UploadPictureRequest;
import com.activitae.activitae.requests.picture.SetAvatarRequest;

@Service
public class PictureService {
	
	@Autowired
	private PictureRepository pictureRepository;
	
	@Autowired
	private AvatarRepository avatarRepository;
	
	public Picture uploadPicture(UploadPictureRequest request) {
		Picture picture = new Picture();
		try {
			//Byte[] data = new Byte[request.getFile().getBytes().length];
			/*for(int i=0;i<data.length;++i) {
				data[i]=request.getFile().getBytes()[i];
			}*/
			
			picture.setData(request.getFile().getBytes());
		} catch(IOException e) {
			
		}
		return pictureRepository.save(picture);
	}
	
	public Picture getPictureById(Long id) {
		return pictureRepository.findById(id).get();
	}
	
	public byte[] getPictureFileById(Long id) {
		Picture picture = pictureRepository.findById(id).get();
		//byte data[] = new byte[picture.getData().length];
		//for(int i=0;i<data.length;++i)
			//data[i] = picture.getData()[i];
		/*byte[] encodeBase64 = Base64.getEncoder().encode(data);
		String base64Encoded="";
		try {
			base64Encoded = new String(encodeBase64, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return base64Encoded;*/
		return picture.getData();
		
		
	}
	
	public Avatar setAvatar(SetAvatarRequest request) {
		Picture picture = getPictureById(request.getPicture_id());
		Optional<Avatar> a1 = avatarRepository.findByPicture_id(picture.getId());
		if(request.getIsAvatar()) {
			if(a1.isEmpty()) {
				Avatar avatar = new Avatar();
				avatar.setPicture(picture);
				return avatarRepository.save(avatar);
			}
		} else {
			if(a1.isPresent()) {
				avatarRepository.delete(a1.get());
			}
		}
		return null;
	}
	
	public List<Avatar> getAvatars(){
		return avatarRepository.findAll();
	}
	
}
