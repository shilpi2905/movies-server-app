package org.codejudge.sb.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.codejudge.sb.exception.handler.MovieExceptionHandler;
import org.codejudge.sb.model.input.MovieInput;
import org.codejudge.sb.model.input.ShowsInput;
import org.codejudge.sb.model.input.TheatreInput;
import org.codejudge.sb.model.output.MovieOutput;
import org.codejudge.sb.model.output.MovieShows;
import org.codejudge.sb.model.output.ShowsOutput;
import org.codejudge.sb.model.output.TheatreOutput;
import org.codejudge.sb.service.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieAppController.class)
public class MovieAppControllerTest {

	@InjectMocks
	private MovieAppController controller;
	
	@MockBean
	private MovieService movieService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private MovieExceptionHandler handler;
	
	ObjectMapper mapper;
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mapper = new ObjectMapper();
	}
	
	@Test
	public void addMovieTest() throws Exception {
		String movieInput = "{\r\n" + 
				"        \"movie_name\": \"Long Shotq\",\r\n" + 
				"        \"movie_trailer\": \"https://www.youtube.com/watch?v=ZKsc2I4Tgsk\",\r\n" + 
				"        \"movie_overview\": \"When Fred Flarsky reunites with and charms his first crush, Charlotte Field—one of the most influential women in the world. As Charlotte prepares to make a run for the Presidency, she hires Fred as her speechwriter and sparks fly.\",\r\n" + 
				"        \"movie_poster\": \"http://image.tmdb.org/t/p/original/m2ttWZ8rMRwIMT7zA48Jo6mTkDS.jpg\",\r\n" + 
				"        \"length\": 156\r\n" + 
				"    }";
		MovieInput movie = mapper.readValue(movieInput, MovieInput.class);
		when(movieService.addMovie(any())).thenReturn(new MovieOutput());
		assertEquals(HttpStatus.OK, controller.addMovie(movie).getStatusCode());
	}
	
	/*@Test
	public void addMovieTestFail() throws Exception {
		String movieIn = "{\r\n" + 
				"        \"movie_name\": \"Long Shotq\",\r\n" + 
				"        \"movie_trailer\": \"https://www.youtube.com/watch?v=ZKsc2I4Tgsk\",\r\n" + 
				"        \"movie_overview\": \"When Fred Flarsky reunites with and charms his first crush, Charlotte Field—one of the most influential women in the world. As Charlotte prepares to make a run for the Presidency, she hires Fred as her speechwriter and sparks fly.\",\r\n" + 
				"        \"movie_poster\": \"http://image.tmdb.org/t/p/original/m2ttWZ8rMRwIMT7zA48Jo6mTkDS.jpg\"\r\n" + 
				"    \r\n" + 
				"    }";
		MovieInput movie = mapper.readValue(movieIn, MovieInput.class);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/movies/create").contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(movie))).andReturn();
		assertNotNull(result);
		int status = result.getResponse().getStatus();
		assertEquals(HttpStatus.BAD_REQUEST.value(), status);
	}*/
	
	@Test
	public void addTheatreTest() throws Exception {
		String theatreInput = "{\r\n" + 
				"        \"theatre_name\": \"Fun Cinemas\",\r\n" + 
				"        \"theatre_location\": \"Fun Republic Mall, Gomtinagar\",\r\n" + 
				"        \"city\": \"Lucknow\",\r\n" + 
				"        \"pincode\": 226012\r\n" + 
				"    }";
		TheatreInput theatre = mapper.readValue(theatreInput, TheatreInput.class);
		when(movieService.addTheatre(any())).thenReturn(new TheatreOutput());
		assertEquals(HttpStatus.OK, controller.addTheatre(theatre).getStatusCode());
	}
	
	@Test
	public void addTheatreTestFail() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/theatres/create").contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(null))).andReturn();
		assertNotNull(result);
		int status = result.getResponse().getStatus();
		assertEquals(HttpStatus.BAD_REQUEST.value(), status);
	}
	
	@Test
	public void addShowsTest() throws Exception {
		String showInput = "{\r\n" + 
				"    \"theatre_id\": 1,\r\n" + 
				"    \"movie_id\": 1,\r\n" + 
				"    \"date\": \"2019-07-31\",\r\n" + 
				"    \"time\": \"12:00:00\"\r\n" + 
				"}";
		ShowsInput shows = mapper.readValue(showInput, ShowsInput.class);
		when(movieService.addShow(any())).thenReturn(new ShowsOutput());
		assertEquals(HttpStatus.OK, controller.addShows(shows).getStatusCode());
	}
	
	@Test
	public void addShowsTestFail() throws Exception {
		String showInput ="{\r\n" + 
				"    \"theatre_id\": 1,\r\n" + 
				"    \"movie_id\": 1,\r\n" + 
				"    \"date\": \"2019/07/31\",\r\n" + 
				"    \"time\": \"12:00:00\"\r\n" + 
				"}";
		ShowsInput shows = mapper.readValue(showInput, ShowsInput.class);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/shows/create").contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(shows))).andReturn();
		assertNotNull(result);
		int status = result.getResponse().getStatus();
		assertEquals(HttpStatus.BAD_REQUEST.value(), status);
	}
	
	@Test
	public void getMovieShowsTest() throws Exception {
		when(movieService.getShowsByCityAndDate(any(), any(), any())).thenReturn(new MovieShows());
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/showsBy").contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.accept(MediaType.APPLICATION_JSON_UTF8).content("city=Lucknow&date=2019-07-31&movie_id=1")).andReturn();
		assertNotNull(result);
		int status = result.getResponse().getStatus();
		assertEquals(HttpStatus.OK.value(), status);
	}
	
	@Test
	public void getMovieShowsTestFailWithMissingReqData() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/showsBy").contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.accept(MediaType.APPLICATION_JSON_UTF8).content("date=2019-07-31&movie_id=1")).andReturn();
		assertNotNull(result);
		int status = result.getResponse().getStatus();
		assertEquals(HttpStatus.BAD_REQUEST.value(), status);
	}
	
	@Test
	public void getMovieShowsTestFailWithInvalidCity() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/showsBy").contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.accept(MediaType.APPLICATION_JSON_UTF8).content("city=Lucknow1&date=2019-07-31&movie_id=1")).andReturn();
		assertNotNull(result);
		int status = result.getResponse().getStatus();
		assertEquals(HttpStatus.BAD_REQUEST.value(), status);
	}
	
	@Test
	public void getMovieShowsTestFailWithInvalidData() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/showsBy").contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.accept(MediaType.APPLICATION_JSON_UTF8).content("city=Lucknow&date=2019/07/31&movie_id=1")).andReturn();
		assertNotNull(result);
		int status = result.getResponse().getStatus();
		assertEquals(HttpStatus.BAD_REQUEST.value(), status);
	}
}
