package com.omz.demo.members.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "omz_client")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MembersEntity {
	@Id //기본키에는 이 어노테이션 해줘야함
	@Column(name = "member_email" )
	private String memberEmail; //이메일
	
	@Column(name = "member_pass", length = 100, nullable = false ) //길이default 255
	private String memberPass; //비밀번호
	
	@Column(name = "member_name", nullable = false )
	private String memberName; //이름
	
	@Column(name = "member_phone", nullable = false )
	private String memberPhone; //전화번호
	
	@Column(name = "member_type" )
	private long memberType;  //회원구분  일반회원 1, 관리자 2

}
