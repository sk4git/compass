package com.compass.microservices.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.microservices.model.Link;
import com.compass.microservices.repository.LinkRepository;
import com.compass.microservices.services.LinkService;

@Service
public class LinkServiceImpl implements LinkService{

    private final LinkRepository repository;
    
    @Autowired
    LinkServiceImpl(LinkRepository repository) {
        this.repository = repository;
    }

	@Override
	public List<Link> findByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Link> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Link create(Link link) {
		// TODO Auto-generated method stub
		Link persist = repository.create(link);
		return persist;
	}

	@Override
	public Link update(Link link) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Link link) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Link findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
