package com.qa.springminiproject.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.springminiproject.domain.Movie;
import com.qa.springminiproject.repo.MovieRepo;

@SpringBootTest
public class MovieServiceUnitTest {

	@MockBean
	private MovieRepo repo;
	
	@Autowired
	private MovieService service;
	
	@Test
	void testCreateUnit() {
		// GIVEN
		Movie taken = new Movie("Taken", "Thriller",2007);
		Movie takenWithId = new Movie(1L, "Taken", "Thriller", 2007);
		//WHEN
		Mockito.when(this.repo.save(taken)).thenReturn(takenWithId);
		//THEN
		assertThat(this.service.createMovie(taken)).isEqualTo(takenWithId);
		
		Mockito.verify(this.repo, Mockito.times(1)).save(taken);
		
	}
	
}
