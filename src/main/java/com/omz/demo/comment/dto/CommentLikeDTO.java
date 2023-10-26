package com.omz.demo.comment.dto;

import com.omz.demo.client.entity.ClientEntity;
import com.omz.demo.comment.entity.CommentLikeEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentLikeDTO {
//	  commentlike_id NUMBER NOT NULL,
//    client_id      VARCHAR2(64) NOT NULL,
//    comment_id     NUMBER NOT NULL
	
	private long commentlikeId;
	private String clientId;
	private long commentId;
	
	public static CommentLikeDTO toDto(CommentLikeEntity entity) {
		return CommentLikeDTO.builder()
				.commentlikeId(entity.getCommentlikeId())
				.clientId(entity.getClientId())
				.commentId(entity.getCommentId())
				.build();
	}
	
	public static CommentLikeEntity toEntity(CommentLikeDTO dto) {
		return CommentLikeEntity.builder()
				.commentlikeId(dto.getCommentlikeId())
				.clientId(dto.getClientId())
				.commentId(dto.getCommentId())
				.build();
	}

}
