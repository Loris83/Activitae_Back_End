package com.activitae.activitae.filters;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;

import com.activitae.activitae.entities.Activite;
import com.activitae.activitae.entities.User;
import com.activitae.activitae.requests.activity.GetActivityRequest;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ActivityFilter {
	
	public static Specification<Activite> filter(GetActivityRequest getActivityRequest){
		return(Root<Activite> activity, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
			Predicate predicate = criteriaBuilder.conjunction();
			predicate = setupFilter(predicate, criteriaBuilder, getActivityRequest, activity);
			
			return predicate;
		};
	}
	
	public static Specification<Activite> filterOwned(GetActivityRequest getActivityRequest, Long user_id){
		return(Root<Activite> activity, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
			Predicate predicate = criteriaBuilder.conjunction();
			predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(activity.get("user").get("id"), user_id));
			predicate = setupFilter(predicate, criteriaBuilder, getActivityRequest, activity);
			return predicate;
		};
	}
	
	public static Specification<Activite> filterFavorite(GetActivityRequest getActivityRequest, User user){
		return(Root<Activite> activity, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
			Predicate predicate = criteriaBuilder.conjunction();
			
			predicate = criteriaBuilder.and(predicate,activity.in(user.getFavorites()));
			predicate = setupFilter(predicate, criteriaBuilder, getActivityRequest, activity);
			return predicate;
		};
	}
		
		public static Specification<Activite> filterHistory(GetActivityRequest getActivityRequest, User user){
			return(Root<Activite> activity, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
				Predicate predicate = criteriaBuilder.conjunction();
				
				predicate = criteriaBuilder.and(predicate,activity.in(user.getSeenActivities()));
				predicate = setupFilter(predicate, criteriaBuilder, getActivityRequest, activity);
				
				return predicate;
			};
	}
		
		public static Predicate setupFilter(Predicate predicate, CriteriaBuilder criteriaBuilder, GetActivityRequest getActivityRequest, Path<Activite> activity) {
			
			if(getActivityRequest.getMinDate() != null) {
				predicate = criteriaBuilder.and(predicate,criteriaBuilder.greaterThan(activity.get("date"),getActivityRequest.getMinDate()));
			}
			if(getActivityRequest.getMaxDate() != null) {
				predicate = criteriaBuilder.and(predicate,criteriaBuilder.lessThan(activity.get("date"),getActivityRequest.getMaxDate()));
			}
			if(getActivityRequest.getThematic() != null) {
				predicate = criteriaBuilder.and(predicate,criteriaBuilder.isMember(getActivityRequest.getThematic(), activity.get("activity_thematics").get("name")));
			}
			if(getActivityRequest.getMinPrice() != null) {
				predicate = criteriaBuilder.and(predicate,criteriaBuilder.greaterThan(activity.get("price"),getActivityRequest.getMinPrice()));
			}
			if(getActivityRequest.getMaxPrice() != null) {
				predicate = criteriaBuilder.and(predicate,criteriaBuilder.lessThan(activity.get("price"),getActivityRequest.getMaxPrice()));
			}
			if(getActivityRequest.getType() != null) {
				predicate = criteriaBuilder.and(predicate,criteriaBuilder.equal(activity.get("type"), getActivityRequest.getType()));
			}
			if(getActivityRequest.getPlace_type() != null) {
				predicate = criteriaBuilder.and(predicate,criteriaBuilder.equal(activity.get("place_type"), getActivityRequest.getPlace_type()));
			}
			
			if(getActivityRequest.getId() != null) {
				predicate = criteriaBuilder.and(predicate,criteriaBuilder.equal(activity.get("id"), getActivityRequest.getId()));
			}
			if(getActivityRequest.getEntrantType() != null) {
				predicate = criteriaBuilder.and(predicate,criteriaBuilder.equal(activity.get("entrantType"), getActivityRequest.getEntrantType()));
			}
			if(getActivityRequest.getMinAge() != null) {
				predicate = criteriaBuilder.and(predicate,criteriaBuilder.equal(activity.get("minAge"),getActivityRequest.getMinAge()));
			}
			if(getActivityRequest.getMaxAge() != null) {
				predicate = criteriaBuilder.and(predicate,criteriaBuilder.greaterThanOrEqualTo(activity.get("maxAge"),getActivityRequest.getMaxAge()));
			}
			
			if(getActivityRequest.getSearch() != null) {
				List<String> searches = Arrays.stream(getActivityRequest.getSearch().split(" "))
                        .filter(s -> !s.isBlank())
                        .collect(Collectors.toList());
				for(String search : searches) {
					Predicate searchPredicate = criteriaBuilder.disjunction();

					searchPredicate = criteriaBuilder.or(searchPredicate, criteriaBuilder.like(criteriaBuilder.upper(activity.get("titre")), "%"+search.toUpperCase()+"%"));
					searchPredicate = criteriaBuilder.or(searchPredicate, criteriaBuilder.like(criteriaBuilder.upper(activity.get("descriptif")), "%"+search.toUpperCase()+"%"));
					searchPredicate = criteriaBuilder.or(searchPredicate, criteriaBuilder.like(criteriaBuilder.upper(activity.get("info_comp")), "%"+search.toUpperCase()+"%"));
					searchPredicate = criteriaBuilder.or(searchPredicate, criteriaBuilder.like(criteriaBuilder.concat("#", criteriaBuilder.toString(activity.get("id"))), search));
					predicate = criteriaBuilder.and(predicate, searchPredicate);
				}
				
				
			}
			return predicate;
		}
	
	

}
