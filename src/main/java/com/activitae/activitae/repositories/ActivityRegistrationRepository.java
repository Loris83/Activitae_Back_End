package com.activitae.activitae.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.activitae.activitae.entities.Activite;
import com.activitae.activitae.entities.ActivityRegistration;
import com.activitae.activitae.entities.User;

@Repository
public interface ActivityRegistrationRepository extends JpaRepository<ActivityRegistration, Long>, JpaSpecificationExecutor<ActivityRegistration> {
	List<ActivityRegistration> findByUser(User user);
	List<ActivityRegistration> findByActivity(Activite activity);
	List<ActivityRegistration> findAll();
	Optional<ActivityRegistration> findById(Long id);
}
