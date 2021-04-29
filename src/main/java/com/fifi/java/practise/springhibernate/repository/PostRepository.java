package com.fifi.java.practise.springhibernate.repository;



import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.fifi.java.practise.springhibernate.obj.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

	
//	@Query("SELECT s FROM Post s where CREATEDAT BETWEEN :startDate AND :endDate AND PARENTID = NULL")
//	List<Post> findPostByDate (@Param("startDate") Date startDate,  @Param("endDate") Date endDate);
	
//	@Query("SELECT s FROM Post s where PARENTID = :parentId")
//	List<Post> findCommentByPostId (@Param("parentId") Long parentId);
	
	
}

