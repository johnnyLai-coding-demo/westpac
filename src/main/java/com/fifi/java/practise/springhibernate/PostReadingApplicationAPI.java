package com.fifi.java.practise.springhibernate;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
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
import com.fifi.java.practise.springhibernate.repository.CommentDBfactory;
import com.fifi.java.practise.springhibernate.repository.CommentRepository;
import com.fifi.java.practise.springhibernate.repository.PostDBfactory;
import com.fifi.java.practise.springhibernate.repository.PostRepository;

@RestController
@RequestMapping("/api")
@SpringBootApplication

public class PostReadingApplicationAPI
{
		
    public static void main( String[] args )
    {
    	
    	SpringApplication.run(PostReadingApplicationAPI.class, args);
    }    
    
	@Autowired	
	CommentRepository commentRepository;    

	@Autowired	
	PostRepository postRepository;	
	
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
	
	

}
