package com.compass.microservices.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.compass.microservices.model.Link;

public interface LinkService {
	List<Link> findByTitle(@Param("title") String title);
	List<Link> getAll();
	Link create(Link link);
	Link update(Link link);
	void delete(Link link);
	Link findOne(String id);
}
