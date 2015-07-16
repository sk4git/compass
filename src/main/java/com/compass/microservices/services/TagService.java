package com.compass.microservices.services;

import java.util.List;

import com.compass.microservices.model.Tag;

public interface TagService {
	List<Tag> findAll();
	Tag findById(String id);

	Tag insert(Tag tag);

	Tag update(Tag tag);

	void delete(String id);
}
