package com.omz.demo.movie.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="movie")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
@Builder
public class MovieEntity {
	
	@Id
	@Column(name="movie_id")
	private long movieId;
	
	@Column
	private String title;
	
	@Column(name="movie_description")
	private String movieDescription;
	
	@Column
	private String image;
	
	@Column
	private String poster;
	
	@Column
	private String trailer;
	
	@Column
	private String castings;
}
