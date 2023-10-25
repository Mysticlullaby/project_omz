package com.omz.demo.client.repository;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.omz.demo.client.entity.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, String> {

	public ClientEntity findByClientId(String clientId); //clientId를 넘기면 ClientEntity 값으로 나온다
	public boolean existsByClientId(String clientId);
	
	@Modifying
	@Query(value = "UPDATE omz_client " 
	        + "SET grade = 'stranger' "
	        + "WHERE client_Id = :clientId", nativeQuery = true)
	public void updateGrade(@Param("clientId") String clientId);
	
}
