package com.omz.demo.review.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omz.demo.review.dto.ReviewLikeDTO;
import com.omz.demo.review.entity.ReviewLikeEntity;
import com.omz.demo.review.repository.ReviewLikeRepository;

@Service
@Transactional
public class ReviewLikeServiceImp implements ReviewLikeService{
	
	@Autowired
	private ReviewLikeRepository reviewLikeRepository;

	@Override
	public void saveProcess(ReviewLikeDTO dto) {
		ReviewLikeEntity entity = ReviewLikeDTO.toEntity(dto);
		reviewLikeRepository.save(entity);		
	}
	
	@Override
	public long countProcess(long reviewId) {
		return reviewLikeRepository.countByReviewId(reviewId);
	}

	@Override
	public void deleteProcess(long reviewId, String clientId) {
		reviewLikeRepository.deleteByReviewIdAndClientId(reviewId, clientId);
	}
	
}
