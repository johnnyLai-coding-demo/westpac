package com.fifi.java.practise.springhibernate.obj;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Post {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "MESSAGE")
	private String message;
	
	@Column(name = "PARENTID")
	private Long parentId;

	@Column(name = "CREATEDAT", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	public Post() {
		super();
	}

	public Post(Long id, String message, Long parentId, Date createdAt) {
		super();
		this.id = id;
		this.message = message;
		this.parentId = parentId;
		this.createdAt = createdAt;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return String.format("Post [id=%s, message=%s, parentId=%s, createdAt=%s]", id, message, parentId, createdAt);
	}

}


