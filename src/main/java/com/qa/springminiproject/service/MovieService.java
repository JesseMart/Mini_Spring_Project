package com.qa.springminiproject.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.springminiproject.domain.Movie;
import com.qa.springminiproject.repo.MovieRepo;

@Service // Service classes are used to store the main business logic
public class MovieService {

	public MovieRepo repo;
	
	public MovieService(MovieRepo repo) {
		this.repo = repo;
	}
	
	 
	
	//=========================================== CREATE ============================================
	public Movie createMovie(Movie m) {
		return this.repo.save(m);
	}
	//=========================================== READ ==============================================
	public List<Movie> getAlllMovies() {
		return this.repo.findAll();
	}
	//=========================================== READ ONE ==========================================
	public Movie getOneMovie(Long id) {
		Optional<Movie> m = this.repo.findById(id);
		return m.get();
	}
	//=========================================== UPDATE ============================================
	public Movie updateMovie(Long id, Movie newMovie) {
		Movie existing = this.getOneMovie(id);
		existing.setTitle(newMovie.getTitle());
		existing.setGenre(newMovie.getGenre());
		existing.setYearRelease(newMovie.getYearRelease());
		
		Movie updated = this.repo.save(existing);
		
		return updated;
	}
	//=========================================== DELETE ============================================
	public boolean removeMovie(Long id) {
		this.repo.deleteById(id);
		return this.repo.existsById(id);
	}
	// ========================================== Custom Query ======================================
	public List<Movie> getByGenre(String genre) {
		return this.repo.findbyGenre(genre);
	}
 }
