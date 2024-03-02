package com.activitae.activitae.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.activitae.activitae.entities.Activite;
import com.activitae.activitae.entities.ActivityRegistration;
import com.activitae.activitae.entities.ActivityReport;
import com.activitae.activitae.entities.User;

@Repository
public interface ActivityReportRepository extends JpaRepository<ActivityReport, Long>, JpaSpecificationExecutor<ActivityReport> {
	List<ActivityReport> findByUser(User user);
	List<ActivityReport> findAll();
	Optional<ActivityReport> findById(Long id);
}
