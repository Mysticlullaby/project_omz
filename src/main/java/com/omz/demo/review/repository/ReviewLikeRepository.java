package com.omz.demo.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omz.demo.review.entity.ReviewLikeEntity;

//메소드 이름 지을 때 규칙: find+(테이블명(엔티티명)-생략가능)+By+컬럼이름
public interface ReviewLikeRepository extends JpaRepository<ReviewLikeEntity, Long>{
	public long countByReviewId(long reviewId);
	public ReviewLikeEntity findByReviewIdAndClientId(long reviewId, String clientId);
	public void deleteByReviewIdAndClientId(long reviewId, String clientId);
}
