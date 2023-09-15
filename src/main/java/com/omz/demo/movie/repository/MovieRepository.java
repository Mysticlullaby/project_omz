package com.omz.demo.movie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omz.demo.movie.entity.MovieEntity;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long>{
	//메소드 이름 지을 때 규칙: find+(테이블명(엔티티명)-생략가능)+By+컬럼이름
	List<MovieEntity> findAll();
}
