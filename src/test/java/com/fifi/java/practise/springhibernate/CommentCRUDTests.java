package com.fifi.java.practise.springhibernate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fifi.java.practise.springhibernate.obj.Comment;
import com.fifi.java.practise.springhibernate.repository.CommentDBfactory;
import com.fifi.java.practise.springhibernate.repository.CommentRepository;

@ActiveProfiles("test")
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@DataJpaTest
class CommentCRUDTests {
	
    @Autowired
    private CommentRepository repository;


//    @BeforeEach
//    public void beforeEachTest() throws IOException {
//        MockitoAnnotations.openMocks(this);
//    }	    

	
    @Test    
    public void commentCRUD() {

    	List list = repository.findAll();
    	List inputList = new ArrayList();
    	
        assertNotNull(list);
        assertEquals(list.size(), 0);
        //when(repository.save(any(Comment.class)).thenReturn(userToReturnFromRepository);
        
        Comment johnComment = new Comment(1L, "John", "john@gmail.com", "john_msg_body");
        Comment maryComment = new Comment(1L, "Mary", "Mary@gmail.com", "mary_msg_body");
        
        inputList.add(johnComment);
        inputList.add(maryComment);
        
        CommentDBfactory.insertCommentList(repository, inputList);
        list = repository.findAll();
        
        assertNotNull(list);
        assertEquals(list.size(), 2);
        
       // System.out.println(list);
    }

}
