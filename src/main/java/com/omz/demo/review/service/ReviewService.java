package com.omz.demo.review.service;

import java.util.List;

import com.omz.demo.review.dto.ReviewDTO;

public interface ReviewService {
	public void saveProcess(ReviewDTO dto);
	public List<ReviewDTO> listProcess(long movieId);
}
