package com.activitae.activitae.filters;

import org.springframework.data.jpa.domain.Specification;

import com.activitae.activitae.entities.Activite;
import com.activitae.activitae.entities.ActivityRegistration;
import com.activitae.activitae.entities.User;
import com.activitae.activitae.requests.activity.GetActivityRequest;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ActivityRegistrationFilter {
	
	public static Specification<ActivityRegistration> filterRegistered(GetActivityRequest getActivityRequest, Long user_id){
		return(Root<ActivityRegistration> activityRegistration, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
			Predicate predicate = criteriaBuilder.conjunction();
			Path<Activite> activity = activityRegistration.get("activity");
			Path<User> user = activityRegistration.get("user");
			predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(user.get("id"), user_id));
			predicate=ActivityFilter.setupFilter(predicate, criteriaBuilder, getActivityRequest, activity);
			return predicate;
		};
	}

}
