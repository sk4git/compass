package com.compass.microservices.model;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

public class Project {
	
	@Id
	private String id;
	private String name;
	private String description;
	private List<String> tags;
	public String getId() {
		return id;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	private Date createdDate;
	

}
