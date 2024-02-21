package com.activitae.activitae.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.activitae.activitae.entities.Activite;
import com.activitae.activitae.entities.User;

@Repository
public interface ActiviteRepository extends JpaRepository<Activite, Long>, JpaSpecificationExecutor<Activite> {
	List<Activite> findByUser(User user);
	List<Activite> findAll();
	Optional<Activite> findById(Long id);
}
