package com.fifi.java.practise.springhibernate.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fifi.java.practise.springhibernate.obj.Comment;
import com.fifi.java.practise.springhibernate.obj.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

	
//	@Query("SELECT s FROM Post s where CREATEDAT BETWEEN :startDate AND :endDate AND PARENTID = NULL")
//	List<Post> findPostByDate (@Param("startDate") Date startDate,  @Param("endDate") Date endDate);
	
//	@Query("SELECT s FROM Post s where PARENTID = :parentId")
//	List<Post> findCommentByPostId (@Param("parentId") Long parentId);

	@Query(value = "select P.ID, P.BODY, P.TITLE, P.USERID, count(*) as NUMOFCOMMENTS from Post P left join Comment C on P.ID = C.POSTID where P.ID > :indexId GROUP BY P.ID limit 10", nativeQuery = true)
	List<Post> findSomePostsWithCommentCount (@Param("indexId") Long indexId);	
	
	@Query(value = "select P.ID, P.BODY, P.TITLE, P.USERID, count(*) as NUMOFCOMMENTS from Post P left join Comment C on P.ID = C.POSTID GROUP BY P.ID", nativeQuery = true)
	List<Post> findAllPostsWithCommentCount ();
	
	@Query(value = "select P.ID, P.BODY, P.TITLE, P.USERID,  (select count(*) from COMMENT where POSTID = P.ID )as NUMOFCOMMENTS from POST P where P.ID = :postId", nativeQuery = true)
	Post findPostById (@Param("postId") Long postId);	
}

