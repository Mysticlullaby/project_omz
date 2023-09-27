package com.omz.demo.board.service;

import java.util.List;

import com.omz.demo.board.dto.BoardDTO;
import com.omz.demo.board.dto.PageDTO;

public interface BoardService {
	public long countProcess();

	public List<BoardDTO> listProcess(PageDTO pv);

	public void insertProcess(BoardDTO dto);

	public BoardDTO contentProcess(long omzboardId); // 게시물 상세보기
	
//	public void updateProcess(BoardDTO dto, String urlpath);
//	public void deleteProcess(long num, String urlpath);
}
