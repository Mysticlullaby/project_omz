package com.omz.demo.comment.entity;

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
@Table(name="comment_like")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(
		name="comment_like_num_gen", 
		sequenceName = "comment_like_num_seq",
		initialValue = 1,
		allocationSize = 1)
public class CommentLikeEntity {
//	commentlike_id NUMBER NOT NULL,
//  client_id      VARCHAR2(64) NOT NULL,
//  comment_id     NUMBER NOT NULL
	
	@Id
	@Column(name="commentlike_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_like_num_gen")
	private long commentlikeId;
	
	@Column(name = "client_id")
	private String clientId;
	
//	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ClientEntity.class)
//	@JoinColumn(name = "client_id")
//	private ClientEntity clientEntity;
	
	@Column(name="comment_id")
	private long commentId;
	
}
