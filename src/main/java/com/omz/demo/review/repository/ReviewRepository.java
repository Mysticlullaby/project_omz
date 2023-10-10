package com.omz.demo.review.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.omz.demo.review.dto.ReviewPageDTO;
import com.omz.demo.review.entity.ReviewEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long>{
	
	@Query(value="SELECT b.*"
			  + " FROM (SELECT a.*, rownum as rm"
				    + " FROM (SELECT r.*"
				  		  + " FROM (SELECT r.review_id, count(l.reviewlike_id) AS like_count"
				  			    + " FROM review r, review_like l"
				  			    + " WHERE r.review_id = l.review_id(+) AND r.movie_id = :movieId"
				  			    + " GROUP BY r.review_id"
				  			    + " ORDER BY count(l.reviewlike_id) DESC) x, review r"
				    + " WHERE x.review_id = r.review_id"
				  	+ " ORDER BY x.like_count DESC, r.reg_date DESC)a)b"
			  + " WHERE rm>=1 AND rm<=8", nativeQuery = true)
	List<ReviewEntity> getReviewList(@Param("movieId") long movieId);
	
	ReviewEntity findByReviewId(long id);
	
	@Query(value="SELECT b.*"
			  + " FROM (SELECT a.*, rownum as rm"
				    + " FROM (SELECT r.*"
				  		  + " FROM (SELECT r.review_id, count(l.reviewlike_id) AS like_count"
				  			    + " FROM review r, review_like l"
				  			    + " WHERE r.review_id = l.review_id(+) AND r.movie_id = :movieId"
				  			    + " GROUP BY r.review_id"
				  			    + " ORDER BY count(l.reviewlike_id) DESC) x, review r"
				    + " WHERE x.review_id = r.review_id"
				  	+ " ORDER BY x.like_count DESC, r.reg_date DESC)a)b"
			  + " WHERE rm>=:#{#pdto.startRow} AND rm<=:#{#pdto.endRow}", nativeQuery = true)
	List<ReviewEntity> getReviewPage(@Param("movieId") long movieId, @Param("pdto") ReviewPageDTO pdto);
	
}
