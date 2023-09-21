package com.omz.demo.board.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.omz.demo.board.dto.BoardDTO;
import com.omz.demo.board.dto.PageDTO;
import com.omz.demo.board.service.BoardService;
import com.omz.demo.common.file.FileUpload;

//@CrossOrigin(origins ={"http://localhost:3000"})
@CrossOrigin("*")

@RestController
public class BoardController {

	@Autowired // 다오임프나 서비스임프 빈 선언 해줬던부분 이거 하나로 퉁쳐짐
	private BoardService boardService;

	/*@Value("${spring.servlet.multipart.location}")
	// application.properites에 보면 파일 업로드 경로 설정 해준부분 있는데 그 위치로 가겠다고 지정해주는 부분
	private String filePath;*/

	@Autowired
	private PageDTO pdto;
	private int currentPage;

	public BoardController() {

	}

	// http://localhost:8090/board/list/1
	@GetMapping("/board/list/{currentPage}")
	public Map<String, Object> listExecute(@PathVariable("currentPage") int currentPage, PageDTO pv) {
		Map<String, Object> map = new HashMap<>();
		long totalRecord = boardService.countProcess();
		if (totalRecord >= 1) {
			this.currentPage = currentPage;

			this.pdto = new PageDTO(this.currentPage, totalRecord);

			map.put("boardList", boardService.listProcess(this.pdto));
			//map은 (key, value)의 형태임 여기서는 value의 값을 key인 boardList에 저장하겟다는 말 / 나중에 어딘가에서 boardList 쓸텐데 어디서 쓸까....?
			//안쓰는거 같음 그냥 key 값 필요해서 냅다 넣은 것일까?ㅎ
			map.put("pv", this.pdto);
		}

		return map;
	} // end listExecute()

	// @RequestBody : json => 자바객체
	// @ResponseBody : 자바객체 =>json
	// @PathVariable : /board/list/:num => /board/list/{num}
	// @RequestParam : /board/list?num=value => /board/list?num=1 => /board/list
	// multipart/form-data : @RequestBody선언없이 그냥 받음 BoardDTO dto

	/*@PostMapping("/board/write")
	public String writeProExcute(BoardDTO dto, PageDTO pv, HttpServletRequest req, HttpSession session) {
		MultipartFile file = dto.getFilename();
	
		// System.out.println(dto.getMembersDTO().getMemberName());
	
		// 파일 첨부가 있으면...
		if (file != null && !file.isEmpty()) {
			UUID random = FileUpload.saveCopyFile(file, filePath);
			dto.setUpload(random + "_" + file.getOriginalFilename());
		}
	
		//
		if (req.getParameter("memberEmail") != null) {
			dto.getMembersDTO().setMemberEmail(req.getParameter("memberEmail"));
			dto.getMembersDTO().setMemberName(req.getParameter("memberName"));
		}
	
		dto.setIp(req.getRemoteAddr());
	
		boardService.insertProcess(dto);
	
		// 답변글이면
		if (dto.getRef() != 0)
	
		{
			// ratt.addAttribute("currentPage", pv.getCurrentPage());
			return String.valueOf(pv.getCurrentPage());
		} else {
			return String.valueOf(1);
		}
	
		// return "redirect:/board/list.do";
	}*/

	/*@GetMapping("/board/view/{num}")
	public BoardDTO viewExecute(@PathVariable("num") int num) {
		return boardService.contentProcess(num);
	}*/

	/*@PutMapping("/board/update")
	public void updateExecute(BoardDTO dto, HttpServletRequest request) throws IllegalStateException, IOException {
	
		MultipartFile file = dto.getFilename();
		if (file != null && !file.isEmpty()) {
			UUID random = FileUpload.saveCopyFile(file, filePath);
			dto.setUpload(random + "_" + file.getOriginalFilename());
			// d:\\download\\temp 경로에 첨부파일 저장
			file.transferTo(new File(random + "_" + file.getOriginalFilename()));
		}
		boardService.updateProcess(dto, filePath);
	}*/

	/*@DeleteMapping("/board/delete/{num}")
	public void deleteExecute(@PathVariable("num") long num, HttpServletRequest request) {
		boardService.deleteProcess(num, filePath);
	}*/

	/*@GetMapping("/board/contentdownload/{filename}")
	public ResponseEntity<Resource> downloadExecute(@PathVariable("filename") String filename) throws IOException {
	
		String fileName = filename.substring(filename.indexOf("_") + 1);
	
		// 파일명이 한글일때 인코딩 작업을 한다.
		String str = URLEncoder.encode(fileName, "UTF-8");
		// 원본파일명에서 공백이 있을 때, +로 표시가 되므로 공백으로 처리해줌
		str = str.replaceAll("\\+", "%20");
		Path path = Paths.get(filePath + "\\" + filename);
		Resource resource = new InputStreamResource(Files.newInputStream(path));
		System.out.println("resource:" + resource.getFilename());
	
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + str + ";").body(resource);
	}*/

}// end class
