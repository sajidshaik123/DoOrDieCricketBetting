package com.cric.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import com.cric.common.CommonConstants;
import com.cric.model.AppUtil;
import com.cric.model.ListResponse;
import com.cric.model.Response;
import com.cric.model.User;
import com.cric.repository.IUserRepository;
import com.cric.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	IUserRepository userRepository;
	Logger logger = LoggerFactory.getLogger(UserService.class);

	public Response saveUserData(User user) {
		Response response = new Response();
		Response.Status status = new Response.Status();
		User amegoUserEmailObj = null;
		User amegoUserPhoneObj = null;
		amegoUserEmailObj = userRepository.findByEmailAndIsActive(user.getEmail(), true);
		amegoUserPhoneObj = userRepository.findByPrimaryPhoneAndIsActive(user.getPrimaryPhone(), true);

		try {

			if (user.getUserId() != null) {
				if (amegoUserEmailObj != null && amegoUserPhoneObj != null) {
					if ((user.getUserId().equals(amegoUserEmailObj.getUserId()))
							&& (user.getUserId().equals(amegoUserPhoneObj.getUserId()))) {
						user.setIsActive(true);
						user.setUpdatedOn(AppUtil.getTodayDate());
						response.setData(userRepository.save(user));
						status.setMessage(CommonConstants.User.SAVE);
						status.setSuccess(CommonConstants.ResponseStatus.SUCCESS);
						response.setStatus(status);
						return response;
					} else {
						status.setMessage(CommonConstants.User.EMAILEXIST);
						status.setSuccess(CommonConstants.ResponseStatus.FAIL);
						response.setStatus(status);
						return response;
					}

				} else if (amegoUserEmailObj == null || amegoUserPhoneObj == null) {
					user.setUpdatedOn(AppUtil.getTodayDate());
					response.setData(userRepository.save(user));
					status.setMessage(CommonConstants.User.SAVE);
					status.setSuccess(CommonConstants.ResponseStatus.SUCCESS);

					response.setStatus(status);
					return response;
				}
			} else {
				if ((amegoUserEmailObj != null || amegoUserPhoneObj != null)
						|| (amegoUserEmailObj != null && amegoUserPhoneObj != null)) {
					status.setMessage(CommonConstants.User.EMAILEXIST);
					status.setSuccess(CommonConstants.ResponseStatus.FAIL);
					response.setStatus(status);
					return response;
				}
				try {
					user.setIsActive(true);
					user.setCreatedOn(AppUtil.getTodayDate());
					user.setUpdatedOn(AppUtil.getTodayDate());
				} catch (Exception e) {
					e.printStackTrace();
				}
				response.setData(userRepository.save(user));
				status.setMessage(CommonConstants.User.SAVE);
				status.setSuccess(CommonConstants.ResponseStatus.SUCCESS);
				response.setStatus(status);

			}

		} catch (JsonParseException e1) {
			e1.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("User session exception issue " + e.getMessage());
			status.setMessage(CommonConstants.Authentication.INVALIDATESESSIONFAIL);
			status.setSuccess(CommonConstants.ResponseStatus.FAIL);
		}

		return response;
	}

	public Response login(User user) {

		Response response = new Response();
		Response.Status status = new Response.Status();
		Map<String, Object> data = new HashMap<>();
		User existingUser = null;

		existingUser = userRepository.findByEmailAndPasswordAndIsActive(user.getEmail(), user.getPassword(), true);

		if (existingUser != null) {
			data.put("userId", existingUser.getUserId());
			if (existingUser.getEmail() != null) {
				data.put("email", existingUser.getEmail());
			} else {
				data.put("email", "");
			}
			response.setData(data);
			status.setMessage(CommonConstants.Authentication.AUTHORIZEDUSER);
			status.setSuccess(CommonConstants.ResponseStatus.SUCCESS);
		} else {
			status.setMessage(CommonConstants.Authentication.UNAUTHORIZEDUSER);
			status.setSuccess(CommonConstants.ResponseStatus.FAIL);
		}
		response.setStatus(status);

		return response;

	}

	public ListResponse getUser(String search) {
		ListResponse response = new ListResponse();
		ListResponse.Status status = new ListResponse.Status();
		List<User> userList = null;
		if (search.isEmpty()) {
			userList = (List<User>) userRepository.findByFullNameContainingAndIsActive(search, true);
		} else {
			userList = (List<User>) userRepository.findAll();
		}
		response.setTotalResult(userList.size());
		response.setData(userList);
		response.setStatus(status);
		return response;

	}

}
