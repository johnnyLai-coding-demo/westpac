package com.fifi.java.practise.springhibernate.api;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fifi.java.practise.springhibernate.obj.Comment;
import com.fifi.java.practise.springhibernate.obj.Post;
import com.fifi.java.practise.springhibernate.repository.CommentRepository;

import com.fifi.java.practise.springhibernate.repository.PostRepository;

@CrossOrigin(origins = "${ALLOWED_ORIGIN}")
@RestController
@RequestMapping("/api")

public class PostReadingApplicationAPI
{

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
	public ResponseEntity<Object> getComments(@RequestParam(value = "postId") String postId) throws NumberFormatException  {
		List comments;
		try	{										
			comments = commentRepository.findCommentByPostId(Long.parseLong(postId));
		}	catch (NumberFormatException numberFormatException)	{
			throw new NumberFormatException("Please provide valid post id");
		}
		
        return new ResponseEntity<Object>(comments, HttpStatus.OK);
	}	
	
	//path parameter
	@GetMapping(path = "/posts", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> page() {
		List posts = postRepository.findAllPostsWithCommentCount();
		
        return new ResponseEntity<Object>(posts, HttpStatus.OK);
	}	

	@GetMapping(path = "/post", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> post(@RequestParam(value = "id") String postId) {
		Post post;
		try	{
			post = postRepository.findPostById(Long.parseLong(postId));
		}	catch (NumberFormatException numberFormatException)	{
			throw new NumberFormatException("Please provide valid post id");
		}		
        return new ResponseEntity<Object>(post, HttpStatus.OK);
	}	
	
	@PostMapping (path = "/comment", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> leaveComment(@RequestBody Comment comment) {

		Comment savedComment = commentRepository.save(comment);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}	
	
    @GetMapping(value = "/private", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> privateEndpoint() {

        
        return new ResponseEntity<Object>("All good. You can see this because you are Authenticated.", HttpStatus.OK);
    }	
	
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<String> rulesForCustomerNotFound(HttpServletRequest req, Exception e) 
	{
		String error = new String(e.getMessage());
		return new ResponseEntity<String>(error, HttpStatus.BAD_REQUEST);
	}

}
