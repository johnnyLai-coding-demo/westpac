package com.fifi.java.practise.springhibernate;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fifi.java.practise.springhibernate.obj.Post;

import com.fifi.java.practise.springhibernate.repository.PostDBfactory;
import com.fifi.java.practise.springhibernate.repository.PostRepository;
import com.fifi.java.practise.util.JsonUtil;



@ActiveProfiles("test")
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.MOCK, classes={ PostReadingApplicationAPI.class })
class PostAPITests {
	
	private MockMvc mockMvc;

    @Autowired
    private PostRepository repository;	
	
	@Autowired
	private WebApplicationContext webApplicationContext;	
	
	@BeforeEach
	public void setUp() {
		 mockMvc = webAppContextSetup(webApplicationContext).build();		 
	}	
    
	@Test
	public void testControllerAlive() throws Exception {
		String result = mockMvc.perform(get("http://test/api/echo"))
				.andExpect(status().is(200))
				.andReturn()
				.getResponse()
				.getContentAsString();
		
		assertEquals("Hello World", result);
	}	

    @Test    
    public void postAPITest() throws UnsupportedEncodingException, Exception {

    	repository.deleteAll();
    	
    	//only got insert/Retrieval test cases as this application not involving update/delete logic
    	List list = repository.findAll();
    	List inputList = new ArrayList();
    	
        assertNotNull(list);
        assertEquals(list.size(), 0);
        
        long newPostId = 1L;
        
        Post johnPost = new Post(1L, "john post title", "john post body");
        Post maryPost = new Post(2L, "mary post title", "mary post body");
        
        inputList.add(johnPost);
        inputList.add(maryPost);
        
        PostDBfactory.insertPostList(repository, inputList);
        
        
		String result = mockMvc.perform(get("http://test/api/posts"))
				.andExpect(status().is(200))
				.andReturn()
				.getResponse()
				.getContentAsString();

        list = JsonUtil.jsonListToJavaObjectList(result, Post.class);
        
        assertNotNull(list);
        assertEquals(list.size(), 2);
        assertTrue(((Post)list.get(0)).equals(johnPost) );
        assertTrue(((Post)list.get(1)).equals(maryPost) );
    }	
	
}
