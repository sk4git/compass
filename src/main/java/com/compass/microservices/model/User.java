package com.compass.microservices.model;
import org.springframework.data.annotation.Id;

public class User {
	@Id
	private String id;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	

}