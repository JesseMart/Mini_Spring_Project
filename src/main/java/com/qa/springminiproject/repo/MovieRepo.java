package com.qa.springminiproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.springminiproject.domain.Movie;

//This Repo gives us a way to intereact with the database as an interface
public interface MovieRepo extends JpaRepository<Movie, Long> {

	///Movie is the entity
	//Long is our ID data type
	
}
