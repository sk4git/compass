package com.compass.microservices.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.compass.microservices.model.Project;
import com.compass.microservices.services.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectRestController {
	
    private final ProjectService service;

	@Autowired
	ProjectRestController(ProjectService service) {
        this.service = service;
    }

	@RequestMapping(method=RequestMethod.GET)
	  public List<Project> getAll() {
	    return service.findAll();
	  }
	
	@RequestMapping(method=RequestMethod.GET, value="{id}")
	  public Project getById(@PathVariable String id) {
	    return service.findOne(id);
	  }
	
	@RequestMapping(method=RequestMethod.GET,value="/search")
	  public Project getByName(@RequestParam("name") String name) {
		return service.findByName(name);
	  }
	
	@RequestMapping(method=RequestMethod.GET,value="/tagged/{tag}")
	  public List<Project> getByTagName(@PathVariable("tag") List<String> tag) {
	    return service.findAllByTagName(tag);
	  }
	
	
	  
	  @RequestMapping(method=RequestMethod.POST)
	   @ResponseStatus(HttpStatus.CREATED)
	  public Project create(@RequestBody Project project) {
	    return service.insert(project);
	  }
	  
	  @RequestMapping(method=RequestMethod.DELETE, value="{id}")
	  public void delete(@PathVariable String id) {
		  service.delete(service.findOne(id));
	  }
	  
	  @RequestMapping(method=RequestMethod.PUT)
	  public Project update(@RequestBody Project project) {
	  return service.save(project);
	  }


}
