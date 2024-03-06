package com.activitae.activitae.services;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<ActivityReport> get(Activite activity) {
		List<ActivityReport> activityReports = new ArrayList<ActivityReport>();
		activityReports = activiteReportRepository.findByActivity(activity);
		return activityReports;
	}
	
	public void deleteReports(Activite activity) {//supprime toutes les inscriptions d'une activité
		List<ActivityReport> activityReports = get(activity);
		if(activityReports != null) {
			for(int i=0;i<activityReports.size();i++)
				activiteReportRepository.delete(activityReports.get(i));
		}
	}

}
