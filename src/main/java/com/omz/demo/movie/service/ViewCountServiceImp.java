package com.omz.demo.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omz.demo.movie.dto.ViewCountDTO;
import com.omz.demo.movie.repository.ViewCountRepository;

@Service
@Transactional
public class ViewCountServiceImp implements ViewCountService{
	
	@Autowired
	private ViewCountRepository viewCountRepository;

	@Override
	public void saveProcess(ViewCountDTO dto) {
		viewCountRepository.save(ViewCountDTO.toEntity(dto));
	}

	@Override
	public long countProcess(long movieId) {
		return viewCountRepository.countByMovieId(movieId);
	}

	@Override
	public void deleteProcess(long movieId, String clientId) {
		viewCountRepository.deleteByMovieIdAndClientId(movieId, clientId);
	}
	
	public void getProcess(long movieId, String clientId) {
		viewCountRepository.findByMovieIdAndClientId(movieId, clientId);
	}

	@Override
	public long checkProcess(String clientId) {
		return viewCountRepository.countByClientId(clientId);
	}

}
