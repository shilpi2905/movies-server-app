package org.codejudge.sb.model.output;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieOutput {

	@JsonProperty("movie_id")
	private int movieId;

	@JsonProperty("movie_name")
	private String movieName;

	@JsonProperty("movie_trailer")
	private String movieTrailer;

	@JsonProperty("movie_overview")
	private String movieOverview;

	@JsonProperty("movie_poster")
	private String moviePoster;

	@JsonProperty("length")
	private Integer movieLength;

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieTrailer() {
		return movieTrailer;
	}

	public void setMovieTrailer(String movieTrailer) {
		this.movieTrailer = movieTrailer;
	}

	public String getMovieOverview() {
		return movieOverview;
	}

	public void setMovieOverview(String movieOverview) {
		this.movieOverview = movieOverview;
	}

	public String getMoviePoster() {
		return moviePoster;
	}

	public void setMoviePoster(String moviePoster) {
		this.moviePoster = moviePoster;
	}

	public Integer getMovieLength() {
		return movieLength;
	}

	public void setMovieLength(Integer movieLength) {
		this.movieLength = movieLength;
	}

	@Override
	public String toString() {
		return "MovieOutput [movieId=" + movieId + ", movieName=" + movieName + ", movieTrailer=" + movieTrailer
				+ ", movieOverview=" + movieOverview + ", moviePoster=" + moviePoster + ", movieLength=" + movieLength
				+ "]";
	}

}
