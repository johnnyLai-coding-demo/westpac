package com.fifi.java.practise.springhibernate.dataFeed;




import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.fifi.java.practise.springhibernate.obj.Comment;
import com.fifi.java.practise.springhibernate.obj.Post;
import com.fifi.java.practise.springhibernate.repository.CommentDBfactory;
import com.fifi.java.practise.springhibernate.repository.CommentRepository;
import com.fifi.java.practise.springhibernate.repository.PostDBfactory;
import com.fifi.java.practise.springhibernate.repository.PostRepository;
import com.fifi.java.practise.springhibernate.request.PostCommentHttpRequest;


@Profile({"prod", "default"})
@Component
public class SampleDataFeeder implements CommandLineRunner {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired	
	PostRepository postRepository;
	
	@Autowired	
	CommentRepository commentRepository;

	@Autowired	
	PostDBfactory postDBfactory;
	
	@Autowired	
	CommentDBfactory commentDBfactory;	
	
	@Autowired
	PostCommentHttpRequest postReadingApplication;
	
	@Value("${POST_URL}")
	private String POST_URL;

	@Value("${COMMENT_URL}")
	private String COMMENT_URL;	
							
	
	
	@Override		
	public void run(String... args) throws Exception {

		List<Post> posts = postReadingApplication.requestPost(POST_URL);
		postDBfactory.insertPostList(postRepository, posts);
		logger.info("All posts -> {}", postRepository.findAll().size());
		
		List<Comment> comments = postReadingApplication.requestComment(COMMENT_URL);
		commentDBfactory.insertCommentList(commentRepository, comments);
		logger.info("All comments -> {}", commentRepository.findAll().size());
		logger.info("Finish loading sample data");
	}


}



