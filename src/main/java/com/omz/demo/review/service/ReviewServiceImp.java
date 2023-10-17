package com.omz.demo.review.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omz.demo.comment.entity.CommentEntity;
import com.omz.demo.comment.repository.CommentLikeRepository;
import com.omz.demo.comment.repository.CommentRepository;
import com.omz.demo.movie.entity.ViewCountEntity;
import com.omz.demo.movie.repository.ViewCountRepository;
import com.omz.demo.review.dto.ReviewDTO;
import com.omz.demo.review.dto.ReviewPageDTO;
import com.omz.demo.review.entity.ReviewEntity;
import com.omz.demo.review.repository.ReviewLikeRepository;
import com.omz.demo.review.repository.ReviewRepository;

@Service
@Transactional
public class ReviewServiceImp implements ReviewService{

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private ReviewLikeRepository reviewLikeRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private CommentLikeRepository commentLikeRepository;
	
	@Autowired
	private ViewCountRepository viewCountRepository;
	
	@Override
	public void saveProcess(ReviewDTO dto) {
		ReviewEntity entity = ReviewDTO.toEntity(dto);
		reviewRepository.save(entity);
		
		if(viewCountRepository.findByMovieIdAndClientId(entity.getMovieId(), entity.getClientId()) == null) {
			ViewCountEntity vcEntity = new ViewCountEntity();
			vcEntity.setMovieId(entity.getMovieId());
			vcEntity.setClientId(entity.getClientId());
			viewCountRepository.save(vcEntity);
		}
	}

	@Override
	public List<ReviewDTO> listProcess(long movieId, String clientId) {
		List<ReviewDTO> dtoList = new ArrayList<>();
		List<ReviewEntity> entityList = reviewRepository.getReviewList(movieId);
		
		for(ReviewEntity entity : entityList) {
			ReviewDTO dto = ReviewDTO.toDto(entity);
			
			if(clientId != null) {
				if(reviewLikeRepository.findByReviewIdAndClientId(entity.getReviewId(), clientId) != null) {
					dto.setLikeCheck(true);
				};
			}
			
			dto.setLikeCount(reviewLikeRepository.countByReviewId(entity.getReviewId()));
			dto.setCommentCount(commentRepository.countByReviewId(entity.getReviewId()));			
			dtoList.add(dto);
		}
		
		return dtoList;
	}

	@Override
	public Map<String, Object> pageProcess(long movieId, long currentPage, String clientId) {
		Map<String, Object> map = new HashMap<>();
		long totalCount = reviewRepository.count();
		ReviewPageDTO pdto = new ReviewPageDTO(currentPage, totalCount);
		
		List<ReviewEntity> eList = reviewRepository.getReviewPage(movieId, pdto);
		List<ReviewDTO> dtoList = new ArrayList<>();
		
		for(ReviewEntity entity : eList) {
			ReviewDTO dto = ReviewDTO.toDto(entity);
			
			if(clientId != null) {
				if(reviewLikeRepository.findByReviewIdAndClientId(entity.getReviewId(), clientId) != null) {
					dto.setLikeCheck(true);
				};
			}
			
			dto.setLikeCount(reviewLikeRepository.countByReviewId(entity.getReviewId()));
			dto.setCommentCount(commentRepository.countByReviewId(entity.getReviewId()));
			dtoList.add(dto);
		}
		
		map.put("reviewPage", dtoList);
		map.put("pv", pdto);
		
		return map;
	}

	@Override
	public void deleteProcess(long reviewId) {
		//리뷰에 달린 댓글들 목록 가져오기
		List<CommentEntity> commentList = commentRepository.findByReviewId(reviewId);
		
		//가져온 목록의 댓글들 삭제하기
		for(CommentEntity comment : commentList) {
			//댓글에 달린 좋아요 먼저 삭제하기
			commentLikeRepository.deleteByCommentId(comment.getCommentId());
			//댓글 삭제
			commentRepository.deleteById(comment.getCommentId());
		}
		
		reviewLikeRepository.deleteByReviewId(reviewId);
		reviewRepository.deleteById(reviewId);		
	}

	@Override
	public ReviewDTO getProcess(long reviewId, String clientId) {
		ReviewDTO dto = ReviewDTO.toDto(reviewRepository.findByReviewId(reviewId));
		System.out.println(dto.getReviewId());
		
		if(clientId != null) {
			if(reviewLikeRepository.findByReviewIdAndClientId(reviewId, clientId) != null) {
				dto.setLikeCheck(true);
			}
		}

		dto.setLikeCount(reviewLikeRepository.countByReviewId(reviewId));
		dto.setCommentCount(commentRepository.countByReviewId(reviewId));		
		return dto;
	}

}
