package com.omz.demo.comment.dto;

import java.time.LocalDateTime;

import com.omz.demo.comment.entity.CommentEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO {
//	  comment_id      NUMBER NOT NULL,
//    review_id       NUMBER NOT NULL,
//    client_id       VARCHAR2(64) NOT NULL,
//    comment_content VARCHAR2(512),
//    reg_date        TIMESTAMP,
//    edit_date       TIMESTAMP
	
	private long commentId;
	private long reviewId;
	private String clientId;
	private String commentContent;
	private LocalDateTime regDate;
	private LocalDateTime editDate;
	
	private long likeCount;
	private boolean likeCheck;
	
	public static CommentEntity toEntity(CommentDTO dto) {
		return CommentEntity.builder()
				.commentId(dto.getCommentId())
				.reviewId(dto.getReviewId())
				.clientId(dto.getClientId())
				.commentContent(dto.getCommentContent())
				.regDate(dto.getRegDate())
				.editDate(dto.getEditDate())
				.build();
	}
	
	public static CommentDTO toDto(CommentEntity entity) {
		return CommentDTO.builder()
				.commentId(entity.getCommentId())
				.reviewId(entity.getReviewId())
				.clientId(entity.getClientId())
				.commentContent(entity.getCommentContent())
				.regDate(entity.getRegDate())
				.editDate(entity.getEditDate())
				.build();
	}
	
}
