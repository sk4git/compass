package com.compass.microservices.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.compass.microservices.model.User;

public interface UserRepository extends MongoRepository<User, String>{
	
	List<User> findAll();
	User findOne(String id);
	User insert(User user);
	void delete(String id);
	User save(User user);

}
