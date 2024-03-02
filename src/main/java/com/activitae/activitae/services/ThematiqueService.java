package com.activitae.activitae.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activitae.activitae.entities.Activite;
import com.activitae.activitae.entities.Thematique;
import com.activitae.activitae.repositories.ThematiqueRepository;
import com.activitae.activitae.requests.CreateThematicRequest;

@Service
public class ThematiqueService {

	
	@Autowired
	private ThematiqueRepository thematiqueRepository ;
	
	public List<Thematique> getThematiques () {
		return thematiqueRepository.findAll();
	}
	
	public Thematique getThematiqueById(Long id){
		Optional<Thematique> thematique = thematiqueRepository.findById(id);		
		return thematique.orElseThrow();
	}
	
	public Thematique addThematique(CreateThematicRequest request) {
		Thematique thematic = new Thematique();
		thematic.setItem(request.getItem());
		thematic.setImageUrl(request.getImageUrl());
		return thematiqueRepository.save(thematic);
	}

	
}
