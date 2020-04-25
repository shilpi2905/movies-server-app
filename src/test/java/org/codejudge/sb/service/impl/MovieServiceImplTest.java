package org.codejudge.sb.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.codejudge.sb.exception.model.CommonException;
import org.codejudge.sb.h2.entity.EmbeddedShowId;
import org.codejudge.sb.h2.entity.MovieEntity;
import org.codejudge.sb.h2.entity.ShowEntity;
import org.codejudge.sb.h2.entity.TheatreEntity;
import org.codejudge.sb.h2.repository.MovieRepository;
import org.codejudge.sb.h2.repository.ShowRepository;
import org.codejudge.sb.h2.repository.TheatreRepository;
import org.codejudge.sb.model.input.MovieInput;
import org.codejudge.sb.model.input.ShowsInput;
import org.codejudge.sb.model.input.TheatreInput;
import org.codejudge.sb.model.output.MovieOutput;
import org.codejudge.sb.model.output.MovieShows;
import org.codejudge.sb.model.output.ShowsOutput;
import org.codejudge.sb.model.output.TheatreOutput;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MovieServiceImplTest {

	@InjectMocks
	private MovieServiceImpl service;
	@Mock
	private MovieRepository movieRepository;
	@Mock
	private TheatreRepository theatreRepository;
	@Mock
	private ShowRepository showRepository;
	
	ObjectMapper mapper;
	MovieInput movie = null;
	TheatreInput theatre = null;
	ShowsInput show = null;
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mapper = new ObjectMapper();
		movie = new MovieInput();
		movie.setMovieLength(156);
		movie.setMovieName("ABC");
		movie.setMovieOverview("XYZ Theatre");
		movie.setMoviePoster("MKL Poster");
		movie.setMovieTrailer("https://www.youtube.com/watch?v=ZKsc2I4Tgsk");
		theatre = new TheatreInput();
		theatre.setCity("Lucknow");
		theatre.setTheatreLocation("Fun Republic Mall, Gomtinagar");
		theatre.setTheatreName("Fun Cinemas");
		theatre.setPincode(226012);
		show =new ShowsInput();
		show.setDate("2019-07-31");
		show.setMovieId(1);
		show.setTheatreId(1);
		show.setTime("12:00:00");
	}
	
	@Test
	public void addMovieTest() {
		when(movieRepository.save(any())).thenReturn(new MovieEntity());
		MovieOutput output = service.addMovie(movie);
		assertNotNull(output);
	}
	
	@Test(expected = CommonException.class)
	public void addMovieTestFail() {
		when(movieRepository.save(any())).thenThrow(ConstraintViolationException.class);
		MovieOutput output = service.addMovie(movie);
		assertNull(output);
	}
	
	@Test
	public void addTheatreTest() {
		when(theatreRepository.save(any())).thenReturn(new TheatreEntity());
		TheatreOutput output = service.addTheatre(theatre);
		assertNotNull(output);
	}
	
	@Test
	public void addShowTestWhenShowsListIsEmpty() throws ParseException {
		when(movieRepository.findById(any())).thenReturn(Optional.of(new MovieEntity()));
		when(theatreRepository.findById(any())).thenReturn(Optional.of(new TheatreEntity()));
		List<ShowEntity> shows = new ArrayList<ShowEntity>();
		when(showRepository.findByIdMovieIdAndIdTheatreId(any(), any())).thenReturn(shows);
		ShowsOutput output = service.addShow(show);
		assertNotNull(output);
	}
	
	@Test
	public void addShowTestWhenShowsListIsNotEmpty() throws ParseException {
		MovieEntity mEntity = new MovieEntity();
		mEntity.setMovieLength(156);
		when(movieRepository.findById(any())).thenReturn(Optional.of(mEntity));
		when(theatreRepository.findById(any())).thenReturn(Optional.of(new TheatreEntity()));
		List<ShowEntity> shows = new ArrayList<ShowEntity>();
		ShowEntity entity = new ShowEntity();
		entity.setDate("2019-07-31");
		EmbeddedShowId id = new EmbeddedShowId(1, 1, "15:00:00");
		entity.setId(id);
		shows.add(entity);
		when(showRepository.findByIdMovieIdAndIdTheatreId(any(), any())).thenReturn(shows);
		ShowsOutput output = service.addShow(show);
		assertNotNull(output);
	}
	
	@Test(expected = CommonException.class)
	public void addShowTestWhenShowsOverlap() throws ParseException {
		MovieEntity mEntity = new MovieEntity();
		mEntity.setMovieLength(156);
		when(movieRepository.findById(any())).thenReturn(Optional.of(mEntity));
		when(theatreRepository.findById(any())).thenReturn(Optional.of(new TheatreEntity()));
		List<ShowEntity> shows = new ArrayList<ShowEntity>();
		ShowEntity entity = new ShowEntity();
		entity.setDate("2019-07-31");
		EmbeddedShowId id = new EmbeddedShowId(1, 1, "12:00:00");
		entity.setId(id);
		shows.add(entity);
		when(showRepository.findByIdMovieIdAndIdTheatreId(any(), any())).thenReturn(shows);
		ShowsOutput output = service.addShow(show);
		assertNotNull(output);
	}
	
	@SuppressWarnings("static-access")
	@Test(expected = CommonException.class)
	public void addShowTestFail() throws ParseException {
		when(movieRepository.findById(any())).thenReturn(Optional.of(new MovieEntity()).empty());
		when(theatreRepository.findById(any())).thenReturn(Optional.of(new TheatreEntity()).empty());
		List<ShowEntity> shows = new ArrayList<ShowEntity>();
		when(showRepository.findByIdMovieIdAndIdTheatreId(any(), any())).thenReturn(shows);
		ShowsOutput output = service.addShow(show);
		assertNotNull(output);
	}
	
	@Test
	public void getShowsByCityAndDateTest() {
		when(movieRepository.findById(any())).thenReturn(Optional.of(new MovieEntity()));
		List<ShowEntity> shows = new ArrayList<ShowEntity>();
		ShowEntity entity = new ShowEntity();
		entity.setDate("2019-07-31");
		EmbeddedShowId id = new EmbeddedShowId(1, 1, "16:00:00");
		entity.setId(id);
		shows.add(entity);
		when(showRepository.findByIdMovieIdAndDate(any(), any())).thenReturn(shows);
		List<TheatreEntity> theatres = new ArrayList<TheatreEntity>();
		TheatreEntity tEntity = new TheatreEntity();
		tEntity.setTheatreId(1);
		tEntity.setCity(theatre.getCity());
		tEntity.setPincode(theatre.getPincode());
		tEntity.setTheatreName(theatre.getTheatreName());
		tEntity.setTheatreLocation(theatre.getTheatreLocation());
		theatres.add(tEntity);
		when(theatreRepository.findAllById(any())).thenReturn(theatres);
		MovieShows sShows = service.getShowsByCityAndDate(1, "Lucknow", "2019-07-31");
		assertNotNull(sShows);
	}
	
	@SuppressWarnings("static-access")
	@Test(expected = CommonException.class)
	public void getShowsByCityAndDateTestFail() {
		when(movieRepository.findById(any())).thenReturn(Optional.of(new MovieEntity()).empty());
		MovieShows sShows = service.getShowsByCityAndDate(1, "Lucknow", "2019-07-31");
		assertNotNull(sShows);
	}
	
	@Test(expected = CommonException.class)
	public void getShowsByCityAndDateTestFailWhenShowsListIsEmpty() {
		when(movieRepository.findById(any())).thenReturn(Optional.of(new MovieEntity()));
		List<ShowEntity> shows = new ArrayList<ShowEntity>();
		when(showRepository.findByIdMovieIdAndDate(any(), any())).thenReturn(shows);
		MovieShows sShows = service.getShowsByCityAndDate(1, "Lucknow", "2019-07-31");
		assertNotNull(sShows);
	}
	
	@Test(expected = CommonException.class)
	public void getShowsByCityAndDateTestFailWhenTheatresListIsEmpty() {
		when(movieRepository.findById(any())).thenReturn(Optional.of(new MovieEntity()));
		List<ShowEntity> shows = new ArrayList<ShowEntity>();
		ShowEntity entity = new ShowEntity();
		entity.setDate("2019-07-31");
		EmbeddedShowId id = new EmbeddedShowId(1, 1, "16:00:00");
		entity.setId(id);
		shows.add(entity);
		when(showRepository.findByIdMovieIdAndDate(any(), any())).thenReturn(shows);
		List<TheatreEntity> theatres = new ArrayList<TheatreEntity>();
		when(theatreRepository.findAllById(any())).thenReturn(theatres);
		MovieShows sShows = service.getShowsByCityAndDate(1, "Lucknow", "2019-07-31");
		assertNotNull(sShows);
	}
}
