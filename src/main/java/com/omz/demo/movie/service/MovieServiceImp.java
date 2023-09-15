package com.omz.demo.movie.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omz.demo.movie.dto.MovieDTO;
import com.omz.demo.movie.entity.MovieEntity;
import com.omz.demo.movie.repository.MovieRepository;

@Service
@Transactional
public class MovieServiceImp implements MovieService{
	
	@Autowired
	private MovieRepository movieRepository;

	@Override
	public List<MovieDTO> listProcess() {
		List<MovieDTO> dtoList = new ArrayList<>();
		List<MovieEntity> entityList = movieRepository.findAll();
		
		for(MovieEntity entity : entityList) {
			dtoList.add(MovieDTO.toDto(entity));
		}
		
		return dtoList;
	}

}
