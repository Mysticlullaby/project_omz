package com.omz.demo.review.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omz.demo.review.dto.ReviewDTO;
import com.omz.demo.review.service.ReviewService;

@CrossOrigin("*")
@RestController
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/review/write")
	public void writeExecute(ReviewDTO dto) {
		reviewService.saveProcess(dto);
	}

}
