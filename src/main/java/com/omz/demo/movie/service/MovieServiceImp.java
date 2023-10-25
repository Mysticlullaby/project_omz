package com.omz.demo.movie.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omz.demo.movie.dto.MovieDTO;
import com.omz.demo.movie.entity.MovieEntity;
import com.omz.demo.movie.repository.MovieRepository;
import com.omz.demo.movie.repository.ViewCountRepository;
import com.omz.demo.review.entity.ReviewEntity;
import com.omz.demo.review.repository.ReviewRepository;

@Service
@Transactional
public class MovieServiceImp implements MovieService{
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private ViewCountRepository viewCountRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;

	@Override
	public List<MovieDTO> listProcess() {
		List<MovieDTO> dtoList = new ArrayList<>();
		List<MovieEntity> entityList = movieRepository.findAll();
		
		for(MovieEntity entity : entityList) {
			dtoList.add(MovieDTO.toDto(entity));
		}
		
		return dtoList;
	}

	@Override
	public MovieDTO getProcess(long movieId, String clientId) {
		MovieDTO dto = MovieDTO.toDto(movieRepository.findByMovieId(movieId));
		dto.setViewCount(viewCountRepository.countByMovieId(movieId));
		
		String[] stringList = dto.getProvider().replace("['", "").replace("']", "").split("', '");
		List<String> platformList = new ArrayList<>();
        for (String string : stringList) {
            platformList.add(string);
        }
        
        dto.setPlatformList(platformList);
        
        System.out.println(platformList);
		
		if(clientId != null) {
			if(viewCountRepository.findByMovieIdAndClientId(movieId, clientId) != null) {
				dto.setViewCheck(true);
			}
			
			ReviewEntity reviewEntity = reviewRepository.findByMovieIdAndClientId(movieId, clientId);
			if(reviewEntity != null) {
				dto.setReviewId(reviewEntity.getReviewId());
			}
		}
		return dto;
	}

	@Override
	public List<MovieDTO> searchProcess(String keyword) {
		List<MovieDTO> dtoList = new ArrayList<>();
		List<MovieEntity> entityList = movieRepository.searchByKeyword(keyword);
		
		for(MovieEntity entity : entityList) {
			dtoList.add(MovieDTO.toDto(entity));
		}
		
		return dtoList;
	}
}
