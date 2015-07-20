package com.compass.microservices.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.microservices.model.Project;
import com.compass.microservices.repository.ProjectRepository;
import com.compass.microservices.services.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{
private final ProjectRepository projectRepository;
    
    @Autowired
    ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
	@Override
	public List<Project> findAll() {
		// TODO Auto-generated method stub
		return projectRepository.findAll();
	}

	@Override
	public Project insert(Project project) {
		// TODO Auto-generated method stub
		return projectRepository.insert(project);
	}

	@Override
	public void delete(Project project) {
		projectRepository.delete(project);
		
	}

	@Override
	public Project findOne(String id) {
		// TODO Auto-generated method stub
		return projectRepository.findOne(id);
	}
	@Override
	public Project save(Project project) {
		// TODO Auto-generated method stub
		return projectRepository.save(project);
	}
	@Override
	public Project findByName(String name) {
		// TODO Auto-generated method stub
		return projectRepository.findByName(name);
	}
	@Override
	public List<Project> findAllByTagName(String tag) {
		// TODO Auto-generated method stub
		return projectRepository.findAllByTagName(tag);
	}
	@Override
	public List<Project> findAllByTagName(List<String> tags) {
		// TODO Auto-generated method stub
		return projectRepository.findAllByTagName(tags);
	}

}
