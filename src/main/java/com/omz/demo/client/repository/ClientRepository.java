package com.omz.demo.client.repository;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.repository.JpaRepository;

import com.omz.demo.client.entity.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, String> {

	public ClientEntity findByClientId(String clientId); //clientId를 넘기면 ClientEntity 값으로 나온다
	boolean existsByClientId(String clientId);
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	public ClientEntity deleteByClientId(String clientId);

}
