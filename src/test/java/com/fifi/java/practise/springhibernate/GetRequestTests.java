package com.fifi.java.practise.springhibernate;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fifi.java.practise.springhibernate.obj.Comment;

import com.fifi.java.practise.springhibernate.repository.CommentDBfactory;
import com.fifi.java.practise.springhibernate.repository.CommentRepository;
import com.fifi.java.practise.util.FifiUtil;
import com.google.gson.Gson;


@ActiveProfiles("test")
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes={ PostReadingApplication.class })
class GetRequestTests {
	
	private MockMvc mockMvc;

	@Value("${POST_URL}")
	private String POST_URL;

	@Value("${COMMENT_URL}")
	private String COMMENT_URL;		
	
    @Autowired
    private CommentRepository repository;	
	
	@Autowired
	private WebApplicationContext webApplicationContext;	
	
	@BeforeEach
	public void setUp() {
		 mockMvc = webAppContextSetup(webApplicationContext).build();		 
	}	

    @Test    
    public void commentAPITest() throws UnsupportedEncodingException, Exception {

    	List list = PostReadingApplication.requestComment(COMMENT_URL);
    	assertTrue(list.size() > 0);
    }	
    
    @Test    
    public void postAPITest() throws UnsupportedEncodingException, Exception {

    	List list = PostReadingApplication.requestPost(POST_URL);
    	assertTrue(list.size() > 0);
    }	    
	
}
