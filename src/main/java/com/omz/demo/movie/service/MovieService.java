package com.omz.demo.movie.service;

import java.util.List;

import com.omz.demo.movie.dto.MovieDTO;

public interface MovieService {
	public List<MovieDTO> listProcess();
	public MovieDTO getProcess(long id, String clientId);
	
}
