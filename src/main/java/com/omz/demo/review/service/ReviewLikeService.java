package com.omz.demo.review.service;

import com.omz.demo.review.dto.ReviewLikeDTO;

public interface ReviewLikeService {
	public void saveProcess(ReviewLikeDTO dto);
	public long countProcess(long reviewId);
	public void deleteProcess(long reviewId, String clientId);
}
