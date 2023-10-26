package com.omz.demo.review.dto;

import com.omz.demo.client.entity.ClientEntity;
import com.omz.demo.review.entity.ReviewLikeEntity;

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
public class ReviewLikeDTO {
//    reviewlike_id NUMBER NOT NULL,
//    client_id     VARCHAR2(64) NOT NULL,
//    review_id     NUMBER NOT NULL
	
	
	
	private long reviewlikeId;
	private String clientId;
	private long reviewId;
	
	public static ReviewLikeDTO toDto(ReviewLikeEntity entity) {
		return ReviewLikeDTO.builder()
				.reviewlikeId(entity.getReviewlikeId())
				.clientId(entity.getClientId())
				.reviewId(entity.getReviewId())
				.build();
	}
	
	public static ReviewLikeEntity toEntity(ReviewLikeDTO dto) {
		return ReviewLikeEntity.builder()
				.reviewlikeId(dto.getReviewlikeId())
				.clientId(dto.getClientId())
				.reviewId(dto.getReviewId())
				.build();
	}
	
}
