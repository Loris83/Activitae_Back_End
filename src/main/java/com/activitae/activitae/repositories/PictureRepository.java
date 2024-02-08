package com.activitae.activitae.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.activitae.activitae.entities.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long>  {
	Optional<Picture> findById(Long id);
}
