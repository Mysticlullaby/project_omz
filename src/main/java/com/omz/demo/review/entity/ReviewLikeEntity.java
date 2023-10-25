package com.omz.demo.review.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.omz.demo.client.entity.ClientEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="review_like")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(
		name="review_like_num_gen", 
		sequenceName = "review_like_num_seq",
		initialValue = 1,
		allocationSize = 1)
public class ReviewLikeEntity {
	
	@Id
	@Column(name="reviewlike_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_like_num_gen")
	private long reviewlikeId;
	
	@Column(name = "client_id")
	private String clientId;
	
	@Column(name="review_id")
	private long reviewId;
}
