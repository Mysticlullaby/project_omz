package com.omz.demo.members.dto;

import org.springframework.stereotype.Component;

import com.omz.demo.members.entity.MembersEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MembersDTO {
	private String memberEmail; // 이메일
	private String memberPass; // 비밀번호
	private String memberName; // 이름
	private String memberPhone; // 전화번호
	private long memberType; // 회원구분 일반회원 1, 관리자 2 / long으로 변경
	private boolean rememberEmail; // 자동 로그인
	private String authRole;

	// 기존 디티오에서 몇개빼고 게터세터랑 생성자 지웟는데 왜지웟을까? 다른 방법이 잇을텐데 그게 뭘까

	public boolean isRememberEmail() {
		return rememberEmail;
	}

	public boolean matchPassword(String memberPass) {
		return this.memberPass.equals(memberPass);
	}

	// dto to Entity
	public static MembersEntity toEntity(MembersDTO dto) {
		MembersEntity entity = new MembersEntity();
		entity.setMemberEmail(dto.getMemberEmail());
		entity.setMemberPass(dto.getMemberPass());
		entity.setMemberName(dto.getMemberName());
		entity.setMemberPhone(dto.getMemberPhone());
		entity.setMemberType(dto.getMemberType());

		return entity;
		// 이부분에서 dto로 받아온거를 엔티티로 변경해준거임 그러고 리턴으로 엔티티를 내보냄
	}

	// Entity to dto
	public static MembersDTO toDto(MembersEntity entity) {
		MembersDTO dto = new MembersDTO();
		dto.setMemberEmail(entity.getMemberEmail());
		dto.setMemberPass(entity.getMemberPass());
		dto.setMemberName(entity.getMemberName());
		dto.setMemberPhone(entity.getMemberPhone());
		dto.setMemberType(entity.getMemberType());

		return dto;
	}
}
