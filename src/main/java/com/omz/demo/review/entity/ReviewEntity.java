package com.omz.demo.review.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="review")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewEntity {
	
	@Id
	@Column(name="review_id")
	private long reviewId;
	
	@Column(name="movie_id")
	private long movieId;
	
	@Column(name="client_id")
	private String clientId;
	
	@Column(name="review_content")
	private String reviewContent;
	
	@Column
	private long rating;
	
	@Column(name="reg_date")
	private String regDate;
	
	
}
