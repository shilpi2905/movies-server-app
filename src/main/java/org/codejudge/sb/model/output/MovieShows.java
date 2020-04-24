package org.codejudge.sb.model.output;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieShows {
	
	@JsonProperty("movie")
	private MovieOutput movie;
	
	@JsonProperty("theatres")
	private List<TheatreShows> theatres;

	public MovieOutput getMovie() {
		return movie;
	}

	public void setMovie(MovieOutput movie) {
		this.movie = movie;
	}

	public List<TheatreShows> getTheatres() {
		return theatres;
	}

	public void setTheatres(List<TheatreShows> theatres) {
		this.theatres = theatres;
	}

	@Override
	public String toString() {
		return "MovieShows [movie=" + movie + ", theatres=" + theatres + "]";
	}

}
