package com.omz.demo.comment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omz.demo.comment.dto.CommentLikeDTO;
import com.omz.demo.comment.repository.CommentLikeRepository;

@Service
@Transactional
public class CommentLikeServiceImp implements CommentLikeService{
	
	@Autowired
	private CommentLikeRepository commentLikeRepository;

	@Override
	public void saveProcess(CommentLikeDTO dto) {
		commentLikeRepository.save(CommentLikeDTO.toEntity(dto));	
	}

	@Override
	public long countProcess(long commentId) {
		return commentLikeRepository.countByCommentId(commentId);
	}

	@Override
	public void deleteProcess(long commentId, String clientId) {
		commentLikeRepository.deleteByCommentIdAndClientId(commentId, clientId);	
	}
	
	@Override
	public void deleteSequence(long commentId) {
		commentLikeRepository.deleteByCommentId(commentId);
	}

}
