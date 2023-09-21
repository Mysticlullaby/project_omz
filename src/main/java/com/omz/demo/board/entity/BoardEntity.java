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
public class BoardEntity {
	
	@Id //pk
	@Column(name="omzboard_id")
	private long omzboardId;
	
	@Column(name="read_count")
	private long readCount;
	
	@Column(name="board_ref")
	private long boardRef;
	
	@Column(name="re_step")
	private long reStep;
	
	@Column(name="re_level")
	private long reLevel ;
	
	@Column
	private String subject;
	
	@Column(name="board_content")
	private String boardContent ;
	
	@Column(name="reg_date", insertable = false)
	private String regDate;

	@Column
	private String upload;
	
	@ManyToOne
	@JoinColumn(name = "client_id") //fk
	private ClientEntity clientEntity;



}
