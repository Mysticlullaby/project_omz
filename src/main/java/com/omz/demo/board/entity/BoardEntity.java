package com.omz.demo.board.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.omz.demo.client.entity.ClientEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "omzboard")
@Getter
@Setter
@ToString
@NoArgsConstructor //파라미터 없는 생성자 자동생성
@AllArgsConstructor //모든 멤버변수 제공
@Builder
//@SequenceGenerator(
//name = "token_gen",
//sequenceName = "omzboard_num_seq",
//initialValue = 1, 
//allocationSize = 1)

public class BoardEntity {
	
	@Id //pk
	@Column(name = "omzboard_id")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_gen")
	private long omzboardId;
	
	@Column(name = "client_id")
	private String clientId;
	
//	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ClientEntity.class)
//	@JoinColumn(name = "client_id")
//	private ClientEntity clientEntity;
	
	@Column(name = "read_count")
	private long readCount;
	
	@Column(name = "board_ref")
	private long boardRef;
	
	@Column(name = "re_step")
	private long reStep;
	
	@Column(name = "re_level")
	private long reLevel;
	
	@Column(name = "subject")
	private String subject;
	
	@Column(name = "board_content")
	private String boardContent ;
	
	@Column(name = "reg_date")
	private LocalDateTime regDate;
	
	@Column(name = "edit_date")
	private LocalDateTime editDate;

	@Column(name = "upload")
	private String upload;

}
