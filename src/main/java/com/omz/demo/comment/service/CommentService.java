package com.omz.demo.comment.service;

import java.util.List;

import com.omz.demo.comment.dto.CommentDTO;

public interface CommentService {
	public void saveProcess(CommentDTO dto);
	public List<CommentDTO> listProcess(long reviewId);
}
