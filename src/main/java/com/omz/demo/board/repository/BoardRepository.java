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

	@Query(value = "INSERT INTO board2(num, subject, reg_date, readcount, ref, re_step, re_level, content, ip, upload, member_email)"
			+ " VALUES(board_num_seq2.nextval,:#{#entity.subject}, sysdate,0,board_num_seq2.nextval,:#{#entity.re_step}, :#{#entity.re_level},"
			+ ":#{#entity.content}, :#{#entity.ip}, :#{#entity.upload},:memberEmail)", nativeQuery = true)
	@Modifying
	void findSaveNew(@Param("entity") BoardEntity entity, @Param("memberEmail") String memberEmail);
	// service에서 호출되는 메소드임 : findSaveNew
	// :#{#entity.ip} 표시 : 객체로 넘어온거를 표현할때임
	// nativeQuery=true : 매퍼에 햇던 쿼리 갖다 쓰려면 이거 붙여줘야 한대

	//답변글 쓸때
	@Query(value = "UPDATE board2 SET re_step = re_step + 1 WHERE ref=:ref AND re_step > :re_step", nativeQuery = true)
	@Modifying
	void findReStepCounte(@Param("ref") long ref, @Param("re_step") long re_step);

	@Modifying
	@Query(value = "INSERT INTO board2(num, subject, reg_date, readcount, ref, re_step, re_level, content, ip, upload, member_email)"
			+ " VALUES(board_num_seq2.nextval, :#{#entity.subject}, sysdate, 0,:#{#entity.ref},:#{#entity.re_step}, :#{#entity.re_level},"
			+ ":#{#entity.content}, :#{#entity.ip}, :#{#entity.upload},:memberEmail)", nativeQuery = true)
	void findSaveReply(@Param("entity") BoardEntity entity, @Param("memberEmail") String memberEmail);

	@Query(value = "SELECT count(*) FROM board2", nativeQuery = true)
	long findCount();

	@Query(value = "SELECT b.* FROM(SELECT rownum AS rm , a.* FROM(SELECT b.*, m.member_name"
			+ " FROM board2 b, members2 m WHERE b.member_email=m.member_email(+)"
			+ " ORDER BY ref DESC, re_step ASC)a)b" + " WHERE b.rm>=:startRow AND b.rm<=:endRow", nativeQuery = true)
	List<BoardEntity> findAllActiveBoard2Native(@Param("startRow") long startRow, @Param("endRow") long endRow);

	@Query(value = "UPDATE board2 SET readcount = readcount + 1 WHERE num=:num", nativeQuery = true)
	@Modifying
	void findByReadCount(@Param("num") long num);

	@Query(value = "SELECT b.*, m.member_name,m.member_email FROM board2 b, members2 m WHERE b.member_email=m.member_email(+)"
			+ " AND b.num=:num", nativeQuery = true)
	BoardEntity findByContent(@Param("num") long num);

	@Query(value = "SELECT upload FROM board2 WHERE num=:num", nativeQuery = true)
	String findByFileNum(@Param("num") long num);

//	@Query(value="UPDATE board2 SET subject=:subject,content=:content,upload=:upload"			
//			   + " WHERE num=:num", nativeQuery=true)
//	void findByUpdateEntity(@Param("subject") String subject, @Param("content") String content, @Param("upload") String upload, @Param("num") long num );

	@Query(value = "UPDATE board2 SET subject=:#{#entity.subject},content=:#{#entity.content},upload=:#{#entity.upload}"
			+ " WHERE num=:#{#entity.num}", nativeQuery = true)
	@Modifying
	void findByUpdateEntity(@Param("entity") BoardEntity entity);

	@Query(value = "DELETE FROM board2 WHERE num=:num", nativeQuery = true)
	@Modifying
	void findDelete(@Param("num") long num);
}
