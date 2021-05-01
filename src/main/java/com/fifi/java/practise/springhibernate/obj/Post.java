package com.fifi.java.practise.springhibernate.obj;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Post {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "USERID")
	private Long userId;
	
	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "BODY", columnDefinition="CLOB")
	private String body;
	

//	@Column(name = "CREATEDAT", columnDefinition="DATETIME")
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date createdAt;
	
	public Post() {
		super();
	}

	public Post(Long id, Long userId, String title, String body) {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.body = body;
	}

	public Post(Long userId, String title, String body) {
		super();
		this.userId = userId;
		this.title = title;
		this.body = body;
	}	
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	@Override
	public String toString() {
		return String.format("Post [id=%s, userId=%s, title=%s, body=%s]", id, userId, title, body);
	}

	//ignore ID as it is auto-increment
	@Override  
	public boolean equals(Object obj)   
	{  
		if (obj == null)   
		return false;  
 
		if (obj.getClass() == this.getClass())	{
			
			Post postObj = (Post) obj;
			
			if (this.getBody().equals(postObj.getBody()) && 
				this.getTitle().equals(postObj.getTitle()) &&
				this.getUserId().longValue() == postObj.getUserId().longValue())	{
				return true;								
			}
		}
		return false;
	} 		
}


