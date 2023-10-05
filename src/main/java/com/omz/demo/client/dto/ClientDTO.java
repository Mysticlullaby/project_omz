package com.omz.demo.client.dto;

import javax.validation.constraints.NotBlank;

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
//	@NotBlank(message = "아이디는 필수 입력사항 입니다.")
	private String clientId;
//	@NotBlank(message = "비밀번호는 필수 입력사항 입니다.")
	private String clientPass;
//	@NotBlank(message = "이름은 필수 입력사항 입니다.")
	private String clientName;
	private String phone;
	private String email;
	private String gender;
	private long age;
//	@NotBlank(message = "MBTI는 필수 입력사항 입니다.")
	private String mbti;
	private String regDate;
	private String grade; // 회원, 관리자 구분용
	private String authRole;	

	public static ClientEntity toEntity(ClientDTO dto) {
		return ClientEntity.builder()
				.clientId(dto.getClientId())
				.clientPass(dto.getClientPass())
				.clientName(dto.getClientName())
//				.phone(dto.getPhone())
//				.email(dto.getEmail())
//				.gender(dto.getGender())
//				.age(dto.getAge())
				.mbti(dto.getMbti())
//				.regDate(dto.getRegDate())
				.grade(dto.getGrade())
				.build();
	}
	
	public static ClientDTO toDto(ClientEntity entity) {
		return ClientDTO.builder()
				.clientId(entity.getClientId())
				.clientPass(entity.getClientPass())
				.clientName(entity.getClientName())
//				.phone(entity.getPhone())
//				.email(entity.getEmail())
//				.gender(entity.getGender())
//				.age(entity.getAge())
				.mbti(entity.getMbti())
//				.regDate(entity.getRegDate())
				.grade(entity.getGrade())
				.build();
	}
	
}
