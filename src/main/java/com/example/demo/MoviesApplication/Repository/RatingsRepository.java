package com.example.demo.MoviesApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.MoviesApplication.Entity.Ratings;

@Repository
public interface RatingsRepository extends JpaRepository<Ratings, String> {


 
}
