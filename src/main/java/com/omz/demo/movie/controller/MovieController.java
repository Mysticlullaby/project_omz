package com.omz.demo.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omz.demo.movie.dto.MovieDTO;
import com.omz.demo.movie.service.MovieService;

@CrossOrigin("*")
@RestController
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping("/movie/list")
	public List<MovieDTO> listExecute(){
		return movieService.listProcess();
	}

}
