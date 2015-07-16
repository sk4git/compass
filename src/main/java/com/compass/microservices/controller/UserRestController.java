package com.compass.microservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.compass.microservices.model.User;
import com.compass.microservices.services.UserService;

@RestController
@RequestMapping("/users")
public class UserRestController {
	private final UserService userService;

	@Autowired
	UserRestController(UserService service) {
		this.userService = service;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<User> getAll() {
		return userService.findAll();
	}

	@RequestMapping(method = RequestMethod.GET,value = "{id}")
	public User getUserById(@PathVariable String id) {
		return userService.findOne(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public User create(@RequestBody User usr) {

		return userService.insert(usr);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public void delete(@PathVariable String id) {
		userService.delete(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public User update(@RequestBody User usr) {
		return userService.update(usr);
	}

}
