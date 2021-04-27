package com.fifi.java.practise.springhibernate.obj;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Post {
	@Id
	@GeneratedValue
	private Long id;
	private String message;
	private Long parentId;

	public Post() {
		super();
	}

	public Post(Long id, String message, Long parentId) {
		super();
		this.id = id;
		this.message = message;
		this.parentId = parentId;
	}

	public Post(String message, Long parentId) {
		super();
		this.message = message;
		this.parentId = parentId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return String.format("Post [id=%s, message=%s, parentId=%s]", id, message, parentId);
	}

}


