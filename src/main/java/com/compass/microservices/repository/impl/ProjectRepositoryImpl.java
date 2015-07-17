package com.compass.microservices.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.compass.microservices.repository.CustomProjectRepository;

public class ProjectRepositoryImpl implements CustomProjectRepository {
	  private final MongoOperations operations;
	  
	  @Autowired
	  public ProjectRepositoryImpl(MongoOperations operations) {
	    this.operations = operations;
	  }

}
