package com.cric.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cric.model.Plan;


public interface IPlanRepository extends CrudRepository<Plan, Integer>{

	Plan findByNameAndIsActive(String name, boolean b);

	List<Plan> findByPlanTypeContainingAndIsActive(String search, boolean b);

	List<Plan> findByIsActiveOrderByUpdatedOnDesc(boolean b);

	Plan findByPlanId(Integer planId);


}
