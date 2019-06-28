package com.cric.service;

import com.cric.model.ListResponse;
import com.cric.model.Response;
import com.cric.model.User;

public interface IUserService {

	Response saveUserData(User user);

	Response login(User user);

	ListResponse getUser(String search);

}
