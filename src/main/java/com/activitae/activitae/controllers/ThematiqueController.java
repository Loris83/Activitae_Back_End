package com.activitae.activitae.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.activitae.activitae.entities.Picture;
import com.activitae.activitae.entities.Thematique;
import com.activitae.activitae.services.ThematiqueService;

@RestController
@RequestMapping("/api/thematiques")
public class ThematiqueController {
	
	@Autowired
	private ThematiqueService thematiqueService ;
	
	@GetMapping("/get-thematiques")
	public List<Thematique> getThematiques(){
		return thematiqueService.getThematiques();
	}
	
	@GetMapping("get/{id}")
	public Thematique getThematiqueById(@PathVariable Long id) {
		return thematiqueService.getThematiqueById(id);
	}
	
	
}
