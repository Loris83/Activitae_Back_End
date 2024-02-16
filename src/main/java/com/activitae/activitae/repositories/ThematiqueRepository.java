package com.activitae.activitae.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.activitae.activitae.entities.Thematique;


public interface ThematiqueRepository extends JpaRepository<Thematique, Long> {
	List<Thematique> findAll();
	Optional<Thematique> findById(Long id);

}
