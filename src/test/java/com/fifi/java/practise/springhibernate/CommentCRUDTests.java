package com.fifi.java.practise.springhibernate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fifi.java.practise.springhibernate.api.PostReadingApplicationAPI;
import com.fifi.java.practise.springhibernate.obj.Comment;
import com.fifi.java.practise.springhibernate.repository.CommentDBfactory;
import com.fifi.java.practise.springhibernate.repository.CommentRepository;
import com.fifi.java.practise.springhibernate.repository.PostDBfactory;

@ActiveProfiles("test")
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
class CommentCRUDTests {
	
    @Autowired
    private CommentRepository repository;
	
	@Autowired	
	CommentDBfactory commentDBfactory;	    
	
    @Test    
    public void commentCRUD() {

    	repository.deleteAll();
    	
    	//only got insert/Retrieval test cases as this application not involving update/delete logic
    	List list = repository.findAll();
    	List inputList = new ArrayList();
    	
        assertNotNull(list);
        assertEquals(list.size(), 0);

        
        Comment johnComment = new Comment(1L, "John", "john@gmail.com", "john_msg_body");
        Comment maryComment = new Comment(1L, "Mary", "Mary@gmail.com", "mary_msg_body");
        
        inputList.add(johnComment);
        inputList.add(maryComment);
        
        commentDBfactory.insertCommentList(repository, inputList);
        list = repository.findAll();
        
        assertNotNull(list);
        assertEquals(list.size(), 2);        
    }

}
