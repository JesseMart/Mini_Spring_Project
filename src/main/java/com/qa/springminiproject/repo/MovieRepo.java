package com.qa.springminiproject.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qa.springminiproject.domain.Movie;

//This Repo gives us a way to intereact with the database as an interface
public interface MovieRepo extends JpaRepository<Movie, Long> {

	///Movie is the entity
	//Long is our ID data type
	
	//JPQL - Java Persistence Query Language
	//Query is used to allow the request for specific methods by default
	@Query(value = "SELECT * FROM MOVIE WHERE GENRE = ?Action", nativeQuery=true)
	List<Movie> findbyGenre(String genre);
}
