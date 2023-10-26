package com.omz.demo.client.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import com.omz.demo.board.entity.BoardEntity;
import com.omz.demo.comment.entity.CommentEntity;
import com.omz.demo.comment.entity.CommentLikeEntity;
import com.omz.demo.movie.entity.ViewCountEntity;
import com.omz.demo.review.entity.ReviewEntity;
import com.omz.demo.review.entity.ReviewLikeEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "omz_client")
@Getter
@Setter
@Entity
@Builder
@ToString
@Component
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {

	@Id
	@Column(name = "client_id") //Repository에서 joinColumn을 사용할 필요가 사라짐
	private String clientId;
 
	@Column(name = "client_pass")
	private String clientPass;

	@Column(name = "client_name")
	private String clientName;

//	@Column
//	private String phone;
// 
//	@Column // (unique = true)
//	private String email; 
//
//	@Column
//	private String gender;
//
//	@Column
//	private long age;

	@Column
	private String mbti;

//	@Column(name = "reg_date", insertable = false)
//	@CreationTimestamp
//	private String regDate;

	@Column
	private String grade; // 회원 구분용

}
