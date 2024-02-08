package com.activitae.activitae.services;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;

import com.activitae.activitae.entities.Picture;
import com.activitae.activitae.repositories.PictureRepository;
import com.activitae.activitae.requests.UploadPictureRequest;

@Service
public class PictureService {
	
	@Autowired
	private PictureRepository pictureRepository;
	
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
	
}
