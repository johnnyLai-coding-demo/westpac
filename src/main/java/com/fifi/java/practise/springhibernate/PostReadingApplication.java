package com.fifi.java.practise.springhibernate;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.context.annotation.Profile;

import org.springframework.web.bind.annotation.RestController;

import com.fifi.java.practise.springhibernate.obj.Comment;
import com.fifi.java.practise.springhibernate.obj.Post;
import com.fifi.java.practise.springhibernate.repository.CommentDBfactory;
import com.fifi.java.practise.springhibernate.repository.CommentRepository;
import com.fifi.java.practise.springhibernate.repository.PostDBfactory;
import com.fifi.java.practise.springhibernate.repository.PostRepository;
import com.fifi.java.practise.util.FifiUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


@Profile("prod")
@SpringBootApplication
@RestController
//@RequestMapping("/api")
public class PostReadingApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired	
	PostRepository postRepository;
	
	@Autowired	
	CommentRepository commentRepository;
	
	
	@Value("${POST_URL}")
	private String POST_URL;

	@Value("${COMMENT_URL}")
	private String COMMENT_URL;	

	
	public static void main(String[] args) {
		SpringApplication.run(PostReadingApplication.class, args);
	}
							
	public static List<Post> requestPost(String postURL) throws IOException, JSONException {
			
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
	        
	        posts = FifiUtil.jsonListToJavaObjectList(result, Post.class);
	        	      	      
	    } else {
	        System.out.println("NOT WORKING");
	    }
	    return posts;
	}

	public static List<Comment> requestComment(String commentURL) throws IOException, JSONException {
			
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
	        
	        comments = FifiUtil.jsonListToJavaObjectList(result, Comment.class);
	        	      	      
	    } else {
	        System.out.println("NOT WORKING");
	    }
	    return comments;
	}	
	
	@Override		
	public void run(String... args) throws Exception {

		List<Post> posts = requestPost(POST_URL);
		PostDBfactory.insertPostList(postRepository, posts);
		logger.info("All posts -> {}", postRepository.findAll().size());
		
		List<Comment> comments = requestComment(COMMENT_URL);
		CommentDBfactory.insertCommentList(commentRepository, comments);
		logger.info("All comments -> {}", commentRepository.findAll().size());
						
		//List students = repository.findByDate(java.sql.Timestamp.valueOf("2008-01-01 00:00:00"), java.sql.Timestamp.valueOf("2013-09-04 13:30:00"));
//		List students = repository.findPostByDate(java.sql.Timestamp.valueOf("2008-01-01 00:00:00"), java.sql.Timestamp.valueOf("2013-09-04 00:00:00"));

	}


}



