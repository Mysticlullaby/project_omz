package com.omz.demo.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.omz.demo.client.entity.ClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, String> {

	public ClientEntity findByClientId(String clientId);
	boolean existsByClientId(String clientId);

//	JPQL : 엔티티 객체를 조회하는 객체지향 쿼리, 테이블 대상으로 쿼리하는 것이 아닌 엔티티 객체를 대상으로 쿼리
//	public ClientId findByClientId(@Param("clientId") String clientId)
//	@Query(value="SELECT c FROM clientEntity WHERE c.clientId=:clientId")

//	nativeQuery : 테이블을 대상으로 쿼리 한다(oracle 기준)
//	@Query(value = "INSERT INTO omz_client(client_id, client_pass, client_name, phone, email, gender, age, mbti, reg_date, grede)"
//	+ "VALUES(:#{#client_id}, :#{#entity.client_pass}, :#{#entity.client_name}, :#{#entity.phone}, :#{#entity.email}, :#{#entity.gender}, :#{#entity.age}, :#{#entity.mbti}, sysdate, 0)", nativeQuery = true)
//	@Modifying
//	void saveNew(@Param("entity") ClientEntity entity);
	
//	@QueryDSL (JPQL 문법 사용)
//	EntityManager entityManager = EntityManager.getEntityManager();
//	JPAQuery queryFactory = new JPAQuery(entityManager);
//	ClinetEntity result = queryFactory
//						  .select(clientEntity)
//						  .from(clinetEntity)
//						  .where(clientIdEq(clinetId))
	
//	ORACLE 방식
//	FROM board2 b, member2 m
//	WHERE b.member_email = m.member_email(+)
//	
//	ANSI 방식(JPQL)
//	FROM board2 b left Outer Join member2 m
//	ON b.member_email = m.member_email

}
