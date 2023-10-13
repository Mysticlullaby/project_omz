package com.omz.demo.movie.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="view_count")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(
		name="view_num_gen", 
		sequenceName = "view_num_seq",
		initialValue = 1,
		allocationSize = 1)
public class ViewCountEntity {
//	viewcount_id NUMBER NOT NULL,
//  client_id    VARCHAR2(64) NOT NULL,
//  movie_id     NUMBER NOT NULL,
//  reg_date     TIMESTAMP,
//  edit_date    TIMESTAMP
	
	@Id
	@Column(name="viewcount_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "view_num_gen")
	private long viewcountId;
	
	@Column(name="client_id")
	private String clientId;
	
	@Column(name="movie_id")
	private long movieId;
	
	@CreationTimestamp
	@Column(name="reg_date", updatable = false)
	private LocalDateTime regDate;

}
