package com.omz.demo.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.omz.demo.comment.entity.CommentEntity;

//메소드 이름 지을 때 규칙: find+(테이블명(엔티티명)-생략가능)+By+컬럼이름
public interface CommentRepository extends JpaRepository<CommentEntity, Long>{
	
	@Query(value = "SELECT b.*"
				+ " FROM (SELECT c.comment_id, count(l.commentlike_id) AS like_count"
					  + " FROM omz_comment c, comment_like l"
					  + " WHERE c.comment_id = l.comment_id(+) AND c.review_id = :reviewId"
					  + " GROUP BY c.comment_id)a, omz_comment b"
				+ " WHERE a.comment_id = b.comment_id"
				+ " ORDER BY like_count DESC, reg_date", nativeQuery = true)
	public List<CommentEntity> findByReviewId(@Param("reviewId") long reviewId);
	
	public long countByReviewId(long reviewId);

}
