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
import com.omz.demo.review.entity.ReviewEntity;

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
//	@Column(name = "client_id") Repository에서 joinColumn을 사용할 필요가 사라짐
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
	
	// 일대다, 회원 한 명이 게시글, 리뷰, 코멘트를 여러 개 작성할 수 있으므로 client 기준으로 @OneToMany를 선언한다
	// client는 board, review, comment를 List로 가지며, 연관관계의 주인을 정하기 위해 @OneToMany에 mappedBy 속성을 추가한다
	@Transient
	@OneToMany(mappedBy = "clientEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BoardEntity> boardEntityList = new ArrayList<>();

	@Transient
	@OneToMany(mappedBy = "clientEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ReviewEntity> ReviewEntityList = new ArrayList<>();

	@Transient
	@OneToMany(mappedBy = "clientEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CommentEntity> CommentEntityList = new ArrayList<>();
 
	// 부모 Entity에서는 @OneToMany, 자식 Entity에서는 @ManyToOne이 되는 것이므로, 양방향으로 @OneToMany를 사용하면 자식 Entity에는 @ManyToOne이 같이 사용된다
	// 양방향 @OneToMany 일때는 부모 Entity에 @JoinColumn 어노테이션이 제거되고 @OneToMany의 mappedBy 속성을 추가해 자식 Entity와의 관계를 설정한다 
	// 이때 mappedBy 속성에는 자식 Entity에서 부모 Entity를 바라보는 변수이름을 지정합니다. 자식 Entity에서는 단방향 @ManyToOne와 동일하게 부모와의 관계를 지정해준다
}
