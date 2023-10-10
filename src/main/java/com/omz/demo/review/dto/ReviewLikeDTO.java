package com.omz.demo.review.dto;

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
	private String client_id;
	private long reviewId;
	
}
