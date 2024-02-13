package com.activitae.activitae.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.activitae.activitae.entities.Avatar;
import com.activitae.activitae.entities.Picture;
import com.activitae.activitae.requests.UploadPictureRequest;
import com.activitae.activitae.requests.picture.SetAvatarRequest;
import com.activitae.activitae.services.PictureService;
import com.activitae.activitae.utils.JwtUtils;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pictures")
public class PictureController {
	@Autowired
	private PictureService pictureService;
	
	@Autowired
	 private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;
	
	@PostMapping("/upload")
	public Picture upload(@RequestBody MultipartFile file){
		UploadPictureRequest request = new UploadPictureRequest();
		request.setFile(file);
		return pictureService.uploadPicture(request);
	}
	
	@GetMapping("/get/{id}")
	public Picture get(@PathVariable Long id){
		return pictureService.getPictureById(id);
	}
	
	@GetMapping("/view/{id}")
	public byte[] view(@PathVariable Long id){
		return pictureService.getPictureFileById(id);
	}
	
	@PostMapping("/set-avatar")
	public Avatar setAvatar(@RequestBody SetAvatarRequest request){
		return pictureService.setAvatar(request);
	}
	
	@GetMapping("/get-avatars")
	public List<Avatar> getAvatars(){
		return pictureService.getAvatars();
	}
}
