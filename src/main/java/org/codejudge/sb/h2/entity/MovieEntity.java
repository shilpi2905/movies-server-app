package org.codejudge.sb.h2.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MOVIES")
public class MovieEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movie_id")
	private int movieId;
	@Column(unique = true, name = "movie_name")
	private String movieName;
	@Column(name = "movie_trailer")
	private String movieTrailer;
	@Column(name = "movie_overview")
	private String movieOverview;
	@Column(name = "movie_poster")
	private String moviePoster;
	@Column(name = "length")
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
		return "MovieEntity [movieId=" + movieId + ", movieName=" + movieName + ", movieTrailer=" + movieTrailer
				+ ", movieOverview=" + movieOverview + ", moviePoster=" + moviePoster + ", movieLength=" + movieLength
				+ "]";
	}

}
