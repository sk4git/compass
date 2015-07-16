package com.compass.microservices.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.compass.microservices.model.Tag;

public interface TagRepository extends MongoRepository<Tag, String>{
	List<Tag> findAll();
	Tag findOne(String id);
	Tag insert(Tag tag);
	Tag update(Tag tag);
	void delete(String id);
}
