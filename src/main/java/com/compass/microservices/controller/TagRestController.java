package com.compass.microservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.compass.microservices.model.Tag;
import com.compass.microservices.services.TagService;

@RestController
@RequestMapping("/tags")
public class TagRestController {
	private final TagService tagService;

	@Autowired
	TagRestController(TagService tagService) {
		this.tagService = tagService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Tag> getAll() {
		return tagService.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public Tag getTagById(@PathVariable String id) {

		return tagService.findById(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Tag insert(@RequestBody Tag tag) {
		return tagService.insert(tag);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public void delete(@PathVariable String id) {
		tagService.delete(id);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Tag update(@RequestBody Tag tag) {
		return tagService.update(tag);
	}

}
