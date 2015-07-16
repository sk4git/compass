package com.compass.microservices.services;

import java.util.List;

import com.compass.microservices.model.User;

public interface UserService {
	List<User> findAll();
	User findOne(String id);
	User insert(User user);
	void delete(String id);
	User update(User user);

}
