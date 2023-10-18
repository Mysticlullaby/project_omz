package com.omz.demo.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omz.demo.movie.dto.ViewCountDTO;
import com.omz.demo.movie.service.ViewCountService;

@CrossOrigin("*")
@RestController
public class ViewCountController {
	
	@Autowired
	private ViewCountService viewCountService;
	
	@PostMapping("/view/add")
	public void saveExecute(ViewCountDTO dto) {
		viewCountService.saveProcess(dto);
	}
	
	@DeleteMapping("/view/delete/{movieId}/{clientId}")
	public void deleteExecute(@PathVariable("movieId") long movieId, @PathVariable("clientId") String clientId) {
		viewCountService.deleteProcess(movieId, clientId);
	}
}

