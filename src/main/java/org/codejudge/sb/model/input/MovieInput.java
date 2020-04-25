package org.codejudge.sb.model.input;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieInput {

	@JsonProperty("movie_name")
	private String movieName;

	@JsonProperty("movie_trailer")
	private String movieTrailer;

	@JsonProperty("movie_overview")
	private String movieOverview;

	@JsonProperty("movie_poster")
	private String moviePoster;

	@JsonProperty("length")
	private Long movieLength;

	//@NotNull(message = "movie_name must not be null")
	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	//@NotNull(message = "movie_trailer must not be null")
	public String getMovieTrailer() {
		return movieTrailer;
	}

	public void setMovieTrailer(String movieTrailer) {
		this.movieTrailer = movieTrailer;
	}

	//@NotNull(message = "movie_overview must not be null")
	public String getMovieOverview() {
		return movieOverview;
	}

	public void setMovieOverview(String movieOverview) {
		this.movieOverview = movieOverview;
	}

	//@NotNull(message = "movie_poster must not be null")
	public String getMoviePoster() {
		return moviePoster;
	}

	public void setMoviePoster(String moviePoster) {
		this.moviePoster = moviePoster;
	}

	@NotNull(message = "length must not be null")
	public Long getMovieLength() {
		return movieLength;
	}

	public void setMovieLength(Long movieLength) {
		this.movieLength = movieLength;
	}

	@Override
	public String toString() {
		return "MovieInput [movieName=" + movieName + ", movieTrailer=" + movieTrailer + ", movieOverview="
				+ movieOverview + ", moviePoster=" + moviePoster + ", movieLength=" + movieLength + "]";
	}

}
