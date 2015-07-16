package com.compass.microservices.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.compass.microservices.model.Tag;
import com.compass.microservices.model.User;
import com.compass.microservices.repository.UserRepository;
import com.compass.microservices.services.UserService;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    
    @Autowired
    UserServiceImpl(UserRepository repository) {
        this.userRepository = repository;
    }

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void delete(String id) {
		userRepository.delete(id);
	}

	@Override
	public User findOne(String id) {
		return userRepository.findOne(id);
	}

	@Override
	public User insert(User usr) {
		return userRepository.insert(usr);
	}

	@Override
	public User update(User user) {
		return userRepository.save(user);
	}

	
}
