package com.activitae.activitae.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.activitae.activitae.entities.Avatar;


public interface AvatarRepository extends JpaRepository<Avatar, Long> {
	List<Avatar> findAll();
	Optional<Avatar> findById(Long id);
	Optional<Avatar> findByPicture_id(Long Picture_id);
}
