package com.activitae.activitae.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activitae.activitae.entities.Activite;
import com.activitae.activitae.entities.ActivityRegistration;
import com.activitae.activitae.entities.ActivityReport;
import com.activitae.activitae.repositories.ActivityRegistrationRepository;
import com.activitae.activitae.repositories.ActivityReportRepository;
import com.activitae.activitae.repositories.UserRepository;
import com.activitae.activitae.utils.JwtUtils;

@Service
public class ActivityReportService {
	
	@Autowired
	private ActivityReportRepository activiteReportRepository;
	
	@Autowired
    private UserService userService;

	@Autowired
	private JwtUtils jwtUtils;
	
	public ActivityReport reportActivity(Activite activity) {
		ActivityReport report = new ActivityReport();
		report.setUser(userService.getSelf());
		report.setActivity(activity);
		report.setReport(true);
		return activiteReportRepository.save(report);
	}

}
