package com.fifi.java.practise.springhibernate.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fifi.java.practise.springhibernate.obj.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

	
	@Query("SELECT s FROM Post s where CREATEDAT BETWEEN :startDate AND :endDate")
	List<Post> findByDate (@Param("startDate") Date startDate,  @Param("endDate") Date endDate);
	
	@Query("SELECT s FROM Post s where PARENTID = :parentId")
	List<Post> findByParentId (@Param("parentId") Long parentId);
}

