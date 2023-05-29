package com.example.demo.MoviesApplication.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.MoviesApplication.Entity.Movies;
import com.example.demo.MoviesApplication.Entity.Ratings;
import com.example.demo.MoviesApplication.Repository.MoviesRepository;
import com.example.demo.MoviesApplication.Repository.RatingsRepository;

@Service
public class DaoServiceImpl implements DaoService {

	@Autowired
	private MoviesRepository moviesRepository;
	@Autowired
	private RatingsRepository ratingsRepository;

	String line = "";

	@Override
	public void saveMoviesData() {

		try {
			BufferedReader br = new BufferedReader(
					new FileReader("C:/Users/q/Downloads/MoviesApplication/src/main/resources/movies.csv"));
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				Movies m = new Movies();
				m.setTconst(data[0]);
				m.setTitleType(data[1]);
				m.setPrimaryTitle(data[2]);
				m.setGenres(data[3]);
				m.setRuntimeMinutes(data[4]);

				moviesRepository.save(m);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	String Line = ",";

	@Override
	public void saveRatingsData() {
		try {
			BufferedReader br = new BufferedReader(
					new FileReader("C:/Users/q/Downloads/MoviesApplication/src/main/resources/ratings.csv"));
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				Ratings r = new Ratings();
				r.setTconst(data[0]);
				r.setAverageRating(data[1]);
				r.setNumVote(data[2]);
				ratingsRepository.save(r);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public List<Movies> getRuntimeMinutes(Movies Movies) {
		List<Movies> movies = moviesRepository.findAll();
		List<Movies> ms = new ArrayList<>();

		for (Movies movie : movies) {
			Movies m = new Movies();
			m.setTconst(movie.getTconst());
			m.setPrimaryTitle(movie.getPrimaryTitle());
			m.setRuntimeMinutes(movie.getRuntimeMinutes());
			m.setGenres(movie.getGenres());
			m.save(m);
		}

		return movies;
	}

	@Override
	public Movies newMovies(Movies movies) {
		return moviesRepository.save(movies);
	}

	@Override
	public List<Movies> getTopRatedMovies() {

		String url = "jdbc:mysql://localhost:3306/demo_db";
		String username = "root";
		String password = "root";

		List<Movies> movies = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			String query = "SELECT m1_0.tconst, m1_0.primary_title, m1_0.genres, AVG(r1_0.average_rating) AS average_rating "
					+ "FROM movies m1_0 " + "JOIN ratings r1_0 ON m1_0.tconst = r1_0.tconst "
					+ "GROUP BY m1_0.tconst, m1_0.primary_title, m1_0.genres "
					+ "HAVING AVG(r1_0.average_rating) > 6.0 " + "ORDER BY AVG(r1_0.average_rating) DESC";
			PreparedStatement stmt = conn.prepareStatement(query);
			System.out.println("query run successfully");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String settconst = rs.getString("tconst");
				String setprimary_title = rs.getString("primary_title");
				String setgenres = rs.getString("genres");
				String setaverage_rating = rs.getString("average_rating");

				Movies movie = new Movies();
				movies.add(movie);
				System.out.println("data lodded successfully");
				moviesRepository.findAll();
			}

			stmt.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return moviesRepository.findAll();

	}

	@Override
	public List<Movies> getGenreMoviesWithSubtotals() {
		String url = "jdbc:mysql://localhost:3306/demo_db";
		String username = "root";
		String password = "root";

		List<Movies> movies = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(url, username, password)) {
//            String query = "SELECT genres, primary_title, num_vote " +
//                    "FROM movies " +
//                    "JOIN ALL " +
//                    "SELECT '', 'TOTAL', SUM(num_vote) " +
//                    "FROM movies " +
//                    "GROUP BY genres " +
//                    "ORDER BY genres ASC, primaryTitle ASC";

			String query = "SELECT genres,primary_title,num_vote FROM movies ORDER BY genres, primary_title"
					+ " UNION ALL " + "SELECT"
					+ "    genres, 'TOTAL', SUM(numVotes) AS num_vote FROM movies GROUP BY genres ORDER BY genres, primary_title";
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String genres = rs.getString("genres");
				String primary_title = rs.getString("primary_title");
				String num_Vote = rs.getString("num_vote");

				Movies movie = new Movies();
				movies.add(movie);
			}

			stmt.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return moviesRepository.findAll();
	}

	@Override
	public List<Movies> getRuntimeMinutes() {

		String url = "jdbc:mysql://localhost:3306/demo_db";
		String username = "root";
		String password = "root";

		List<Movies> movies = new ArrayList<>();
		String query = "UPDATE Movies " + "SET runtime_minutes = CASE "
				+ "WHEN genres = 'Documentary' THEN runtime_minutes + 15 "
				+ "WHEN genres = 'Animation' THEN runtime_minutes + 30 " + "ELSE runtime_minutes + 45 " + "END";

		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			PreparedStatement stmt = conn.prepareStatement(query);
			BufferedReader br = new BufferedReader(
					new FileReader("C:/Users/q/Downloads/MoviesApplication/src/main/resources/movies.csv"));
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				stmt.setString(0, "runtime_minutes");
				stmt.setString(1, "genres");
			}
			ResultSet rs = stmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<Movies> updatedRuntimeMinutes = moviesRepository.findByRuntimeMinutes(query);

		return moviesRepository.findByRuntimeMinutes(query);
	}

}
