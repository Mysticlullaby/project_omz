package com.omz.demo.movie.dto;

import java.time.LocalDateTime;

import com.omz.demo.client.entity.ClientEntity;
import com.omz.demo.movie.entity.MovieEntity;
import com.omz.demo.movie.entity.ViewCountEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewCountDTO {
//	  viewcount_id NUMBER NOT NULL,
//    client_id    VARCHAR2(64) NOT NULL,
//    movie_id     NUMBER NOT NULL,
//    reg_date     TIMESTAMP,
//    edit_date    TIMESTAMP
	private long viewcountId;
	private String clientId;
	private long movieId;
	private LocalDateTime regDate;
	
	public static ViewCountDTO toDto(ViewCountEntity entity) {
		return ViewCountDTO.builder()
				.viewcountId(entity.getViewcountId())
				.clientId(entity.getClientId())
				.movieId(entity.getMovieId())
				.regDate(entity.getRegDate())
				.build();
	}
	
	public static ViewCountEntity toEntity(ViewCountDTO dto) {
		return ViewCountEntity.builder()
				.viewcountId(dto.getViewcountId())
				.clientId(dto.getClientId())
				.movieId(dto.getMovieId())
				.regDate(dto.getRegDate())
				.build();
	}
	
}
