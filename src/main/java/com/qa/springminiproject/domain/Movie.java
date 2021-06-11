package com.qa.springminiproject.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity 
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private String genre;
	private int yearRelease;
	
	public Movie () {}
	public Movie( String title, String genre, int yearRelease) {
		super();
		this.title = title;
		this.genre = genre;
		this.yearRelease = yearRelease;
	}

	
	// Test Contructor ID
	public Movie(Long id, String title, String genre, int yearRelease) {
		super();
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.yearRelease = yearRelease;
	}
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getYearRelease() {
		return yearRelease;
	}

	public void setYearRelease(int yearRelease) {
		this.yearRelease = yearRelease;
	}

	
	
	
	
}
