package com.omz.demo.movie.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.omz.demo.movie.entity.MovieEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
@Builder
public class MovieDTO {
	private long movieId;
	private String title;
	private String movieDescription;
	private String image;
	private String poster;
	private String trailer;
	private String castings;
	private String provider;
	
	private long viewCount;
	private boolean viewCheck;
	private long reviewId;
	
	private List<String> platformList;
	
	public static MovieEntity toEntity(MovieDTO dto) {
		return MovieEntity.builder()
				.movieId(dto.getMovieId())
				.title(dto.getTitle())
				.movieDescription(dto.getMovieDescription())
				.image(dto.getImage())
				.poster(dto.getPoster())
				.trailer(dto.getTrailer())
				.castings(dto.getCastings())
				.provider(dto.getProvider())
				.build();
	}
	
	public static MovieDTO toDto(MovieEntity entity) {
		return MovieDTO.builder()
				.movieId(entity.getMovieId())
				.title(entity.getTitle())
				.movieDescription(entity.getMovieDescription())
				.image(entity.getImage())
				.poster(entity.getPoster())
				.trailer(entity.getTrailer())
				.castings(entity.getCastings())
				.provider(entity.getProvider())
				.build();
	}
}
