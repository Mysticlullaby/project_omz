package com.omz.demo.board.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omz.demo.board.dto.BoardDTO;
import com.omz.demo.board.dto.PageDTO;
import com.omz.demo.board.entity.BoardEntity;
import com.omz.demo.board.repository.BoardRepository;


@Service
@Transactional
public class BoardServiceImp implements BoardService {

	@Autowired
	private BoardRepository boardRepository;

	public BoardServiceImp() {

	}

	@Override
	public long countProcess() {
		return boardRepository.findCount();
	}

	@Override
	public List<BoardDTO> listProcess(PageDTO pv) {
		System.out.printf("startRow:%d, endRow:%d\n", pv.getStartRow(), pv.getEndRow());
		List<BoardDTO> aList = new ArrayList<>();		
		List<BoardEntity> result = boardRepository.findAllActiveOmzboardNative(pv.getStartRow(), pv.getEndRow());

		result.forEach(board -> aList.add(BoardDTO.toDto(board)));
		return aList; 
	}

	@Override
	public void insertProcess(BoardDTO dto) {
		System.out.printf("board_id:%d subject:%s\n", dto.getOmzboardId(), dto.getSubject());
		System.out.println("dto:" + dto.getClientDTO());
		BoardEntity entity = BoardDTO.toEntity(dto);
		
		// 답변글이면
		if (dto.getBoardRef() != 0) {
			boardRepository.findReStepCount(entity.getBoardRef(), entity.getReStep());
			dto.setReStep(dto.getReStep() + 1);
			dto.setReLevel(dto.getReLevel() + 1);
			boardRepository.findSaveReply(BoardDTO.toEntity(dto), dto.getClientDTO().getClientId());
		} else {
//			boardRepository.findSaveNew(BoardDTO.toEntity(dto), dto.getClientDTO().getClientId());
			boardRepository.save(entity);
		}
	}

	/*@Override
	public BoardDTO contentProcess(long num) {
		BoardDTO bDTO = null;
		bDTO = BoardDTO.toDto(boardRepository.findByContent(num));
	
		return bDTO;
	}*/

	//원래 주석이엇음
	/*	@Override
	public BoardDTO updateSelectProcess(int num) {
			return boardDao.content(num);
	}*/

	/*@Override
	public void updateProcess(BoardDTO dto, String urlpath) {
		String filename = dto.getUpload();
	
		String path = boardRepository.findByFileNum(dto.getNum());
		// 수정할 파일이 있으면
		if (filename != null) {
	
			// 기존 첨부파일이 있으면
			if (path != null) {
				File file = new File(urlpath, path);
				file.delete();
			}
		} else {
			dto.setUpload(path);
		}
	
		// boardRepository.findByUpdateEntity(dto.getSubject(), dto.getContent(),
		// dto.getUpload(), dto.getNum());
		
		boardRepository.findByUpdateEntity(BoardDTO.toEntity(dto));
	}*/

	/*@Override
	public void deleteProcess(long num, String urlpath) {	
		String path = boardRepository.findByFileNum(num);
		if(path!=null) {
			File file = new File(urlpath, path);
			file.delete();
		}
		boardRepository.findDelete(num);
	} 
	*/

}
