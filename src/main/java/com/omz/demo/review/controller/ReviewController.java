package com.omz.demo.review.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/review/list/{movieId}")
	public List<ReviewDTO> listExecute(@PathVariable("movieId") long movieId){
		return reviewService.listProcess(movieId);
	}
	
	@GetMapping("/review/page/{movieId}/{currentPage}")
	public Map<String, Object> pageExecute(@PathVariable("movieId") long movieId, @PathVariable("currentPage") long currentPage){
		System.out.println("movieId for review: " + movieId);
		System.out.println("currentPage: " + currentPage);
		return reviewService.pageProcess(movieId, currentPage);		
	}
	
	@GetMapping("/review/detail/{reviewId}")
	public ReviewDTO getExecute(@PathVariable("reviewId") long reviewId) {
		ReviewDTO data = reviewService.getProcess(reviewId);
		System.out.println("sending reviewDetail data: " + data);
		return data;
	}
	
	@DeleteMapping("/review/delete/{reviewId}")
	public void deleteExecute(@PathVariable("reviewId") long reviewId) {
		reviewService.deleteProcess(reviewId);
	}
	
	@PutMapping("/review/update")
	public void updateExecute(ReviewDTO dto) {
		System.out.println("movieId from ReviewDTO in updateExecute: " + dto.getMovieId());
		System.out.println("reviewId from ReviewDTO in updateExecute: " + dto.getReviewId());
		reviewService.saveProcess(dto);
	}
}
