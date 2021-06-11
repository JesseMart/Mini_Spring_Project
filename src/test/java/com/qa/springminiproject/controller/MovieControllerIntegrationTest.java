package com.qa.springminiproject.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.springminiproject.domain.Movie;;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // <-- Mocking the aspect of our app
public class MovieControllerIntegrationTest {
	
	@Autowired
	private MockMvc mock; //<-- Mock the controller and relevant mappers
	
	@Autowired
	private ObjectMapper mapper; // <-- Converting our request to JSON format
	
	
	@Test
	void testCreate() throws Exception {
		
//		//1st step create the Movie
//		Movie movie1 = new Movie("Taken", "Thriller",2007);
//		
//		//2nd step convert to JSON string
//		String movie1AsJSON = this.mapper.writeValueAsString(movie1);
//		
//		//Build mock REQUEST
//		RequestBuilder mockRequest = 
//				post("/movie/createMovie")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(movie1AsJSON);
//		
//		//Create the saved Movie
//		Movie savedMovie = new Movie(1L, "Taken", "Thriller", 2007);
//		
//		//Convert saved movie into JSON
//		String savedMovieAsJSON = this.mapper.writeValueAsString(savedMovie);
//		
//		// Check Http status
//		ResultMatcher matchStatus = status().isCreated();
//		
//		// Check the response body - correct saved movie
//		
//		ResultMatcher matchBody = content().json(savedMovieAsJSON);
//		
//		//BUild REQUEST
//		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
		
		
		this.mock.perform(
				post("/movie/createMovie")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(new Movie("Taken", "Thriller",2007))))
			.andExpect(status().isCreated())
			.andExpect(content().json(this.mapper.writeValueAsString(new Movie(1L, "Taken", "Thriller", 2007))));
		
		
	}
	
	
}
