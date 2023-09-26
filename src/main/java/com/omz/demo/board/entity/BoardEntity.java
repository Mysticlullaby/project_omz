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
	@Column(insertable = false)
	private long omzboardId;
	
	@Column(insertable = false)
	private long readCount;
	
	@Column(insertable = false)
	private long boardRef;
	
	@Column(insertable = false)
	private long reStep;
	
	@Column(insertable = false)
	private long reLevel ;
	
	@Column
	private String subject;
	
	@Column(name="board_content")
	private String boardContent ;
	
	@Column(name="reg_date", insertable = false)
	private String regDate;

	@Column(insertable = false)
	private String upload;
	
	@ManyToOne
	@JoinColumn(name = "client_id", insertable = false) //fk
	private ClientEntity clientEntity;



}
