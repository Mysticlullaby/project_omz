package com.omz.demo.client.repository;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.repository.JpaRepository;

import com.omz.demo.client.entity.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, String> {

	public ClientEntity findByClientId(String clientId); //clientId를 넘기면 ClientEntity 값으로 나온다
	boolean existsByClientId(String clientId);	
	
	// 주인은 mappedBy 속성을 사용하지 않고, @JoinColumn을 사용
	@ManyToOne(fetch = FetchType.LAZY) // 다대일, 한 명의 회원이 여러 게시글을 작성할 수 있으므로 board, review, comment 기준으로 @ManyToOne을 선언한다
	@JoinColumn(name = "client_id") // 외래 키를 매핑할 때 사용하며, name 속성에는 매핑할 외래 키 이름을 지정
	@OnDelete(action = OnDeleteAction.CASCADE)
	public Long deleteByClientId(String clientId);

}
