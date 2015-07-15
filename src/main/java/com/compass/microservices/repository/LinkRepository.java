package com.compass.microservices.repository;

import java.util.List;

import com.compass.microservices.model.Link;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "link", path = "link")
public interface LinkRepository extends MongoRepository<Link, String>{
	List<Link> findByTitle(@Param("title") String title);
	List<Link> getAll();
	Link create(Link link);
	Link update(Link link);
	@Override
	void delete(Link link);
	@Override
	Link findOne(String id);

}
