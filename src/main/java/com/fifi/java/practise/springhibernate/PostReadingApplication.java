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
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.configurationprocessor.json.JSONException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fifi.java.practise.springhibernate.obj.Comment;
import com.fifi.java.practise.springhibernate.obj.Post;
import com.fifi.java.practise.springhibernate.repository.CommentRepository;
import com.fifi.java.practise.springhibernate.repository.PostRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;




@SpringBootApplication
@RestController
@RequestMapping("/api")
public class PostReadingApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired	
	PostRepository postRepository;
	
	@Autowired	
	CommentRepository commentRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(PostReadingApplication.class, args);
	}

	
	@GetMapping(path = "/echo", produces=MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public ResponseEntity<Object> echo() {					
        return new ResponseEntity<Object>("Hello World", HttpStatus.OK);
	}
	
    //get parameter
    //The @ResponseBody annotation is used to serialize the JSON document
	@GetMapping(path = "/comments", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> getComments(@RequestParam(value = "postId") String postId) {
						
		//System.out.println(postId);
		//List comments = repository.findCommentByPostId(Long.parseLong(postId));
		List comments = commentRepository.findCommentByPostId(Long.parseLong(postId));
		
        return new ResponseEntity<Object>(comments, HttpStatus.OK);
	}	
	
	//path parameter
	@GetMapping(path = "/posts", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> page() {
		//@RequestParam(value = "startDate") String startDate, @RequestParam(value = "endDate") String endDate
		//List posts = repository.findPostByDate(java.sql.Timestamp.valueOf(startDate + " 00:00:00"), java.sql.Timestamp.valueOf(endDate + " 23:59:59"));

		List posts = postRepository.findAll();
		
        return new ResponseEntity<Object>(posts, HttpStatus.OK);
	}	
	
	
	public static List<Post> requestPost() throws IOException, JSONException {
		
		Gson gson = new Gson();				
	    URL urlForGetRequest = new URL("https://jsonplaceholder.typicode.com/posts/");
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
	        
	        Type listType = new TypeToken<List<Post>>(){}.getType();
	        posts = gson.fromJson(result, listType);
	        	      	      
	    } else {
	        System.out.println("NOT WORKING");
	    }
	    return posts;
	}

	public static List<Comment> requestComment() throws IOException, JSONException {
		
		Gson gson = new Gson();				
	    URL urlForGetRequest = new URL("https://jsonplaceholder.typicode.com/comments/");
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
	        
	        Type listType = new TypeToken<List<Comment>>(){}.getType();
	        comments = gson.fromJson(result, listType);
	        	      	      
	    } else {
	        System.out.println("NOT WORKING");
	    }
	    return comments;
	}	
	
	public void insertPostList(List<Post> postList)	{
		
		Post post = null;
		
		for (int i=0; i<postList.size(); i++)	{
			post = postList.get(i);
			insertPost(post);
		}
	}
	
	public void insertPost(Post post)	{
		
		Long userId = post.getUserId();
		String title = post.getTitle();
		String body = post.getBody();
		postRepository.save(new Post(userId, title, body));		
	}
	
	public void insertCommentList(List<Comment> commentList)	{
		
		Comment comment = null;
		
		for (int i=0; i<commentList.size(); i++)	{
			comment = commentList.get(i);
			insertComment(comment);
		}
	}
	
	public void insertComment(Comment post)	{
		
		Long postId = post.getPostId();
		String name = post.getName();
		String email = post.getEmail();
		String body = post.getBody();
		commentRepository.save(new Comment(postId, name, email, body));		
	}	
	
	@Override		
	public void run(String... args) throws Exception {


		List<Post> posts = requestPost();
		insertPostList(posts);
		logger.info("All posts -> {}", postRepository.findAll().size());

		
		List<Comment> comments = requestComment();
		insertCommentList(comments);
		logger.info("All comments -> {}", commentRepository.findAll().size());
		

		
		
		
		//List students = repository.findByDate(java.sql.Timestamp.valueOf("2008-01-01 00:00:00"), java.sql.Timestamp.valueOf("2013-09-04 13:30:00"));
//		List students = repository.findPostByDate(java.sql.Timestamp.valueOf("2008-01-01 00:00:00"), java.sql.Timestamp.valueOf("2013-09-04 00:00:00"));
		//List students = repository.findByParentId(1L);
		
//		System.out.println(students);
	}


}



