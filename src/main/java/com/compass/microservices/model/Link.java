package com.compass.microservices.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "links")
public class Link {
	@Id
	private String id;
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	private String title;
	private String name;
	private String body;
    @DBRef
	private User  userCreated;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public User getUserCreated() {
		return userCreated;
	}
	public void setUserCreated(User userCreated) {
		this.userCreated = userCreated;
	}
	
	
	
}
