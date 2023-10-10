package com.omz.demo.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omz.demo.client.entity.ClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, String> {

	public ClientEntity findByClientId(String clientId);
	boolean existsByClientId(String clientId);

}
