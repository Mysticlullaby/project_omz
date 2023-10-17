package com.omz.demo.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omz.demo.comment.entity.CommentLikeEntity;

public interface CommentLikeRepository extends JpaRepository<CommentLikeEntity, Long>{
	public long countByCommentId(long commentId);
	public CommentLikeEntity findByCommentIdAndClientId(long commentId, String clientId);
	public void deleteByCommentIdAndClientId(long commentId, String clientId);
	public void deleteByCommentId(long commentId);
}
