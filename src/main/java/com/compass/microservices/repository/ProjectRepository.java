package com.compass.microservices.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.compass.microservices.model.Project;

@RepositoryRestResource(collectionResourceRel = "projects", path = "projects")
public interface ProjectRepository extends MongoRepository<Project, String>,CustomProjectRepository{
	List<Project> findAll();
	Project insert(Project project);
	void delete(Project project);
	Project save(Project project);
	Project findOne(String id);
	  
	@Query("{'name': ?0 }")
	Project findByName(String name);

	@Query("{ 'tags' : ?0 }")
			List<Project> findAllByTagName(String tag);
	
	@Query("{ 'tags' : { $in : ?0 } }")
	List<Project> findAllByTagName(List<String> tag);
}