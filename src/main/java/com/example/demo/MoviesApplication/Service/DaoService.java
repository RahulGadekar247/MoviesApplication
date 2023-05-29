package com.example.demo.MoviesApplication.Service;

import java.util.List;

import com.example.demo.MoviesApplication.Entity.Movies;

public interface DaoService {

	void saveMoviesData();
	void saveRatingsData();
	List<Movies>getRuntimeMinutes(Movies Movies);
	public Movies newMovies(Movies movies);
	List<Movies> getTopRatedMovies();
	List<Movies> getGenreMoviesWithSubtotals();
	List<Movies>getRuntimeMinutes();
	
	
	
}
