package com.omz.demo.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omz.demo.comment.entity.CommentEntity;

//메소드 이름 지을 때 규칙: find+(테이블명(엔티티명)-생략가능)+By+컬럼이름
public interface CommentRepository extends JpaRepository<CommentEntity, Long>{
	public List<CommentEntity> findByReviewIdOrderByRegDateDesc(long reviewId);
	public long countByReviewId(long reviewId);

}
