package com.fifi.java.practise.springhibernate.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fifi.java.practise.springhibernate.obj.Post;

@Component
public class PostDBfactory {

	public void insertPostList(PostRepository postRepository, List<Post> postList)	{
		
		Post post = null;
		
		for (int i=0; i<postList.size(); i++)	{
			post = postList.get(i);
			insertPost(postRepository, post);
		}
	}
	
	public void insertPost(PostRepository postRepository, Post post)	{
		
		Long userId = post.getUserId();
		String title = post.getTitle();
		String body = post.getBody();
		postRepository.save(new Post(userId, title, body));		
	}
	
}
