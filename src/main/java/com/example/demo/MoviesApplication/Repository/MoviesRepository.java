package com.example.demo.MoviesApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.MoviesApplication.Entity.Movies;

@Repository
public interface MoviesRepository extends JpaRepository<Movies, String> {

	
	List<Movies> findByGenres(String genres);
	
	List<Movies> findByRuntimeMinutes(String runtimeMinutes);

	

}
