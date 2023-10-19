package com.omz.demo.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		System.out.println("liestExecute processing");
		return movieService.listProcess();
	}
	
	@GetMapping("/movie/{movieId}/{clientId}")
	public MovieDTO getExecute(@PathVariable("movieId") long movieId, @PathVariable("clientId") String clientId) {
		return movieService.getProcess(movieId, clientId);
	}
	
	@GetMapping("/search/{keyword}")
	public List<MovieDTO> searchExecute(@PathVariable("keyword") String keyword){
		System.out.println("searchExecute in process: " + keyword);
		return movieService.searchProcess(keyword);
	}

}
