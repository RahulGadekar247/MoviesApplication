package com.example.demo.MoviesApplication.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Ratings {
	@Id
	private String tconst;
	@Column(name="average_rating")
	private String averageRating;
	@Column(name="num_vote")
	private String numVote;
	
	
	
	
	public String getTconst() {
		return tconst;
	}
	public void setTconst(String tconst) {
		this.tconst = tconst;
	}
	public String getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(String averageRating) {
		this.averageRating = averageRating;
	}
	public String getNumVote() {
		return numVote;
	}
	public void setNumVote(String numVote) {
		this.numVote = numVote;
	}
	@Override
	public String toString() {
		return "Ratings [tconst=" + tconst + ", averageRating=" + averageRating + ", numVote=" + numVote + "]";
	}
	
	
	

}
