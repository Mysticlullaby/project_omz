package com.omz.demo.review.dto;

import com.omz.demo.review.entity.ReviewEntity;

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
public class ReviewDTO {
//	review_id      NUMBER NOT NULL,
//    movie_id       NUMBER NOT NULL,
//    client_id      VARCHAR2(64) NOT NULL,
//    review_content VARCHAR2(1024),
//    rating         NUMBER,
//    reg_date       VARCHAR2(32)
	private long reviewId;
	private long movieId;
	private String clientId;
	private String reviewContent;
	private long rating;
	private String regDate;
	
	public static ReviewEntity toEntity(ReviewDTO dto) {
		return ReviewEntity.builder()
				.reviewId(dto.getReviewId())
				.movieId(dto.getMovieId())
				.clientId(dto.getClientId())
				.reviewContent(dto.getReviewContent())
				.rating(dto.getRating())
				.regDate(dto.getRegDate())
				.build();
	}
	
	public static ReviewDTO toDto(ReviewEntity entity) {
		return null;
	}

}
