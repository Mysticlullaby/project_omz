package com.omz.demo.comment.entity;

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
import com.omz.demo.client.repository.ClientRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="omz_comment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@SequenceGenerator(
		name="comment_num_gen", 
		sequenceName = "comment_num_seq",
		initialValue = 1,
		allocationSize = 1)
public class CommentEntity {
//	comment_id      NUMBER NOT NULL,
//  review_id       NUMBER NOT NULL,
//  client_id       VARCHAR2(64) NOT NULL,
//  comment_content VARCHAR2(512),
//  reg_date        TIMESTAMP,
//  edit_date       TIMESTAMP
	
	@Id
	@Column(name="comment_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_num_gen")
	private long commentId;
	
	@Column(name="review_id")
	private long reviewId;

	@Column(name="client_id") 
	private String clientId;
	
	@Column(name="comment_content")
	private String commentContent;
	
	@CreationTimestamp
	@Column(name="reg_date", updatable = false)
	private LocalDateTime regDate;
	
	@UpdateTimestamp
	@Column(name="edit_date")
	private LocalDateTime editDate;
	
}
