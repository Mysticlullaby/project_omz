package com.omz.demo.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omz.demo.movie.entity.ViewCountEntity;

public interface ViewCountRepository extends JpaRepository<ViewCountEntity, Long>{
	public long countByMovieId(long movieId);
	public ViewCountEntity findByMovieIdAndClientId(long movieId, String clientId);
	public void deleteByMovieIdAndClientId(long movieId, String clientId);
	public long countByClientId(String clientId);

}
