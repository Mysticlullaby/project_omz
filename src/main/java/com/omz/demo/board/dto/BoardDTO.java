package  com.omz.demo.board.dto;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.omz.demo.board.entity.BoardEntity;
import com.omz.demo.client.dto.ClientDTO;
import com.omz.demo.client.entity.ClientEntity;

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
	private long omzboardId, readCount, boardRef, reStep, reLevel;
	private String subject, boardContent, clientId;
	private String regDate;

	//form페이지에서 파일첨부를 받아 처리해주는 멤버변수
	private MultipartFile filename;
	
	//board테이블의 파일첨부를 처리해주는 멤버변수
	private String upload;
	// filename 변수랑 관련있는 컬럼명이라서 따로 뺀듯
	
	private ClientDTO clientDTO= new ClientDTO();

	public static BoardDTO toDto(BoardEntity entity) {
		
		ClientDTO clientDTO=new ClientDTO();
		clientDTO.setClientId(entity.getClientEntity().getClientId());
//		clientDTO.setMemberName(entity.getClientEntity().getMemberName());
		 
		return BoardDTO.builder()
				.omzboardId(entity.getOmzboardId())
				.readCount(entity.getReadCount())
				.boardRef(entity.getBoardRef())
				.reStep(entity.getReStep())
				.reLevel(entity.getReLevel())
				.subject(entity.getSubject())
				.boardContent(entity.getBoardContent())
				.regDate(entity.getRegDate())
				.upload(entity.getUpload())
				.clientDTO(clientDTO)
				.build();
	}
	
	//컨트롤러에서 디비로 넘겨줄때 변경해야하는 부분
	public static BoardEntity toEntity(BoardDTO dto) {
		
		ClientEntity clientEntity=new ClientEntity();
		clientEntity.setClientId(dto.getClientDTO().getClientId());
	//	clientEntity.setMemberName(dto.getClientDTO().getMemberName());
		
		return BoardEntity.builder()
				.omzboardId(dto.getOmzboardId())
				.readCount(dto.getReadCount())
				.boardRef(dto.getBoardRef())
				.reStep(dto.getReStep())
				.reLevel(dto.getReLevel()) 
				.subject(dto.getSubject())
				.boardContent(dto.getBoardContent())
				.regDate(dto.getRegDate())
				.upload(dto.getUpload())
				.clientEntity(clientEntity)
				.build();
	}

}