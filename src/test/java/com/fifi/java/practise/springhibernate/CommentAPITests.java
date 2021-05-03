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
import com.fifi.java.practise.util.JsonUtil;



@ActiveProfiles("test")
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.MOCK, classes={ PostReadingApplicationAPI.class })
class CommentAPITests {
	
	private MockMvc mockMvc;

    @Autowired
    private CommentRepository repository;	
	
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
    public void commentAPITest() throws UnsupportedEncodingException, Exception {

    	repository.deleteAll();
    	
    	//only got insert/Retrieval test cases as this application not involving update/delete logic
    	List list = repository.findAll();
    	List inputList = new ArrayList();
    	
        assertNotNull(list);
        assertEquals(list.size(), 0);

        
        long newCommentId = 1L;
        
        Comment johnComment = new Comment(newCommentId, "John", "john@gmail.com", "john_msg_body");
        Comment maryComment = new Comment(newCommentId, "Mary", "Mary@gmail.com", "mary_msg_body");
        
        inputList.add(johnComment);
        inputList.add(maryComment);
        
        CommentDBfactory.insertCommentList(repository, inputList);
        
        
		String result = mockMvc.perform(get("http://test/api/comments").param("postId", newCommentId+""))
				.andExpect(status().is(200))
				.andReturn()
				.getResponse()
				.getContentAsString();

        list = JsonUtil.jsonListToJavaObjectList(result, Comment.class);
        
        assertNotNull(list);
        assertEquals(list.size(), 2);
        assertTrue(((Comment)list.get(0)).equals(johnComment) );
        assertTrue(((Comment)list.get(1)).equals(maryComment) );
    }
    
    @Test    
    public void commentAPIErrorTest() throws UnsupportedEncodingException, Exception {
                
		String result = mockMvc.perform(get("http://test/api/comments").param("postId", ""))
				.andExpect(status().is(400))
				.andReturn()
				.getResponse()
				.getContentAsString();

		assertEquals(result, "Please provide valid post id");
    }    
	
}
