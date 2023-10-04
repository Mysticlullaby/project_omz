package com.omz.demo.review.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omz.demo.review.dto.ReviewDTO;
import com.omz.demo.review.dto.ReviewPageDTO;
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

	@Override
	public List<ReviewDTO> listProcess(long movieId) {
		List<ReviewDTO> dtoList = new ArrayList<>();
		List<ReviewEntity> entityList = reviewRepository.getReviewList(movieId);
		
		for(ReviewEntity entity : entityList) {
			dtoList.add(ReviewDTO.toDto(entity));
		}
		
		return dtoList;
	}

	@Override
	public Map<String, Object> pageProcess(long movieId, long currentPage) {
		Map<String, Object> map = new HashMap<>();
		long totalCount = reviewRepository.count();
		ReviewPageDTO pdto = new ReviewPageDTO(currentPage, totalCount);
		
		List<ReviewEntity> eList = reviewRepository.getReviewPage(movieId, pdto);
		List<ReviewDTO> dtoList = new ArrayList<>();
		
		for(ReviewEntity entity : eList) {
			dtoList.add(ReviewDTO.toDto(entity));
		}
		
		map.put("reviewPage", dtoList);
		map.put("pv", pdto);
		
		return map;
	}

	@Override
	public void deleteProcess(long reviewId) {
		reviewRepository.deleteById(reviewId);		
	}

	@Override
	public ReviewDTO getProcess(long reviewId) {
		return ReviewDTO.toDto(reviewRepository.findByReviewId(reviewId));
	}

}
