package com.omz.demo.review.service;

import java.util.List;
import java.util.Map;

import com.omz.demo.review.dto.ReviewDTO;

public interface ReviewService {
	public void saveProcess(ReviewDTO dto);
	public List<ReviewDTO> listProcess(long movieId);
	public Map<String, Object> pageProcess(long movieId, long currentPage, String clientId);
	public void deleteProcess(long reviewId);
	public ReviewDTO getProcess(long reviewId);
}
