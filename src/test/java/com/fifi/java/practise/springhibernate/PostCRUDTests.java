package com.fifi.java.practise.springhibernate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
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

import com.fifi.java.practise.springhibernate.obj.Post;
import com.fifi.java.practise.springhibernate.repository.PostRepository;

@RunWith(SpringRunner.class)

@ActiveProfiles("test")
@EnableAutoConfiguration
@DataJpaTest
class PostCRUDTests {
	
	@Autowired
    private PostRepository repository;

    
    @BeforeEach
    public void beforeEachTest() throws IOException {
        MockitoAnnotations.openMocks(this);
    }	
	
    @Test    
    public void postCRUD() {

    	List<Post> list = repository.findAll();
 
        assertNotNull(list);
        assertEquals(list.size(), 0);
        

    }

}
