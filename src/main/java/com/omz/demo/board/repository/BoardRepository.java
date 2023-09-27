package com.omz.demo.board.repository;

import java.util.List;

import javax.persistence.ColumnResult;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.omz.demo.board.entity.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

	// :#{#entity.ip} 표시 : 객체로 넘어온거를 표현할때임
	/*@Query(value = "INSERT INTO omzboard"
			+ " VALUES(omzboard_num_seq.nextval, "
			+ ":#{#entity.client_id}, "
			+ ":#{#entity.subject}, "
			+ ":#{#entity.board_content}, "
			+ "0, "
			+ "omzboard_num_seq.nextval, "
			+ ":#{#entity.re_step}, "
			+ ":#{#entity.re_level},"
			+ ":#{#entity.upload}), null, null ", nativeQuery = true)*/
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
		@Query(value = "UPDATE omzboard SET reStep = reStep + 1 WHERE boardRef=:boardRef AND reStep > :reStep", nativeQuery = true)
		@Modifying
		void findReStepCount(@Param("boardRef") long boardRef, @Param("reStep") long reStep);
	
	@Modifying
	@Query(value ="INSERT INTO omzboard(omzboardId, subject, regDate, readCount, boardRef, reStep, reLevel, boardContent, upload, clientId)"
			+ " VALUES(omzboard_num_seq.nextval,:#{#entity.subject}, sysdate, 0, omzboard_num_seq.nextval,:#{#entity.reStep}, :#{#entity.reLevel},"
			+ ":#{#entity.boardContent}, :#{#entity.upload},:clientId)", nativeQuery = true)
	void findSaveReply(@Param("entity") BoardEntity entity, @Param("clientId") String clientId);

	//여기서 findCount해주면 그 결과를 가지고 컨트롤러에서 토탈레코드 따져서 보여줄때 사용함
	/*@Query(value = "SELECT count(*) FROM omzboard", nativeQuery = true)
	long findCount();*/
	
	//보드리스트 출력
	@Query(value = "SELECT b.*"
				+ " FROM (SELECT rownum AS rm, a.*"
					+ " FROM ("
							+ "SELECT o.*"
							+ " FROM omzboard o"
							+ " ORDER BY board_ref DESC)a)b"
					+ " where b.rm >= :startRow and b.rm <= :endRow", nativeQuery = true)
	List<BoardEntity> findAllActiveOmzboardNative(@Param("startRow") long startRow, @Param("endRow") long endRow);
	//특정범위를 조회하고 결과가 BoardEntity 객체로 반환된다는 말

	 
//	조회수증가 (나도써?)
//	@Query(value = "UPDATE omzboard SET readCount = readCount + 1 WHERE omzboardId=:omzboardId", nativeQuery = true)
//	@Modifying
//	void findByReadCount(@Param("omzboardId") long omzboardId);

	
//	@Query(value = "SELECT b.*, m.member_name,m.member_email FROM board2 b, members2 m WHERE b.member_email=m.member_email(+)"
//			+ " AND b.num=:num", nativeQuery = true)
	
	/*@Query(value = "SELECT b.*, m.member_name,m.member_email FROM board2 b, members2 m WHERE b.member_email=m.member_email(+)"
			+ " AND b.num=:num", nativeQuery = true)
	BoardEntity findByContent(@Param("omzboardId") long omzboardId);*/
	
//	@Query(value = "", nativeQuery = true)
//	BoardEntity findByContent(@Param("omzboardId") long omzboardId);
	
//	@Query(value = "SELECT upload FROM board2 WHERE num=:num", nativeQuery = true)
//	String findByFileNum(@Param("num") long num);
//
////	@Query(value="UPDATE board2 SET subject=:subject,content=:content,upload=:upload"			
////			   + " WHERE num=:num", nativeQuery=true)
////	void findByUpdateEntity(@Param("subject") String subject, @Param("content") String content, @Param("upload") String upload, @Param("num") long num );
//
//	@Query(value = "UPDATE board2 SET subject=:#{#entity.subject},content=:#{#entity.content},upload=:#{#entity.upload}"
//			+ " WHERE num=:#{#entity.num}", nativeQuery = true)
//	@Modifying
//	void findByUpdateEntity(@Param("entity") BoardEntity entity);
//
//	@Query(value = "DELETE FROM board2 WHERE num=:num", nativeQuery = true)
//	@Modifying
//	void findDelete(@Param("num") long num);
}
