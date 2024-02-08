package com.activitae.activitae.requests;

import org.springframework.web.multipart.MultipartFile;

public class UploadPictureRequest {
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
