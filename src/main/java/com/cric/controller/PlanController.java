package com.cric.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cric.model.ListResponse;
import com.cric.model.Plan;
import com.cric.model.Response;
import com.cric.service.IPlanService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/plan")
public class PlanController {

	@Autowired
	IPlanService planService;

	@CacheEvict(value = { "plan" }, allEntries = true)
	@PostMapping("/save")
	public Response savePlan(@RequestParam("plan") String plan,
			@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
		return planService.savePlan(plan, file);
	}

	@Cacheable("plan")
	@GetMapping(value = "/VPlanList")
	@ApiOperation(value = "get  all Amego Plan details", nickname = "get all Amego Plans", notes = "This will get all the Amego Plan details", produces = "application/json", consumes = "application/json")
	public ListResponse getPlanList(@RequestParam(value = "search", required = false) String search) {
		return planService.getPlanList(search);
	}

	@Cacheable("plan")
	@PostMapping(value = "/params")
	@ApiOperation(value = "View Plan", nickname = "View Plan", notes = "View Plan", produces = "application/json", consumes = "application/json")
	public Response getPlanByParams(@RequestBody Plan plan) {
		return planService.getPlanByParams(plan);
	}

	@DeleteMapping(value = "/delete")
	@ApiOperation(value = "Delete Plan", nickname = "Delete Plan", notes = "Delete Plan", produces = "application/json", consumes = "application/json")
	public Response deletePlan(@RequestParam Integer planId) {
		return planService.deletePlan(planId);
	}

}
