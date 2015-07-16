package com.compass.microservices.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.compass.microservices.model.Tag;
import com.compass.microservices.repository.TagRepository;
import com.compass.microservices.services.TagService;

public class TagServiceImpl implements TagService {
	private TagRepository tagRepository;

	@Autowired
	public TagServiceImpl(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}

	@Override
	public List<Tag> findAll() {
		return tagRepository.findAll();
	}

	@Override
	public Tag update(Tag tag) {
		return tagRepository.update(tag);
	}

	@Override
	public void delete(String id) {
		tagRepository.delete(id);		
	}

	@Override
	public Tag insert(Tag tag) {
		return tagRepository.insert(tag);
	}

	@Override
	public Tag findById(String id) {
		return tagRepository.findOne(id);
	}

}
