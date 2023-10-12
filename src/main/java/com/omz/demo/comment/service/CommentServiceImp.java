package com.omz.demo.comment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omz.demo.comment.dto.CommentDTO;
import com.omz.demo.comment.entity.CommentEntity;
import com.omz.demo.comment.repository.CommentLikeRepository;
import com.omz.demo.comment.repository.CommentRepository;

@Service
@Transactional
public class CommentServiceImp implements CommentService{
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private CommentLikeRepository commentLikeRepository;

	@Override
	public void saveProcess(CommentDTO dto) {
		commentRepository.save(CommentDTO.toEntity(dto));
	}

	@Override
	public List<CommentDTO> listProcess(long reviewId, String clientId) {
		List<CommentDTO> dtoList = new ArrayList<>();
		List<CommentEntity> entityList = commentRepository.findByReviewIdOrderByRegDateDesc(reviewId);
		for(CommentEntity entity : entityList) {
			CommentDTO dto = CommentDTO.toDto(entity);
			dto.setLikeCount(commentLikeRepository.countByCommentId(dto.getCommentId()));
			if(commentLikeRepository.findByCommentIdAndClientId(dto.getCommentId(), clientId) != null) {
				dto.setLikeCheck(true);
			}
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public void deleteProcess(long commentId) {
		commentRepository.deleteById(commentId);	
	}		

}
