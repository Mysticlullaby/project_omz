package com.omz.demo.client.dto;

import com.omz.demo.client.entity.ClientEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
	private String clientId;
	private String clientPass;
	private String clientName;
	private String phone;
	private String email;
	private String gender;
	private long age;
	private String mbti;
	private String regDate;
	private String grade; // 회원, 관리자 구분용

	public static ClientEntity toEntity(ClientDTO dto) {
		return ClientEntity.builder()
				.clientId(dto.getClientId())
				.clientPass(dto.getClientPass())
				.clientName(dto.getClientName())
				.phone(dto.getPhone())
				.email(dto.getEmail())
				.gender(dto.getGender())
				.age(dto.getAge())
				.mbti(dto.getMbti())
				.regDate(dto.getRegDate())
				.grade(dto.getGrade())
				.build();
	}
	
	public static ClientDTO toDto(ClientEntity entity) {
		return ClientDTO.builder()
				.clientId(entity.getClientId())
				.clientPass(entity.getClientPass())
				.clientName(entity.getClientName())
				.phone(entity.getPhone())
				.email(entity.getEmail())
				.gender(entity.getGender())
				.age(entity.getAge())
				.mbti(entity.getMbti())
				.regDate(entity.getRegDate())
				.grade(entity.getGrade())
				.build();
	}
	
}
