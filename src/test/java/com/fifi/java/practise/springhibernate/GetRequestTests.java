package com.fifi.java.practise.springhibernate;



import static org.junit.Assert.assertTrue;


import java.io.UnsupportedEncodingException;

import java.util.List;



import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fifi.java.practise.springhibernate.request.PostCommentHttpRequest;



@ActiveProfiles("test")
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes={ PostCommentHttpRequest.class })

class GetRequestTests {
	

	@Value("${POST_URL}")
	private String POST_URL;

	@Value("${COMMENT_URL}")
	private String COMMENT_URL;		
	
	@Autowired
	private PostCommentHttpRequest postReadingApplication;
	

	

	


    @Test    
    public void commentAPITest() throws UnsupportedEncodingException, Exception {
    	System.out.println(POST_URL);
    	System.out.println(COMMENT_URL);
    	List list = postReadingApplication.requestComment(COMMENT_URL);
    	assertTrue(list.size() > 0);
    }	
    
    @Test    
    public void postAPITest() throws UnsupportedEncodingException, Exception {

    	List list = postReadingApplication.requestPost(POST_URL);
    	assertTrue(list.size() > 0);
    }	    
	
}
