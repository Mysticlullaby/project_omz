package com.omz.demo.review.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omz.demo.review.dto.ReviewDTO;
import com.omz.demo.review.entity.ReviewEntity;
import com.omz.demo.review.repository.ReviewRepository;

@Service
@Transactional
public class ReviewServiceImp implements ReviewService{

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Override
	public void saveProcess(ReviewDTO dto) {
		ReviewEntity entity = ReviewDTO.toEntity(dto);
		reviewRepository.save(entity);
	}

}
