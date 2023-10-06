package com.omz.demo.comment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omz.demo.comment.dto.CommentDTO;
import com.omz.demo.comment.service.CommentService;

@RestController
@CrossOrigin("*")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/comment/write")
	public void saveExecute(CommentDTO dto) {
		commentService.saveProcess(dto);
	}
	
	@GetMapping("/comment/list/{reviewId}")
	public List<CommentDTO> listExecute(@PathVariable("reviewId") long reviewId){
		System.out.println("listExecute processing");
		return commentService.listProcess(reviewId);
	}
}