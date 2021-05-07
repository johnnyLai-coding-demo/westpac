package com.fifi.java.practise.springhibernate;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fifi.java.practise.springhibernate.repository.CommentRepository;

import com.fifi.java.practise.springhibernate.repository.PostRepository;



@SpringBootApplication

public class PostReadingApplication
{
		
    public static void main( String[] args )
    {
    	
    	SpringApplication.run(PostReadingApplication.class, args);
    }    
    


}
