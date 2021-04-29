package com.fifi.java.practise.springhibernate.repository;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fifi.java.practise.springhibernate.obj.Comment;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

	
//	@Query("SELECT s FROM Post s where CREATEDAT BETWEEN :startDate AND :endDate AND PARENTID = NULL")
//	List<Post> findPostByDate (@Param("startDate") Date startDate,  @Param("endDate") Date endDate);
	
	@Query("SELECT c FROM Comment c where POSTID = :postId")
	List<Comment> findCommentByPostId (@Param("postId") Long parentId);
	
	
}

