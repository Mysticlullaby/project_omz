package com.omz.demo.board.repository;

import java.util.List;

import javax.persistence.ColumnResult;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.omz.demo.board.entity.BoardEntity;
import com.omz.demo.movie.entity.MovieEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

	@Query(value = "INSERT INTO omzboard(omzboard_id, client_id, subject, board_content, read_count, board_ref, re_step, re_level, upload, reg_date)"
			+ " VALUES(omzboard_num_seq.nextval, "
			+ ":clientId, "
			+ ":#{#entity.subject}, "
			+ ":#{#entity.boardContent}, "
			+ "0,"
			+ "omzboard_num_seq.nextval, "
			+ ":#{#entity.reStep}, "
			+ ":#{#entity.reLevel},"
			+ ":#{#entity.upload}, to_timestamp(sysdate, 'YYYY-MM-DD HH24:MI:SS'))", nativeQuery = true)
	@Modifying
	void findSaveNew(@Param("entity") BoardEntity entity, @Param("clientId") String clientId);
	// service에서 호출되는 메소드임 : findSaveNew
	// nativeQuery=true : 매퍼에 햇던 네이티브 쿼리 갖다 쓰려면 이거 붙여줘야 한대

	//답변글 쓸때
	@Query(value = "UPDATE omzboard SET re_step = re_step + 1 WHERE board_ref=:boardRef AND re_step > :reStep", nativeQuery = true)
	@Modifying
	void findReStepCount(@Param("boardRef") long boardRef, @Param("reStep") long reStep);
	
	//답변 삽입
	@Modifying
	@Query(value ="INSERT INTO omzboard(omzboard_id, client_id, subject, board_content, read_count, board_ref, re_step, re_level, upload, reg_date)"
			+ " VALUES(omzboard_num_seq.nextval, "
			+ ":clientId, "
			+ ":#{#entity.subject}, "
			+ ":#{#entity.boardContent}, "
			+ "0,"
			+ ":#{#entity.boardRef}, "
			+ ":#{#entity.reStep}, "
			+ ":#{#entity.reLevel},"
			+ ":#{#entity.upload}, to_timestamp(sysdate, 'YYYY-MM-DD HH24:MI:SS'))", nativeQuery = true)
	void findSaveReply(@Param("entity") BoardEntity entity, @Param("clientId") String clientId);

	//여기서 findCount해주면 그 결과를 가지고 컨트롤러에서 토탈레코드 따져서 보여줄때 사용함
	//걍 count로 했는데 없애도 되려나....크흠....
	/*@Query(value = "SELECT count(*) FROM omzboard", nativeQuery = true)
	long findCount();*/
	
	//보드리스트 출력
	@Query(value = "SELECT b.*"
				+ " FROM (SELECT rownum AS rm, a.*"
					+ " FROM ("
							+ "SELECT o.*"
							+ " FROM omzboard o"
							+ " ORDER BY board_ref DESC, re_step ASC)a)b"
					+ " where b.rm >= :startRow and b.rm <= :endRow", nativeQuery = true)
	List<BoardEntity> findAllActiveOmzboardNative(@Param("startRow") long startRow, @Param("endRow") long endRow);
	//특정범위를 조회하고 결과가 BoardEntity 객체로 반환된다는 말

	 
	//조회수증가
	@Query(value = "UPDATE omzboard SET read_count = read_count + 1 WHERE omzboard_id=:omzboardId", nativeQuery = true)
	@Modifying
	void findByReadCount(@Param("omzboardId") long omzboardId);

	// 게시글 세부페이지 출력
	BoardEntity findByOmzboardId(@Param("omzboardId") long omzboardId);
	
	//첨부파일 관련
	@Query(value = "SELECT upload FROM omzboard WHERE omzboard_id=:omzboardId", nativeQuery = true)
	String findByFileNum(@Param("omzboardId") long omzboardId);

	@Query(value = "UPDATE omzboard SET subject=:#{#entity.subject}, board_content=:#{#entity.boardContent}, upload=:#{#entity.upload}"
			+ " WHERE omzboard_id=:#{#entity.omzboardId}", nativeQuery = true)
	@Modifying
	void findByUpdateEntity(@Param("entity") BoardEntity entity);

	// 게시글 삭제
	@Query(value = "DELETE FROM omzboard WHERE omzboard_id=:omzboardId", nativeQuery = true)
	@Modifying
	void findDelete(@Param("omzboardId") long omzboardId);
	
	
}
