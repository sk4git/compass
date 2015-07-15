package com.compass.microservices.controller;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.compass.microservices.model.Link;
import com.compass.microservices.services.LinkService;

@RestController
@RequestMapping("/links")
public class LinkRestController {
    private final LinkService service;

	@Autowired
	LinkRestController(LinkService service) {
        this.service = service;
    }

	@RequestMapping(method=RequestMethod.GET)
	  public List<Link> getAll() {
	    return new ArrayList<Link>();
	  }
	  
	  @RequestMapping(method=RequestMethod.POST)
	   @ResponseStatus(HttpStatus.CREATED)

	  public Link create(@RequestBody Link link) {
	    return null;
	  }
	  
	  @RequestMapping(method=RequestMethod.DELETE, value="{id}")
	  public void delete(@PathVariable String id) {

	  }
	  
	  @RequestMapping(method=RequestMethod.PUT, value="{id}")
	  public Link update(@PathVariable String id, @RequestBody Link link) {
	  return null;
	  }

}
