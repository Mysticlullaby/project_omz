package com.omz.demo.client.dto;

import com.omz.demo.client.entity.ClientEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
	private String clientId;
	private String clientPass;
	private String clientName;

	public static ClientEntity toEntity(ClientDTO dto) {
		return ClientEntity.builder()
				.clientId(dto.getClientId())
				.clientPass(dto.getClientPass())
				.clientName(dto.getClientName())
				.build();
	}
	
	public static ClientDTO toDto(ClientEntity entity) {
		return ClientDTO.builder()
				.clientId(entity.getClientId())
				.clientPass(entity.getClientPass())
				.clientName(entity.getClientName())
				.build();
	}
	
}
