package com.omz.demo.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omz.demo.client.entity.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, String> {

	public ClientEntity findByClientId(String clientId);
	boolean existsByClientId(String clientId);

}
