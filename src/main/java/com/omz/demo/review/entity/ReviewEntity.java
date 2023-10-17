package com.omz.demo.review.entity;

import java.time.LocalDateTime;

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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.omz.demo.client.entity.ClientEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@EntityListeners(AuditingEntityListener.class)
@Table(name="review")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(
name="review_num_gen", 
sequenceName = "review_num_seq",
initialValue = 1,
allocationSize = 1)
public class ReviewEntity {
	
	@Id
	@Column(name="review_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_num_gen")
	private long reviewId;
	
	@Column(name="movie_id")
	private long movieId;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ClientEntity.class)
	@JoinColumn(name = "client_id")
	//@Column(name="client_id")
//	private String clientId;
	private ClientEntity clientEntity;
	
	@Column(name="review_content")
	private String reviewContent;
	
	@Column
	private long rating;
	
	@CreationTimestamp
	@Column(name="reg_date", updatable = false)
	private LocalDateTime regDate;
	
//	@UpdateTimestamp
//	@Column(name="edit_date")
//	private LocalDateTime editDate;
	
}
