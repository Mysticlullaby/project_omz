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

//	@Query(value = "INSERT INTO omz_client(client_id, client_pass, client_name, phone, email, gender, age, mbti, reg_date, grede)"
//			+ "VALUES(:#{#client_id}, :#{#entity.client_pass}, :#{#entity.client_name}, :#{#entity.phone}, :#{#entity.email}, :#{#entity.gender}, :#{#entity.age}, :#{#entity.mbti}, sysdate, 0)", nativeQuery = true)
//	@Modifying
//	void saveNew(@Param("entity") ClientEntity entity);

}
