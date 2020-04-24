package org.codejudge.sb.service;

import java.text.ParseException;

import org.codejudge.sb.model.input.MovieInput;
import org.codejudge.sb.model.input.ShowsInput;
import org.codejudge.sb.model.input.TheatreInput;
import org.codejudge.sb.model.output.MovieOutput;
import org.codejudge.sb.model.output.MovieShows;
import org.codejudge.sb.model.output.ShowsOutput;
import org.codejudge.sb.model.output.TheatreOutput;

public interface MovieService {
	
	public MovieOutput addMovie(MovieInput movie);
	public TheatreOutput addTheatre(TheatreInput theatre) ;
	public ShowsOutput addShow(ShowsInput show) throws ParseException;
	public MovieShows getShowsByCityAndDate(Integer movieId, String city, String date);

}
