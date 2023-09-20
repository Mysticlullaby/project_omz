package  com.omz.demo.board.dto;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.omz.demo.board.entity.BoardEntity;
import com.omz.demo.members.dto.MembersDTO;
import com.omz.demo.members.entity.MembersEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//import members.dto.MembersDTO;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {
	private long omzboard_id, read_count, board_ref, re_step, re_level;
	private String subject, board_content, client_id;
	private Date reg_date;

	//form페이지에서 파일첨부를 받아 처리해주는 멤버변수
	private MultipartFile filename;
	
	//board테이블의 파일첨부를 처리해주는 멤버변수
	private String upload;
	// filename 변수랑 관련있는 컬럼명이라서 따로 뺀듯
	
	private MembersDTO membersDTO= new MembersDTO();

	public static BoardDTO toDto(BoardEntity entity) {
		
		  MembersDTO membersDTO=new MembersDTO();
		  membersDTO.setMemberEmail(entity.getMembersEntity().getMemberEmail());
		  membersDTO.setMemberName(entity.getMembersEntity().getMemberName());
		 
		return BoardDTO.builder()
				.num(entity.getNum())
				.readcount(entity.getReadcount())
				.ref(entity.getRef())
				.re_step(entity.getRe_step())
				.re_level(entity.getRe_level())
				.subject(entity.getSubject())
				.content(entity.getContent())
				.ip(entity.getIp())
				.reg_date(entity.getReg_date())
				.upload(entity.getUpload())
				.membersDTO(membersDTO)
				.build();
	}
	
	//z컨트롤러에서 디비로 넘겨줄때 변경해야하는 부분
	public static BoardEntity toEntity(BoardDTO dto) {
		
		MembersEntity memberEntity=new MembersEntity();
		memberEntity.setMemberEmail(dto.getMembersDTO().getMemberEmail());
		memberEntity.setMemberName(dto.getMembersDTO().getMemberName());
		
		return BoardEntity.builder()
				.num(dto.getNum())
				.readcount(dto.getReadcount())
				.ref(dto.getRef())
				.re_step(dto.getRe_step())
				.re_level(dto.getRe_level())
				.subject(dto.getSubject())
				.content(dto.getContent())
				.ip(dto.getIp())
				.reg_date(dto.getReg_date())
				.upload(dto.getUpload())
				.membersEntity(memberEntity)
				.build();
	}


}







