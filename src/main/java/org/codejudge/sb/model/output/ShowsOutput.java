package org.codejudge.sb.model.output;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShowsOutput {

	@JsonProperty("movie")
	private MovieOutput movie;

	@JsonProperty("theatre")
	private TheatreOutput theatre;

	@JsonProperty("shows")
	private List<Shows> shows;

	public MovieOutput getMovie() {
		return movie;
	}

	public void setMovie(MovieOutput movie) {
		this.movie = movie;
	}

	public TheatreOutput getTheatre() {
		return theatre;
	}

	public void setTheatre(TheatreOutput theatre) {
		this.theatre = theatre;
	}

	public List<Shows> getShows() {
		return shows;
	}

	public void setShows(List<Shows> shows) {
		this.shows = shows;
	}

	@Override
	public String toString() {
		return "ShowsOutput [movie=" + movie + ", theatre=" + theatre + ", shows=" + shows + "]";
	}

}
