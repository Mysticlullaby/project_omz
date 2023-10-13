package com.omz.demo.comment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PutMapping("/comment/update")
	public void updateExecute(CommentDTO dto) {
		commentService.saveProcess(dto);
	}
	
	@DeleteMapping("/comment/delete/{commentId}")
	public void deleteExecute(@PathVariable("commentId") long commentId) {
		commentService.deleteProcess(commentId);
	}
	
	@GetMapping("/comment/list/{reviewId}/{clientId}")
	public List<CommentDTO> listExecute(@PathVariable("reviewId") long reviewId, @PathVariable("clientId") String clientId){
		return commentService.listProcess(reviewId, clientId);
	}
}
