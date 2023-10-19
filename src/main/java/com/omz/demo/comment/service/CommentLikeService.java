package com.omz.demo.comment.service;

import com.omz.demo.comment.dto.CommentLikeDTO;

public interface CommentLikeService {
	public void saveProcess(CommentLikeDTO dto);
	public long countProcess(long commentId);
	public void deleteProcess(long commentId, String clientId);
	public void deleteSequence(long commentId);
}
