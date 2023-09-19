package com.omz.demo.movie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omz.demo.movie.entity.MovieEntity;

//메소드 이름 지을 때 규칙: find+(테이블명(엔티티명)-생략가능)+By+컬럼이름
public interface MovieRepository extends JpaRepository<MovieEntity, Long>{
	List<MovieEntity> findAll();
	MovieEntity findByMovieId(long id);
}
