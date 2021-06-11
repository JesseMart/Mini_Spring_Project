package com.qa.springminiproject.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.springminiproject.domain.Movie;
import com.qa.springminiproject.service.MovieService;

//Allows the use of RequestMapping to expose methods and provides
//configuration for handling request in a RESTful manner (returning data in JSON format)
@RestController 
@RequestMapping("/movie")  // <-- Exposes methods to request at a defined URL
public class MovieController {

	private MovieService service;
	
	@Autowired
	public MovieController(MovieService service) {
		this.service = service;
	}
	
	//============================ CREATE =================================
	@PostMapping("/createMovie")  // < -- PostMapping is used to send data
	public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
		return new ResponseEntity<Movie>(this.service.createMovie(movie), HttpStatus.CREATED);
	}
	//============================ READ =================================
	@GetMapping("/getAllMovies") // <-- GetMapping used to fetch data
	public ResponseEntity<List<Movie>> getAllMovies() {
		return ResponseEntity.ok(this.service.getAlllMovies());
	}
	//============================ READ ONE =================================
	@GetMapping("/getOne/{index}")
	public ResponseEntity<Movie> getMovie(@PathVariable Long index) {
		return ResponseEntity.ok(this.service.getOneMovie(index));
	}
	//============================ UPDATE =================================
	@PutMapping("/update/{index}") // <-- PutMapping used to replace data in its entirety
	public ResponseEntity<Movie> updateMovie(@PathVariable Long index, @RequestBody Movie newMovie) {
		this.service.updateMovie(index, newMovie);
		return new ResponseEntity<Movie>(newMovie, HttpStatus.ACCEPTED);
	}
	
	//============================ UPDATE ONE =================================
	@PatchMapping("/update/{index}") // <-- PatchMapping used to partially update data
	public ResponseEntity<Movie> updateOneMovie(@PathParam("title") String title, @PathParam("genre") String genre, @PathParam("yearRelease") int yearRelease, @PathVariable Long index) {
		return null;
	}
	//============================ DELETE =================================
	@DeleteMapping("/delete/{index}") 
	public ResponseEntity<Movie> deleteMovie(@PathVariable Long index) {
		return this.service.removeMovie(index) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
			new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//=========================== custom Query ===========================
	@GetMapping("/getGenre/{genre}")
	public ResponseEntity<List<Movie>> getGenre(@PathVariable String genre) {
		return new ResponseEntity<List<Movie>>(this.service.getByGenre(genre), HttpStatus.OK);
	}
	
}
