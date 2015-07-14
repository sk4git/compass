package com.learning.model;

public class Link {
	private int link_ID;
	private String name;
	private String title;
	private String body;
	private String user_created;
	
	public Link() {
	}

	public Link(int link_ID, String name, String title, String body,
			String user_created) {
		this.link_ID = link_ID;
		this.name = name;
		this.title = title;
		this.body = body;
		this.user_created = user_created;
	}

	public int getLink_ID() {
		return link_ID;
	}

	public void setLink_ID(int link_ID) {
		this.link_ID = link_ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getUser_created() {
		return user_created;
	}

	public void setUser_created(String user_created) {
		this.user_created = user_created;
	}

}
