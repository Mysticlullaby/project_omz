package com.omz.demo.board.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.omz.demo.members.entity.MembersEntity;

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
public class BoardEntity {
	
	@Id //pk
	@Column
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_gen")
	private long omzboard_id;
	@Column
	private long read_count, board_ref, re_step, re_level;
	@Column
	private String subject, board_content ;
	@Column(insertable = false)
	private Date reg_date;

	@Column
	private String upload;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private MembersEntity membersEntity;



}
