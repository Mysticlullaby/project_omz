package com.omz.demo.review.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omz.demo.review.dto.ReviewLikeDTO;
import com.omz.demo.review.service.ReviewLikeService;

@CrossOrigin("*")
@RestController
public class ReviewLikeController {
	
	@Autowired
	private ReviewLikeService reviewLikeService;
	
	@PostMapping("/review/like")
	public void saveExecute(ReviewLikeDTO dto) {
		reviewLikeService.saveProcess(dto);
	}
	
	@DeleteMapping("/review/delete/{reviewId}/{clientId}")
	public void deleteExecute(@PathVariable("reviewId") long reviewId, @PathVariable("clientId") String clientId) {
		reviewLikeService.deleteProcess(reviewId, clientId);
	}
}
