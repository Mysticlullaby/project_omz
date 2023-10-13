package com.omz.demo.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omz.demo.comment.dto.CommentLikeDTO;
import com.omz.demo.comment.service.CommentLikeService;

@RestController
@CrossOrigin("*")
public class CommentLikeController {
	
	@Autowired
	private CommentLikeService commentLikeService;
	
	@PostMapping("/comment/like")
	public void saveExecute(CommentLikeDTO dto) {
		commentLikeService.saveProcess(dto);
	}
	
	@DeleteMapping("/comment/delete/{commentId}/{clientId}")
	public void deleteExecute(@PathVariable("commentId") long commentId, @PathVariable("clientId") String clientId) {
		commentLikeService.deleteProcess(commentId, clientId);
	}

}
