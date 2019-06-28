package com.cric.service.impl;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cric.common.CommonConstants;
import com.cric.model.AppUtil;
import com.cric.model.ListResponse;
import com.cric.model.Plan;
import com.cric.model.Response;
import com.cric.repository.IPlanRepository;
import com.cric.service.IPlanService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PlanService implements IPlanService {

	@Autowired
	IPlanRepository planRepository;

	Logger logger = LoggerFactory.getLogger(PlanService.class);

	@Override
	public Response savePlan(String plan, MultipartFile file) {
		Response response = new Response();
		Response.Status status = new Response.Status();

		ObjectMapper mapper = new ObjectMapper();
		Plan planMapperObject = null;
		Plan updatedPlan = null;
		try {
			planMapperObject = mapper.readValue(plan, Plan.class);
			if (planMapperObject.getPlanId() != null) {
				planMapperObject.setUpdatedOn(AppUtil.getTodayDate());
				if (file != null) {
					planMapperObject.setDocumentContentType(file.getContentType());
					planMapperObject.setDocumentFileName(file.getOriginalFilename());
					planMapperObject.setDocumentContent(file.getBytes());
				}
				updatedPlan = planRepository.save(planMapperObject);
				response.setData(updatedPlan);
				status.setSuccess(CommonConstants.ResponseStatus.SUCCESS);
				status.setMessage(CommonConstants.Plan.UPDATE);

			} else {
				Plan searchPlan = planRepository.findByNameAndIsActive(planMapperObject.getName(), true);
				if (searchPlan != null) {
					status.setSuccess(CommonConstants.ResponseStatus.FAIL);
					status.setMessage(CommonConstants.Plan.DUPLICATE);
				} else {
					if (file != null) {
						planMapperObject.setDocumentContentType(file.getContentType());
						planMapperObject.setDocumentFileName(file.getOriginalFilename());
						planMapperObject.setDocumentContent(file.getBytes());
					}
					planMapperObject.setCreatedOn(AppUtil.getTodayDate());
					planMapperObject.setUpdatedOn(AppUtil.getTodayDate());
					planMapperObject.setIsActive(true);
					updatedPlan = planRepository.save(planMapperObject);
					response.setData(updatedPlan);
					status.setSuccess(CommonConstants.ResponseStatus.SUCCESS);
					status.setMessage(CommonConstants.Plan.SAVE);
				}
			}
			response.setStatus(status);

		} catch (IOException e) {
			e.printStackTrace();

		} catch (ParseException pe) {
			pe.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return response;

	}

	@Override
	public ListResponse getPlanList(String search) {
		ListResponse response = new ListResponse();
		ListResponse.Status status = new ListResponse.Status();
		List<Plan> planlist = null;
		try {
			if (search != null && !search.isEmpty()) {
				planlist = planRepository.findByPlanTypeContainingAndIsActive(search, true);
			} else {
				planlist = planRepository.findByIsActiveOrderByUpdatedOnDesc(true);
			}

			if (!planlist.isEmpty()) {
				response.setTotalResult(planlist.size());
				response.setData(planlist);
				status.setSuccess(CommonConstants.ResponseStatus.SUCCESS);
				status.setMessage(CommonConstants.Plan.LISTFOUND);

			} else {
				status.setSuccess(CommonConstants.ResponseStatus.FAIL);
				status.setMessage(CommonConstants.Plan.LISTNOTFOUND);
			}
		} catch (Exception e) {
			logger.error("Plan Details exception issue " + e.getMessage());
			status.setMessage(CommonConstants.Plan.ERROR);
			status.setSuccess(CommonConstants.ResponseStatus.FAIL);
		}
		response.setStatus(status);
		return response;

	}

	@Override
	public Response getPlanByParams(Plan plan) {

		Response response = new Response();
		Response.Status status = new Response.Status();
		try {
			Plan amegoPlanObj = new Plan();
			amegoPlanObj = planRepository.findByPlanId(plan.getPlanId());
			if (amegoPlanObj != null) {
				response.setData(amegoPlanObj);
				status.setMessage(CommonConstants.Plan.RECORDFOUND);
				status.setSuccess(CommonConstants.ResponseStatus.SUCCESS);
			} else {
				status.setMessage(CommonConstants.Plan.RECORDNOTFOUND);
				status.setSuccess(CommonConstants.ResponseStatus.FAIL);
			}
		}

		catch (Exception e) {
			logger.error("Plan Details exception issue " + e.getMessage());
			status.setMessage(CommonConstants.Plan.ERROR);

			status.setSuccess(CommonConstants.ResponseStatus.FAIL);
		}
		return response;
	}

	@Override
	public Response deletePlan(Integer planId) {
		Response response = new Response();
		Response.Status status = new Response.Status();
		try {
			Plan objUser = planRepository.findByPlanId(planId);

			if (objUser != null) {
				objUser.setIsActive(false);
				planRepository.save(objUser);
				status.setMessage("User Deleted Sucessfully");
				status.setSuccess("Success");
			} else {
				status.setMessage("User Not Found");
				status.setSuccess("Fail");
			}
		} catch (Exception e) {
			logger.error("User session exception issue " + e.getMessage());
			status.setMessage(CommonConstants.Authentication.INVALIDATESESSIONFAIL);
			status.setSuccess(CommonConstants.ResponseStatus.FAIL);
		}

		return response;
	}

}
