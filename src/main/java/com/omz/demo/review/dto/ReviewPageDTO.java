package com.omz.demo.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ReviewPageDTO {
	private long currentPage; // 현재페이지
	private long totalCount; // 총 레코드수
	private long blockCount = 16; // 한 페이지에 보여줄 레코드 수
	private long blockPage = 5; // 한 블록에 보여줄 페이지 수
	private long totalPage; // 총 페이지수
	private long startRow; // 시작 레코드 번호
	private long endRow; // 끝 레코드 번호
	private long startPage; // 한 블록의 시작 페이지 번호
	private long endPage; // 한 블록의 끝 페이지 번호
	
	public ReviewPageDTO(long currentPage, long totalCount) {
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		
		// 총 페이지수
		totalPage = totalCount / blockCount + (totalCount % blockCount == 0 ? 0 : 1);
		if(totalPage<currentPage)
		  this.currentPage = totalPage;

		// 시작레코드
		startRow = (this.currentPage - 1) * blockCount + 1;

		// 끝레코드
		endRow = startRow + blockCount - 1;

		// 시작 페이지
		startPage = (long) ((this.currentPage - 1) / blockPage) * blockPage + 1;

		// 끝 페이지
		endPage = startPage + blockPage - 1;
		if (totalPage < endPage)
			endPage = totalPage;
	}
}
