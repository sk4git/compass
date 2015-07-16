package com.compass.microservices.services;
import java.util.List;

import com.compass.microservices.model.Project;


public interface ProjectService {
	List<Project> findAll();
	Project insert(Project project);
	Project save(Project project);
	
	void delete(Project project);
	Project findOne(String id);
}
