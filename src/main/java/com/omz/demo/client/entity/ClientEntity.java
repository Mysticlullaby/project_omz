package com.omz.demo.client.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

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
	@Column(name = "client_id")
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
