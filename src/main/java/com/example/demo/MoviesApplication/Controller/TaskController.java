package com.example.demo.MoviesApplication.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.MoviesApplication.Entity.Movies;
import com.example.demo.MoviesApplication.Service.DaoServiceImpl;

import jakarta.websocket.server.PathParam;

@RestController
public class TaskController {

	@Autowired
	private DaoServiceImpl daoServiceImpl;

	@RequestMapping(path = "/feedMoviesData")
	public void saveMoviesData() {
		daoServiceImpl.saveMoviesData();
	}

	@RequestMapping(path = "feedRatingsData")
	public void seveRatingsData() {
		daoServiceImpl.saveRatingsData();

	}

	@GetMapping(path = "/longestdurationmovies")
	public List<Movies> getRumtimeMinutes(@PathParam(value = "Movies") Movies movies) {
		return daoServiceImpl.getRuntimeMinutes(movies);
	}

	@PostMapping(path = "/AddNewMovies")
	public ResponseEntity<String> newMovies(@PathParam(value = "Movies") Movies movies) {
		daoServiceImpl.newMovies(movies);
		return ResponseEntity.status(HttpStatus.OK).body("success");

	}

	@GetMapping("/api/v1/top-rated-movies")
	public ResponseEntity<List<Movies>> getTopRatedMovies() {
		
		List<Movies> movies= daoServiceImpl.getTopRatedMovies();
		System.out.println(movies);
		return ResponseEntity.ok(movies);
	}

	@GetMapping("/api/v1/genre-movies-with-subtotals")
	public ResponseEntity<List<Movies>> getGenreMoviesWithSubtotals() {
		List<Movies> movies = daoServiceImpl.getGenreMoviesWithSubtotals();
		return ResponseEntity.ok(movies);
	}
	
	@PostMapping("api/v1/update-runtime-minutes")
	public ResponseEntity<List<Movies>>getUpdateMoviesRuntimeMinutes(){
		List<Movies>movies = daoServiceImpl.getRuntimeMinutes();
		return ResponseEntity.ok(movies);
	}

}