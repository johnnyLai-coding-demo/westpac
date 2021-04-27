package com.fifi.java.practise.springhibernate;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import com.fifi.java.practise.springhibernate.obj.Post;
import com.fifi.java.practise.springhibernate.repository.PostRepository;

@SpringBootApplication

public class PostReadingApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PostRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(PostReadingApplication.class, args);
	}

	
	@Override	
	@Profile("!test")
	public void run(String... args) throws Exception {

		logger.info("Student id 10001 -> {}", repository.findById(1L));
		
		logger.info("All users 1 -> {}", repository.findAll());
		
		//Insert
		logger.info("Inserting -> {}", repository.save(new Post("John", 1L)));
		logger.info("Inserting -> {}", repository.save(new Post("John2", 1L)));
		//Update
		//logger.info("Update 10001 -> {}", repository.save(new Post(10001L, "Name-Updated", null)));

		//repository.deleteById(10002L);
		
		logger.info("All users 2 -> {}", repository.findAll());
		
		List students = repository.findByParentId(1L);
		
		System.out.println(students);
	}


}



