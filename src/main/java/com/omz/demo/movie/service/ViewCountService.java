package com.omz.demo.movie.service;

import com.omz.demo.movie.dto.ViewCountDTO;

public interface ViewCountService {
	public void saveProcess(ViewCountDTO dto);
	public long countProcess(long movieId);
	public void deleteProcess(long movieId, String clientId);
	public long checkProcess(String clientId);
}
