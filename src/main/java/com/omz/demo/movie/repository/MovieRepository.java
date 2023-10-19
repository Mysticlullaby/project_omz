package com.omz.demo.movie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.omz.demo.movie.entity.MovieEntity;

//메소드 이름 지을 때 규칙: find+(테이블명(엔티티명)-생략가능)+By+컬럼이름
public interface MovieRepository extends JpaRepository<MovieEntity, Long>{
	List<MovieEntity> findAll();
	MovieEntity findByMovieId(long id);
	
	@Query(value="SELECT * FROM movie WHERE lower(title) LIKE lower('%' || :keyword || '%')", nativeQuery = true)
	List<MovieEntity> searchByKeyword(@Param("keyword") String keyword);
}
