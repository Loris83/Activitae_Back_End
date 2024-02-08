package com.activitae.activitae.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.activitae.activitae.entities.Activite;
import com.activitae.activitae.entities.User;

@Repository
public interface ActiviteRepository extends JpaRepository<Activite, Long> {
	Optional<Activite> findById();
	List<Activite> findByUser_id();
	List<Activite> findAll();
}
