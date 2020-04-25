package org.codejudge.sb.controller;

import java.text.ParseException;

import javax.validation.Valid;

import org.codejudge.sb.exception.model.CommonException;
import org.codejudge.sb.exception.model.Error;
import org.codejudge.sb.model.input.MovieInput;
import org.codejudge.sb.model.input.ShowsInput;
import org.codejudge.sb.model.input.TheatreInput;
import org.codejudge.sb.model.output.MovieOutput;
import org.codejudge.sb.model.output.MovieShows;
import org.codejudge.sb.model.output.ShowsOutput;
import org.codejudge.sb.model.output.TheatreOutput;
import org.codejudge.sb.service.MovieService;
import org.codejudge.sb.utils.Constants;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MovieAppController {
	
	@Autowired
	private MovieService movieService;
	
	@ApiOperation("This is create movie api")
    @RequestMapping(value = "/movies/create", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<MovieOutput> addMovie(@ApiParam(value = "") @Valid @RequestBody MovieInput movieBody) {
		log.info("movieBody:: {}", movieBody);
		return new ResponseEntity<MovieOutput>(movieService.addMovie(movieBody), HttpStatus.OK);
	}
	
	@ApiOperation("This is create theatre api")
    @RequestMapping(value = "/theatres/create", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<TheatreOutput> addTheatre(@ApiParam(value = "") @Valid @RequestBody TheatreInput theatreBody) {
		log.info("theatreBody:: {}", theatreBody);
		return new ResponseEntity<TheatreOutput>(movieService.addTheatre(theatreBody), HttpStatus.OK);
	}
	
	@ApiOperation("This is add shows api")
    @RequestMapping(value = "/shows/create", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ShowsOutput> addShows(@ApiParam(value = "") @Valid @RequestBody ShowsInput showsBody) throws ParseException  {
		MDC.put("movieId", String.valueOf(showsBody.getMovieId()));
		MDC.put("theatreId", String.valueOf(showsBody.getTheatreId()));
		log.info("showsBody:: {}", showsBody);
		return new ResponseEntity<ShowsOutput>(movieService.addShow(showsBody), HttpStatus.OK);
	}
	
	@ApiOperation("This is get movie shows api")
    @RequestMapping(value = "/showsBy", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<MovieShows> getMovieShows(@RequestParam(name = "city", required = true) String city,
			@RequestParam(name="date", required = true) String date, @RequestParam(name="movie_id", required = true) int movieId) throws ParseException {
		MDC.put("movieId", String.valueOf(movieId));
		log.info("request params:: City {}, date {}, movie_id {}", city, date, movieId);
		if(!date.matches("^\\d{4}-\\d{2}-\\d{2}$") || !city.matches("Bengaluru|Mumbai|Delhi|Lucknow")) {
			throw new CommonException(new Error(Constants.FAILURE, "Error: Invalid input: Please provide valid city (Bengaluru/Mumbai/Delhi/Lucknow) or date in format (yyyy-mm-dd)"));
		}
		return new ResponseEntity<MovieShows>(movieService.getShowsByCityAndDate(movieId, city, date), HttpStatus.OK);
	}

}
