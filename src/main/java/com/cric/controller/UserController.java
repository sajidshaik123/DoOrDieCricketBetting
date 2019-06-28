package com.cric.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cric.model.ListResponse;
import com.cric.model.Response;
import com.cric.model.User;
import com.cric.service.IUserService;

import io.swagger.annotations.ApiOperation;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	IUserService userService;
	//clean or flush the cache after any update or delete operations, for that we use @CacheEvict  {"users"}
	
	@CacheEvict(value = {"users"}, allEntries = true)
	@PostMapping(value = "/saveUser")
	@ApiOperation(value = "Save Users", nickname = "Save Users", notes = "Save Users", produces = "application/json", consumes = "application/json")
	public Response saveUser(@RequestBody User user) {
		return userService.saveUserData(user);
	}
	
	@Cacheable("users")
	@PostMapping(value = "/login")
	@ApiOperation(value = "Get User Authenticate", nickname = "Get User Authenticate", notes = "Get User Authenticate", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Response> login(@RequestBody User user) {

		Response response = userService.login(user);
		if (response.getStatus().getSuccess().equalsIgnoreCase("Success")) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@Cacheable("users")
	@GetMapping(value = "/VList")
	@ApiOperation(value = "get  all Amego Plan details", nickname = "get all Amego Plans", notes = "This will get all the Amego Plan details", produces = "application/json", consumes = "application/json")
	public ListResponse getPlanList(@RequestParam(value = "search", required = false) String search) {
		return userService.getUser(search);
	}
} 
