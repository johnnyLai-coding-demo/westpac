package com.fifi.java.practise.springhibernate.request;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Component;

import com.fifi.java.practise.springhibernate.obj.Comment;
import com.fifi.java.practise.springhibernate.obj.Post;

import com.fifi.java.practise.springhibernate.repository.CommentRepository;

import com.fifi.java.practise.springhibernate.repository.PostRepository;
import com.fifi.java.practise.util.JsonUtil;




@Component
public class PostCommentHttpRequest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired	
	PostRepository postRepository;
	
	@Autowired	
	CommentRepository commentRepository;
	
	
	@Value("${POST_URL}")
	private String POST_URL;

	@Value("${COMMENT_URL}")
	private String COMMENT_URL;	
							
	public List<Post> requestPost(String postURL) throws IOException, JSONException {
			
	    URL urlForGetRequest = new URL(postURL);
	    String readLine = null;
	    String result = "";
	    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
	    conection.setRequestMethod("GET");

	    int responseCode = conection.getResponseCode();
	    List<Post> posts = null;

	    if (responseCode == HttpURLConnection.HTTP_OK) {
	        BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));

	        while ((readLine = in .readLine()) != null) {

	            result = result + readLine;
	        } 
	        in.close();	    	   
	        
	        posts = JsonUtil.jsonListToJavaObjectList(result, Post.class);
	        	      	      
	    } else {
	        System.out.println("NOT WORKING");
	    }
	    return posts;
	}

	public List<Comment> requestComment(String commentURL) throws IOException, JSONException {
			
	    URL urlForGetRequest = new URL(commentURL);
	    String readLine = null;
	    String result = "";
	    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
	    conection.setRequestMethod("GET");

	    int responseCode = conection.getResponseCode();
	    List<Comment> comments = null;

	    if (responseCode == HttpURLConnection.HTTP_OK) {
	        BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));

	        while ((readLine = in .readLine()) != null) {

	            result = result + readLine;
	        } 
	        in.close();	    	   
	        
	        comments = JsonUtil.jsonListToJavaObjectList(result, Comment.class);
	        	      	      
	    } else {
	        System.out.println("NOT WORKING");
	    }
	    return comments;
	}	
	
}



