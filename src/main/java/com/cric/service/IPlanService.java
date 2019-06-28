package com.cric.service;

import org.springframework.web.multipart.MultipartFile;

import com.cric.model.ListResponse;
import com.cric.model.Plan;
import com.cric.model.Response;



public interface IPlanService {


	Response savePlan(String plan, MultipartFile file);

	ListResponse getPlanList(String search);

	Response getPlanByParams(Plan plan);

	Response deletePlan(Integer planId);

}
