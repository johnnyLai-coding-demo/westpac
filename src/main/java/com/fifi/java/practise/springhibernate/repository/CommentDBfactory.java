package com.fifi.java.practise.springhibernate.repository;

import java.util.List;


import com.fifi.java.practise.springhibernate.obj.Comment;

public class CommentDBfactory {

	public static void insertCommentList(CommentRepository commentRepository, List<Comment> commentList)	{
		
		Comment comment = null;
		
		for (int i=0; i<commentList.size(); i++)	{
			comment = commentList.get(i);
			insertComment(commentRepository, comment);
		}
	}
	
	public static void insertComment(CommentRepository commentRepository, Comment post)	{
		
		Long postId = post.getPostId();
		String name = post.getName();
		String email = post.getEmail();
		String body = post.getBody();
		commentRepository.save(new Comment(postId, name, email, body));		
	}	
	
}
